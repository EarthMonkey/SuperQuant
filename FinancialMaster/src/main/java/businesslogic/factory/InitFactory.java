package businesslogic.factory;

import businesslogic.stockContrastbl.StockContrastBL;
import businesslogic.stockcheckbl.StockItemBL;
import businesslogic.stockcheckbl.StockListBL;
import businesslogic.stockcheckbl.StockSearchBL;
import businesslogic.stockmarketbl.MarketKLineBL;
import businesslogic.stockmarketbl.StockMarketBL;

public class InitFactory implements Runnable{

	private StockItemBL stockItemBL;//����Ա�--���а�
	private StockListBL stockListBL;//��Ʊ�б�
	private StockSearchBL stockSearchBL;//������
	private StockContrastBL stockContrastBL;//����Ա�--�״�ͼ
	private MarketKLineBL marketKLineBL;//���±�����k����k���ݣ���update��
	private static InitFactory factory = null;
	private StockMarketBL stockMarketBL;//��������
	private String id;
	private int i;
	
	private InitFactory() {
		stockItemBL =new StockItemBL();
		stockListBL =new StockListBL();
		stockSearchBL =new StockSearchBL();
		stockContrastBL =new StockContrastBL();
		marketKLineBL =new MarketKLineBL();
		marketKLineBL.update();
		stockMarketBL=new StockMarketBL();
	}
	
   
	public static InitFactory getFactory(){
		if (factory == null) {
			factory = new InitFactory();
		}
		return factory;
	}
	
	 //��ע��ȡ����עʱ���³�ʼ��
	public void update(String id,int i){


		stockListBL.update(id, i);
		this.id=id;
		this.i=i;
		Thread t = new Thread(this);
		t.start();
	}


	public StockItemBL getStockItemBL() {
		return stockItemBL;
	}


	public StockListBL getStockListBL() {
		return stockListBL;
	}


	public StockSearchBL getStockSearchBL() {
		return stockSearchBL;
	}


	public StockContrastBL getStockContrastBL() {
		return stockContrastBL;
	}


	public MarketKLineBL getMarketKLineBL() {
		return marketKLineBL;
	}


	public StockMarketBL getStockMarketBL() {
		return stockMarketBL;
	}


	@Override
	public void run() {
		stockContrastBL.update(id, i);
		stockItemBL.update(id, i);
		
	}
	
	
}
