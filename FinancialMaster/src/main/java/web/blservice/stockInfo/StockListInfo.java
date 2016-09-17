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

	//返回热门股票列表
	public StockListVO getStockList();

	//创新高
	public ArrayList<PeakPO> getPeakUp();
	
    //创新低
	public ArrayList<PeakPO> getPeakDown();

	//连续上涨
	public ArrayList<ContinuingTrendPO> getContinuingTrendUp();
    
	//连续下跌
	public ArrayList<ContinuingTrendPO> getContinuingTrendDown();

	//持续放量
	public ArrayList<ContinuingQuantityPO> getContinuingQuantityUp();

	//持续缩量
	public ArrayList<ContinuingQuantityPO> getContinuingQuantityDown();

	//向上突破	
	public ArrayList<breakthroughPO> getBreakthroughUp();

	//向下突破
	public ArrayList<breakthroughPO> getBreakthroughDown();

	//量价齐升
	public ArrayList<PricePO> getPriceUp();

	//量价齐降
	public ArrayList<PricePO> getPriceDown();
	
}
