package businesslogicservice.stockcheckblservice;

import VO.StockVO;

public interface StockMessageBLService {
	// ������ѡ��Ʊ����ϸ��Ϣ�������StockPO
	public StockVO getStockMessage(String id);

	// �����û������ʱ��η���ɸѡ������б�
	public StockVO updateStockList(String id, String startData, String overData);
}
