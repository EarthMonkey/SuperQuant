package businesslogicservice.stockmarketblservice;

import ENUM.date_enum;
import VO.StockMarketVO;

public interface StockMarketBLService {
	// ���ݹؼ��ֺ�ʱ�䷵�ص�ǰ���µĴ�����Ϣ
	public StockMarketVO getStockMarket(String key, date_enum date);
}
