package businesslogicservice.stockmarketblservice;

import java.util.ArrayList;

import ENUM.date_enum;
import VO.StockMarketVO;

public interface StockMarketBLService {
	//�õ����п��õĴ���ָ����Ŀǰֻ�л���300ָ��(hs300)
	public ArrayList<String> getBenchmark();
	// ���ݴ���ָ����ʱ�䣨ö���ࣩ���ص�ǰ�Ĵ�����Ϣ��˳����ʱ�䡢���̼ۡ���߼ۡ���ͼۡ����̼ۣ�
	public StockMarketVO getStockMarket(String key, date_enum date);
}
