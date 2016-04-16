package businesslogic.stockcheckbl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import PO.stockStatisticPO;
import businesslogicservice.stockcheckblservice.StockListBLService;
import data.manageStockData.ManageStockData;
import data.stockcheckdata.StockData;
import dataservice.manageStockService.manageStockDataService;
import dataservice.stockcheckdataservice.StockDataService;

public class StockListBL implements StockListBLService {

	ArrayList<String[]> init_list = new ArrayList<String[]>();
	String list[][];
	String startDay;
	String endDay;
	String yesStartDay;
	Double ups_and_downs;
	NumberFormat nf;
	ArrayList<stockStatisticPO> ssPOlist;
	stockStatisticPO ssPO;
	StockDataService sds;

	public StockListBL() {
		sds = new StockData();
		manageStockDataService msds = new ManageStockData();
		nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);// С���������λ
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		startDay = format.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		endDay = format.format(cal.getTime());

		ArrayList<String> stockList;
		stockList = msds.getCodeOfStock();

		int size = stockList.size();
		do {
			cal.add(Calendar.DATE, -1);
			startDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock("sh600600", startDay,
					endDay);
		} while (ssPOlist.isEmpty());

		do {
			cal.add(Calendar.DATE, -1);
			yesStartDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock("sh600600", yesStartDay,
					startDay);
		} while (ssPOlist.isEmpty());

		list = new String[size][8];
		Double close[][] = new Double[size][2];

		for (int i = 0; i < size; i++) {
			list[i][0] = stockList.get(i);
			ssPOlist = sds.getStatisitcOfStock(list[i][0], startDay, endDay);
			ssPO = ssPOlist.get(0);
			close[i][1] = ssPO.getClose();
			list[i][1] = ssPO.getName();
			list[i][2] = ssPO.getOpen() + "";
			list[i][3] = ssPO.getHigh() + "";
			list[i][4] = ssPO.getLow() + "";
			list[i][5] = ssPO.getClose() + "";
			list[i][6] = ssPO.getVolume() + "";
			ssPOlist = sds.getStatisitcOfStock(list[i][0], yesStartDay,
					startDay);
			ssPO = ssPOlist.get(0);
			close[i][0] = ssPO.getClose();			
			
			
			if(close[i][0]==0){
				ups_and_downs = 0.0 ;
				
			}else{
				ups_and_downs = (close[i][1] - close[i][0]) / close[i][0];
			}
			list[i][7] = nf.format(ups_and_downs);
			init_list.add(list[i]);
		}
	}
    //��ע��ȡ����עʱ���¹�Ʊ�б�
	public void update(String id, int i) {
		int size = list.length;
		list = new String[size + i][10];
		int index = 0;
		if (i == -1) {
			String[] needRemove=new String[10];
			for (String[] strings : init_list) {
				if (strings[0].equals(id)) {
					needRemove=strings;
				} else {
					list[index] = strings;
					index++;
				}
			}
			init_list.remove(needRemove);
		} else {
			for (String[] strings : init_list) {
				list[index] = strings;
				index++;

			}

			double close[] = new double[2];

			ssPOlist = sds.getStatisitcOfStock(id, startDay, endDay);
			ssPO = ssPOlist.get(0);
			close[1] = ssPO.getClose();
			list[index][0] = id;
			list[index][1] = ssPO.getName();
			list[index][2] = ssPO.getOpen() + "";
			list[index][3] = ssPO.getHigh() + "";
			list[index][4] = ssPO.getLow() + "";
			list[index][5] = ssPO.getClose() + "";
			list[index][6] = ssPO.getVolume() + "";
			ssPOlist = sds.getStatisitcOfStock(id, yesStartDay, startDay);
			ssPO = ssPOlist.get(0);
			close[0] = ssPO.getClose();
			if(close[0]==0){
				ups_and_downs = 0.0 ;
				
			}else{
				ups_and_downs = (close[1] - close[0]) / close[0];
			}
			list[index][7] = nf.format(ups_and_downs);
			init_list.add(list[index]);

		}
	}
	// ����Ԥѡ��Ʊ���б��ö�ά����չʾ
	@Override
	public String[][] getStockList() {
		// ��Ʊ���롢���̼ۡ���߼ۡ���ͼۡ����̼ۡ���Ȩ�ۡ��ɽ����������ʡ���ӯ�ʡ��о���

		return list;
	}
	// ����ɸѡ������б�
	@Override
	public String[][] updateStockList(String key) {
		ArrayList<String[]> new_list = new ArrayList<String[]>();
		for (String[] s : init_list) {
			if (s[0].contains(key)) {
				new_list.add(s);
			}
		}
		int size = new_list.size();
//		if (size == 0) {
//			String[][] list = { { "", "", "", "", "", "", "" } };
//			return list;
//		} else {
			String list[][] = new String[size][7];
			int index = 0;
			for (String[] strings : new_list) {
				list[index] = strings;
				index++;
			}
			return list;
//		}

	}

}
