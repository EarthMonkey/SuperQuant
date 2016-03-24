package businesslogic.stockcheckbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import PO.codeNamePO;
import PO.stockStatisticPO;
import businesslogicservice.stockcheckblservice.StockListBLService;
import data.manageStockData.ManageStockData;
import data.stockcheckdata.StockData;
import dataservice.manageStockService.manageStockDataService;
import dataservice.stockcheckdataservice.StockDataService;

public class StockListBL implements StockListBLService {

	ArrayList<String[]> init_list = new ArrayList<String[]>();

	@Override
	public String[][] getStockList() {
		// ��Ʊ���롢���̼ۡ���߼ۡ���ͼۡ����̼ۡ���Ȩ�ۡ��ɽ����������ʡ���ӯ�ʡ��о���
		StockDataService sds = new StockData();
		manageStockDataService msds=new ManageStockData();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = format.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		String endDay = format.format(cal.getTime());
		// codeNamePO sz_codeName = sds.getCodeName(2015, "sz");
		// codeNamePO sh_codeName = sds.getCodeName(2015, "sh");
		// ArrayList<String> sz_list = sz_codeName.getResult();
		// ArrayList<String> sh_list = sz_codeName.getResult();
		// int size = sz_list.size() + sh_list.size();
		// String[][] list = new String[size][6];
		// int index = 0;
		ArrayList<stockStatisticPO> ssPOlist;
		stockStatisticPO ssPO;
		ArrayList<String> stockList;
		stockList = msds.getCodeOfStock();
		// for (String sz_s : sz_list) {
		// list[index][0] = sz_s;
		// ssPOlist = sds.getStatisitcOfStock(sz_s, yestoday, today);
		// if (!ssPOlist.isEmpty()) {
		// ssPO = ssPOlist.get(0);
		// list[index][1] = ssPO.getOpen() + "";
		// list[index][2] = ssPO.getHigh() + "";
		// list[index][3] = ssPO.getLow() + "";
		// list[index][4] = ssPO.getClose() + "";
		// list[index][5] = ssPO.getVolume() + "";
		// } else {
		// for (int i = 1; i < 6; i++) {
		// list[index][i] = "";
		// }
		// }
		// init_list.add(list[index]);
		// index++;
		// }
		// for (String sh_s : sh_list) {
		// list[index][0] = sh_s;
		// ssPOlist = sds.getStatisitcOfStock(sh_s, yestoday, today);
		// if (!ssPOlist.isEmpty()) {
		// ssPO = ssPOlist.get(0);
		// list[index][1] = ssPO.getOpen() + "";
		// list[index][2] = ssPO.getHigh() + "";
		// list[index][3] = ssPO.getLow() + "";
		// list[index][4] = ssPO.getClose() + "";
		// list[index][5] = ssPO.getVolume() + "";
		// } else {
		// for (int i = 1; i < 6; i++) {
		// list[index][i] = "";
		// }
		// }
		// init_list.add(list[index]);
		// index++;
		// }
		int size=stockList.size();
		do {
			cal.add(Calendar.DATE, -1);
			startDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock(stockList.get(0), startDay, endDay);
		} while (ssPOlist.isEmpty());
		String list[][] = new String[size][7];
		for (int i = 0; i < size; i++) {
			list[i][0] = stockList.get(i);
			ssPOlist = sds.getStatisitcOfStock(list[i][0], startDay, endDay);
			ssPO = ssPOlist.get(0);
			list[i][1]=ssPO.getName();
			list[i][2] = ssPO.getOpen() + "";
			list[i][3] = ssPO.getHigh() + "";
			list[i][4] = ssPO.getLow() + "";
			list[i][5] = ssPO.getClose() + "";
			list[i][6] = ssPO.getVolume() + "";

			init_list.add(list[i]);
		}
		return list;
	}

	@Override
	public String[][] updateStockList(String key) {
		ArrayList<String[]> new_list = new ArrayList<String[]>();
		for (String[] s : init_list) {
			if (s[0].contains(key)) {
				new_list.add(s);
			}
		}
		int size = new_list.size();
		if (size == 0) {
			String[][] list = { { "-", "-", "-", "-", "-", "-" ,"-"} };
			return list;
		} else {
			String list[][] = new String[size][7];
			int index = 0;
			for (String[] strings : new_list) {
				list[index] = strings;
				index++;
			}
			return list;
		}

	}

}
