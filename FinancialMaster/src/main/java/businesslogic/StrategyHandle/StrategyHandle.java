package businesslogic.StrategyHandle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;
import PO.profitPO;
import businesslogicservice.StrategyHandleService.StrategyHandleService;
import data.StockData.StockData;
import dataservice.StockDataService.StockDataService;


public class StrategyHandle implements StrategyHandleService{
	//�������ʱ����ÿ���������Ե�����
	public ArrayList<profitPO> handle(ArrayList<StrategyPO> arrayList1,ArrayList<StrategyPO>arrayList2){
		ArrayList<ArrayList<profitPO>> eachResultList=new ArrayList<>();
		ArrayList<profitPO> totalResult=new ArrayList<>();
		for(int i=0;i<arrayList1.size();i++){
			StrategyPO po1=arrayList1.get(i);
			StrategyPO po2=arrayList2.get(i);
			eachResultList.add(getEachResult(po1,po2));
		}
		
	for (ArrayList<profitPO> arrayList : eachResultList) {
		for (profitPO profitPO : arrayList) {
			boolean in=false;
			for (profitPO profitPO2 : totalResult) {
				if (profitPO2.getDate().compareTo(profitPO.getDate())==0) {
					profitPO2.setProfit(profitPO.getProfit()+profitPO2.getProfit());
					in=true;
					break;
				}
			}
			if (in==false) {
				totalResult.add(profitPO);
			}
		}
	}
	
		rank(totalResult);
		return totalResult;
	}

	//��totalResult�����絽���˳������
	public void rank(ArrayList<profitPO> totalResult) {
		for (int i = 0; i < totalResult.size(); i++) {
			for (int j = 0; j < totalResult.size()-1; j++) {
				if (totalResult.get(j).getDate().compareTo(totalResult.get(j+1).getDate())>0) {
					profitPO po=totalResult.get(j);
					totalResult.set(j, totalResult.get(j+1));
					totalResult.set(j+1, po);
				}
			}
		}
	}

	//ÿ��С���ԵĲ���ʱ����ÿ�������
	public ArrayList<profitPO> getEachResult(StrategyPO po1, StrategyPO po2) {
		StockDataService service=new StockData();
		ArrayList<profitPO> arrayList=new ArrayList<>();
		try {
			List list=service.getStockRecord(po1.getStockId(), po1.getStarttime(), po1.getEndtime());
			ArrayList<TradeRecord> temp=new ArrayList<>();
			ArrayList<TradeRecord> result=new ArrayList<>();
			int count=0;//��¼�����ܷ����������ļ�¼���±�
			for (Object object : list) {
				temp.add((TradeRecord)object);
			}
			for(int i=temp.size()-1;i>=0;i--){
				if (check(po1, temp.get(i))) {
					count=i;
					break;
				}
			}
			for (int i = count; i >=0 ; i--) {
				result.add(temp.get(i));
			}
			
			//������
			double volume=po1.getCost()/temp.get(0).getClose();
			//ԭ����
			double cost=po1.getCost();
			for (TradeRecord tradeRecord : result) {
				profitPO profitPO=new profitPO(tradeRecord.getId().getDate(), volume*tradeRecord.getClose()-cost);
				arrayList.add(profitPO);
				if (check(po2, tradeRecord)&&
						timeCompare(tradeRecord.getId().getDate(), po2.getStarttime(),po2.getEndtime())) {
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	
	//�Ƿ��������/��������
	public boolean check(StrategyPO strategyPO,TradeRecord tradeRecord){
		boolean result=true;
		ArrayList<Strategy> strategies=StrategyMap.getStrategy();
		for (Strategy strategy : strategies) {
			if (strategy.buyStrategy(strategyPO, tradeRecord)==false) {
				result=false;
				break;
			}
		}
		return result;
	}

	//ʱ��Ƚ�
	public boolean timeCompare(String tradeRecord,String starttime,String endtime){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date former=format.parse(tradeRecord);
			Date latter1=format.parse(starttime);
			Date latter2=format.parse(endtime);
			return former.after(latter1)||former.before(latter2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
