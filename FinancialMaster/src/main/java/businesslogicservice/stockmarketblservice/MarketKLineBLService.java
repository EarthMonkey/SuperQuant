package businesslogicservice.stockmarketblservice;

import ENUM.ManageState;
import ENUM.marketKline_enum;
import VO.StockMarketVO;

public interface MarketKLineBLService {
    //��ȡ�������µ����ݣ����������ļ��е�����
	public ManageState update();
	//����k��ͼ����Ҫ������
	public StockMarketVO getData(marketKline_enum k);
}
