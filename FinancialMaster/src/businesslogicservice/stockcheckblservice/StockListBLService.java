package businesslogicservice.stockcheckblservice;

public interface StockListBLService {

	// ����Ԥѡ��Ʊ���б��ö�ά����չʾ
	public String[][] getStockList();

	// ����ɸѡ������б�
	public String[][] updateStockList(String key);
}
