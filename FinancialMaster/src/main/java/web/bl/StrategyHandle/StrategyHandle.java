package web.bl.StrategyHandle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.pojo.TradeRecord;
import DAO.pojo.UserStrategy;
import PO.StrategyPO;
import PO.profitPO;
import data.StockData.StockData;
import data.UserData.UserStrategyData;
import dataservice.StockDataService.StockDataService;
import dataservice.UserDataService.UserStrategyDataService;
import web.blservice.StrategyHandleService.StrategyHandleService;

public class StrategyHandle implements StrategyHandleService {

	
	// �������ʱ����ÿ���������Ե�����
	public ArrayList<profitPO> handle(ArrayList<StrategyPO> arrayList1, ArrayList<StrategyPO> arrayList2) {
		ArrayList<ArrayList<profitPO>> eachResultList = new ArrayList<>();
		ArrayList<profitPO> totalResult = new ArrayList<>();
		try {
			for (int i = 0; i < arrayList1.size(); i++) {
				StrategyPO po1 = arrayList1.get(i);
				StrategyPO po2 = arrayList2.get(i);
				eachResultList.add(getEachResult(po1, po2));
			}

			for (ArrayList<profitPO> arrayList : eachResultList) {
				for (profitPO profitPO : arrayList) {
					boolean in = false;
					for (profitPO profitPO2 : totalResult) {
						if (profitPO2.getDate().compareTo(profitPO.getDate()) == 0) {
							profitPO2.setProfit(profitPO.getProfit() + profitPO2.getProfit());
							in = true;
							break;
						}
					}
					if (in == false) {
						totalResult.add(profitPO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rank(totalResult);
		return totalResult;
	}

	// ��totalResult�����絽���˳������
	public void rank(ArrayList<profitPO> totalResult) {
		for (int i = 0; i < totalResult.size(); i++) {
			for (int j = 0; j < totalResult.size() - 1; j++) {
				if (totalResult.get(j).getDate().compareTo(totalResult.get(j + 1).getDate()) > 0) {
					profitPO po = totalResult.get(j);
					totalResult.set(j, totalResult.get(j + 1));
					totalResult.set(j + 1, po);
				}
			}
		}
	}

	// ÿ��С���ԵĲ���ʱ����ÿ�������
	public ArrayList<profitPO> getEachResult(StrategyPO po1, StrategyPO po2) {

		StockDataService service = new StockData();
		ArrayList<profitPO> arrayList = new ArrayList<>();
		try {
			List list = service.getStockRecord(po1.getStockId(), po1.getStarttime(), po1.getEndtime());
			ArrayList<TradeRecord> temp = new ArrayList<>();
			ArrayList<TradeRecord> result = new ArrayList<>();
			for (Object object : list) {
				temp.add((TradeRecord) object);
			}
			for (int i = temp.size() - 1; i >= 0; i--) {
					result.add(temp.get(i));
			}
			System.out.print(result.size());
			
			//�Ƿ��������״̬��trueΪ������
			boolean buy=false;
			
			// ԭ����
			double originalCost=po1.getCost();
			//ʹ�õ��ʽ�
			double cost = po1.getCost();
			System.out.println("cost "+cost);
			
			// ������
			double volume = 0.0;
			
			//���������
			Date date=null;
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat( "yyyy-MM-dd");
			date=simpleDateFormat.parse(result.get(0).getId().getDate());
			
			

			for (TradeRecord tradeRecord : result) {
				//ÿ����¼�ĵ���ʱ��
				Date now=null;
				now=simpleDateFormat.parse(tradeRecord.getId().getDate());
				
				//�ͳ�ʼʱ������ʱ��
				long l=now.getTime()-date.getTime();
				long day=l/(24*60*60*1000);
				
				
				if(buy==false){
					//������ʱ
					if(check(po1, tradeRecord)&&(day%po1.getFrequency()==0)){
						volume=cost/tradeRecord.getClose();
						profitPO profitPO = new profitPO(tradeRecord.getId().getDate(), cost-originalCost);
						arrayList.add(profitPO);
						buy=true;
						System.out.println(profitPO.getDate()+" ; "+profitPO.getProfit()+" ;1 "+buy);
					}else{
						//�������뵫�����ʱ
						if (arrayList.size()!=0) {
							profitPO oldprofitPO=arrayList.get(arrayList.size()-1);
							profitPO newprofitPO=new profitPO(tradeRecord.getId().getDate(), oldprofitPO.getProfit());
							arrayList.add(newprofitPO);
							System.out.println(newprofitPO.getDate()+" ; "+newprofitPO.getProfit()+" ;2 "+buy);
						//��������Ҳ�������ʱ
						}else {
							profitPO profitPO=new profitPO(tradeRecord.getId().getDate(), 0.0);
							arrayList.add(profitPO);
							System.out.println(profitPO.getDate()+" ; "+profitPO.getProfit()+" ;3 "+buy);
						}
					}
				}else {
					//������ʱ
					Date temp1=simpleDateFormat.parse(po2.getStarttime());
					Date temp2=simpleDateFormat.parse(po2.getEndtime());
					//Ҫ����ʱ���ϵ�����
					if(check(po2, tradeRecord)&&(day%po2.getFrequency()==0)
							&&(!now.before(temp1))&&(!now.after(temp2))){
						
						cost=volume*tradeRecord.getClose();
						profitPO profitPO=new profitPO(tradeRecord.getId().getDate(), cost-originalCost);
						arrayList.add(profitPO);
						buy=false;
						System.out.println(profitPO.getDate()+" ; "+profitPO.getProfit()+" ;4 "+buy);
					}else {
						profitPO profitPO=new profitPO(tradeRecord.getId().getDate(), volume*tradeRecord.getClose()-originalCost);
						arrayList.add(profitPO);
						System.out.println(profitPO.getDate()+" ; "+profitPO.getProfit()+" ;5 "+buy);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	// �Ƿ��������/��������
	public boolean check(StrategyPO strategyPO, TradeRecord tradeRecord) {
		boolean result = true;
		ArrayList<Strategy> strategies = StrategyMap.getStrategy();
		for (Strategy strategy : strategies) {
			if (strategy.buyStrategy(strategyPO, tradeRecord) == false) {
				result = false;
			}
		}
		return result;
	}
	
	//������������ȫΪ��ʱҲͨ��
	public boolean avoid(StrategyPO po){
		if (po.getPriceLow()==0&&po.getPriceHigh()==0&&po.getVolumeLow()==0&&po.getVolumeHigh()==0
				&&po.getTurnoverLow()==0&&po.getTurnoverHigh()==0&&po.getPeLow()==0&&po.getPeHigh()==0
				&&po.getPbLow()==0&&po.getPbHigh()==0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public profitPO getProfit(String userId, String stockId, String strategyName, String starttime, String endtime) {
		UserStrategyDataService userStrategyDataService=new UserStrategyData();
		profitPO po=new profitPO("", 0);
		try {
			//��ȡԭ����
			List list=userStrategyDataService.getUserStrategys(userId, strategyName);
			UserStrategy userStrategy=new UserStrategy();
			for (Object object : list) {
				UserStrategy temp=(UserStrategy) object;
				if (temp.getId().getStockId().equals(stockId)) {
					userStrategy=temp;
					break;
				}
			}
			
			//��ȡ����
			String[] buydatas=userStrategy.getBuystrategy().split(",");
			String[] selldatas=userStrategy.getSellstrategy().split(",");
			
			
			//��װ
			ArrayList<StrategyPO> buy=new ArrayList<>();
			ArrayList<StrategyPO> sell=new ArrayList<>();
			StrategyPO buyPO=new StrategyPO(
					stockId,
					starttime, 
					endtime, 
					Double.parseDouble(buydatas[0]), 
					Double.parseDouble(buydatas[1]), 
					Double.parseDouble(buydatas[2]), 
					Double.parseDouble(buydatas[3]), 
					Double.parseDouble(buydatas[4]), 
					Double.parseDouble(buydatas[5]), 
					Double.parseDouble(buydatas[6]), 
					Double.parseDouble(buydatas[7]), 
					Double.parseDouble(buydatas[8]), 
					Double.parseDouble(buydatas[9]), 
					userStrategy.getFrequency(), 
					userStrategy.getWeight());
			buy.add(buyPO);
			
			StrategyPO sellPO=new StrategyPO(
					stockId, 
					starttime, 
					endtime, 
					Double.parseDouble(selldatas[0]), 
					Double.parseDouble(selldatas[1]), 
					Double.parseDouble(selldatas[2]), 
					Double.parseDouble(selldatas[3]), 
					Double.parseDouble(selldatas[4]), 
					Double.parseDouble(selldatas[5]), 
					Double.parseDouble(selldatas[6]), 
					Double.parseDouble(selldatas[7]), 
					Double.parseDouble(selldatas[8]), 
					Double.parseDouble(selldatas[9]), 
					userStrategy.getFrequency(), 
					userStrategy.getWeight());
			
			sell.add(sellPO);
			
			ArrayList<profitPO> arrayList=handle(buy, sell);
			return arrayList.get(arrayList.size()-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return po;
	}
	
}
