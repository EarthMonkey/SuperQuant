package businesslogicservice.stockcheckblservice;

import VO.StockVO;

public interface StockMessageBLService {
	// ������ѡ��Ʊ����ϸ��Ϣ�������StockPO
	public StockVO getStockMessage(String id,String startData, String overData);
	
	//���ݹؼ��ַ�Χɸѡ��Ʊ
	public StockVO filterStockMessage(int i,double low,double high);
}
