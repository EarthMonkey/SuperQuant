package businesslogic.stockcheckbl;

import businesslogicservice.stockcheckblservice.StockListBLService;

public class StockListBL implements StockListBLService {

	
	@Override
	public String[][] getStockList() {
//		��Ʊ���롢���̼ۡ���߼ۡ����̼ۡ���ͼۡ������������׽��
		String[][] test={{"sh600000","27.65","33.66","31.23","26.55","10240000","1000000000"}
		,{"sh600001","27.65","33.66","31.23","26.55","10240000","1000000000"},
		{"sh600002","27.65","33.66","31.23","26.55","10240000","1000000000"},
		{"sh600003","27.65","33.66","31.23","26.55","10240000","1000000000"}};
		return test;
	}

	@Override
	public String[][] updateStockList(String key) {
        
		return null;
	}

}
