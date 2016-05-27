package web.blservice.stockInfo;

import ENUM.ManageState;
import VO.StockVO;

public interface StockInfo {

	//���ݹ�Ʊid���ض�Ӧ�Ĺ�Ʊ��������Ϣ
	public StockVO getStock(String code);
	
	//������ֹʱ�䣬���¶�Ӧ��Ʊ��ʷ����
	public ManageState updateHistoryData(StockVO sv,String startDate,String endDate);
	
	//����ɸѡ���������¶�Ӧ��ʷ����
	public ManageState filterHistoryData(StockVO sv,int item,double lowerLimit,double upperLimit);
}
