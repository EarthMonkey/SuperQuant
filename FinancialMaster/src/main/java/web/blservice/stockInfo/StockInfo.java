package web.blservice.stockInfo;

import ENUM.ManageState;
import VO.StockDetailVO;

public interface StockInfo {

	//���ݹ�Ʊid���ض�Ӧ�Ĺ�Ʊ��������Ϣ
	public StockDetailVO getStock(String code);
	
	//���ݹ�Ʊid���ض�Ӧ��Ʊ����ʱ��ͼ����
	public String[][] getTimeSharingData(String code);
	
	//������ֹʱ�䣬���¶�Ӧ��Ʊ��ʷ����
	public ManageState updateHistoryData(StockDetailVO sv,String startDate,String endDate);
	
	//����ɸѡ���������¶�Ӧ��ʷ����
	public ManageState filterHistoryData(StockDetailVO sv,int item,double lowerLimit,double upperLimit);
}
