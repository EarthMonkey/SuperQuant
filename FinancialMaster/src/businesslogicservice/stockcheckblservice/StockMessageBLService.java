package businesslogicservice.stockcheckblservice;

import PO.StockPO;

public interface StockMessageBLService {
	// ������ѡ��Ʊ����ϸ��Ϣ�������StockPO
	public StockPO getStockMessage(String id);

	// �����û������ʱ��η���ɸѡ������б�
	public StockPO updateStockList(String id, String startData, String overData);
}
