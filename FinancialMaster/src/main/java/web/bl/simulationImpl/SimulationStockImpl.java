package web.bl.simulationImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import DAO.pojo.Simulation;
import DAO.pojo.SimulationProfit;
import DAO.pojo.TradeRecord;
import ENUM.Deal_enum;
import ENUM.ManageState;
import PO.UpToDateStockPO;
import VO.SimulationStockVO;
import data.SimulationData.SimulationData;
import data.SimulationData.SimulationProfitData;
import data.StockData.StockData;
import dataservice.SimulationDataService.SimulationDataService;
import dataservice.SimulationDataService.SimulationProfitDataService;
import dataservice.StockDataService.StockDataService;
import web.bl.stockImpl.StockImpl;
import web.blservice.simulationInfo.SimulationStockInfo;
import web.blservice.stockInfo.StockUpdateInfo;

public class SimulationStockImpl implements SimulationStockInfo {

	@Override
	public int buyStock(SimulationStockVO simulationStockVO) {
		SimulationDataService simulationDataService=new SimulationData();
		Simulation simulation=new Simulation();
		simulation.setUserId(simulationStockVO.getUserID());
		simulation.setStockId(simulationStockVO.getStockID());
		simulation.setTime(new Date());
		StockDataService stockDataService=new StockData();
		
		StockUpdateInfo stockUpdateInfo=new StockImpl();
		UpToDateStockPO upToDateMessage=stockUpdateInfo.update(simulationStockVO.getStockID());
		double price=upToDateMessage.getNow();
		simulation.setPrice(price);
		simulation.setVolume(simulationStockVO.getNumber());
		
		SimulationProfitDataService simulationProfitDataService=new SimulationProfitData();
		SimulationProfit simulationProfit=new SimulationProfit();
		simulationProfit.setUserId(simulation.getUserId());
		simulationProfit.setStockId(simulation.getStockId());
		simulationProfit.setOperation(Deal_enum.Buy.toString());
		simulationProfit.setDate(new Date());
		simulationProfit.setProfit(price*simulationStockVO.getNumber());
		simulationProfitDataService.persist(simulationProfit);		
		
		return simulationDataService.persist(simulation);
	}

	private double getResult(int id) {
		SimulationDataService simulationDataService=new SimulationData();
		Simulation simulation=simulationDataService.getById(id);

		StockUpdateInfo stockUpdateInfo=new StockImpl();
		UpToDateStockPO upToDateMessage=stockUpdateInfo.update(simulation.getStockId());
		
		return upToDateMessage.getNow()-simulation.getPrice();
	}

	@Override
	public String[][] getResuleDetail(String id) {
		SimulationDataService simulationDataService=new SimulationData();
		Simulation simulation=simulationDataService.getById(Integer.parseInt(id));
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String startTime=sdf.format(simulation.getTime());
		
		Calendar calendar=Calendar.getInstance();
		String endTime = sdf.format(calendar.getTime());
		
		StockDataService stockDataService=new StockData();
		String[][] result=new String[0][0];
		try {
			List<TradeRecord> records=stockDataService.getStockRecord(id, startTime, endTime);
			int size=records.size();
			result=new String[size][2];
			int index=size-1;
			for (TradeRecord tradeRecord : records) {
				result[index][0]=tradeRecord.getId().getDate();
				result[index][1]=(tradeRecord.getClose()-simulation.getPrice())+"";
				index--;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public ManageState sellStock(String id) {
		SimulationDataService simulationDataService=new SimulationData();
		int code=Integer.parseInt(id);
		Simulation simulation=simulationDataService.getById(code);
		simulationDataService.remove(code);
		
		SimulationProfitDataService simulationProfitDataService=new SimulationProfitData();
		SimulationProfit simulationProfit=new SimulationProfit();
		simulationProfit.setUserId(simulation.getUserId());
		simulationProfit.setStockId(simulation.getStockId());
		simulationProfit.setOperation(Deal_enum.Sell.toString());
//		simulationProfit.setState(state);
		simulationProfit.setDate(new Date());
		simulationProfit.setProfit(getResult(code));
		simulationProfitDataService.persist(simulationProfit);
		return ManageState.Succeed;
	}

	@Override
	public ArrayList<SimulationStockVO> getStockList(String userID) {
		SimulationDataService simulationDataService=new SimulationData();
		ArrayList<Simulation> simulations=simulationDataService.getUserRecords(userID);
		ArrayList<SimulationStockVO> simulationStockVOs=new ArrayList<SimulationStockVO>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		StockUpdateInfo stockUpdateInfo=new StockImpl();
		
		for (Simulation simulation : simulations) {
			SimulationStockVO simulationStockVO=new SimulationStockVO();
			simulationStockVO.setId(simulation.getId()+"");
			simulationStockVO.setUserID(simulation.getUserId());
			simulationStockVO.setStockID(simulation.getStockId());
			simulationStockVO.setTime(sdf.format(simulation.getTime()));
			simulationStockVO.setPrice(simulation.getPrice());
			simulationStockVO.setNumber(simulation.getVolume());
			UpToDateStockPO upToDateMessage=stockUpdateInfo.update(simulation.getStockId());
			simulationStockVO.setNow(upToDateMessage.getNow());
			simulationStockVO.setProfitability(getResult(simulation.getId()));
			simulationStockVOs.add(simulationStockVO);
		}		
		return simulationStockVOs;
	}

}
