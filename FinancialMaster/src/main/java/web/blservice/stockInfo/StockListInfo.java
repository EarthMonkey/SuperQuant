package web.blservice.stockInfo;

import java.util.ArrayList;

import PO.RiseStockPO;
import PO.recommendedStock.ContinuingQuantityPO;
import PO.recommendedStock.ContinuingTrendPO;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.PricePO;
import PO.recommendedStock.breakthroughPO;
import VO.StockListVO;

public interface StockListInfo {

	//�������Ź�Ʊ�б�
	public StockListVO getStockList();

	//���¸�
	public ArrayList<PeakPO> getPeakUp();
	
    //���µ�
	public ArrayList<PeakPO> getPeakDown();

	//��������
	public ArrayList<ContinuingTrendPO> getContinuingTrendUp();
    
	//�����µ�
	public ArrayList<ContinuingTrendPO> getContinuingTrendDown();

	//��������
	public ArrayList<ContinuingQuantityPO> getContinuingQuantityUp();

	//��������
	public ArrayList<ContinuingQuantityPO> getContinuingQuantityDown();

	//����ͻ��	
	public ArrayList<breakthroughPO> getBreakthroughUp();

	//����ͻ��
	public ArrayList<breakthroughPO> getBreakthroughDown();

	//��������
	public ArrayList<PricePO> getPriceUp();

	//�����뽵
	public ArrayList<PricePO> getPriceDown();
	
}
