package businesslogicservice.stockcheckblservice;

import VO.StockVO;

public interface StockMessageBLService {
	
	
	// ������ѡ��Ʊ����ϸ��Ϣ�������StockVO
    public StockVO getStockMessage(String id);

    //����ѡ�����ڶθ�����ʷ�����б��б�
    
    public StockVO updateStockMessage( String startData, String overData);
    
	// ���ݹؼ��ַ�Χɸѡ��Ʊ
	public StockVO filterStockMessage(int i, String low, String high);
}
