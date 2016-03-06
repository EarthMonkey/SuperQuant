package VO;

public class StockVO {
//	���ڡ����̼ۡ���߼ۡ����̼ۡ���ͼۡ������������׽��
	String id;//����
	double opening_price;//���̼�
	double closing_price;//���̼�
	double max_price;//��߼�
	double min_price;//��ͼ�
	int trading_amout;//������
	double  trading_money;//���׽��
	String[][] history_data;//��ʷ����
	

	
	public StockVO(String id, double opening_price, double closing_price,
			double max_price, double min_price, int trading_amout,
			double trading_money, String[][] history_data) {
		super();
		this.id = id;
		this.opening_price = opening_price;
		this.closing_price = closing_price;
		this.max_price = max_price;
		this.min_price = min_price;
		this.trading_amout = trading_amout;
		this.trading_money = trading_money;
		this.history_data = history_data;
	}
	
	public String getId() {
		return id;
	}

	public double getOpening_price() {
		return opening_price;
	}

	public double getClosing_price() {
		return closing_price;
	}

	public double getMax_price() {
		return max_price;
	}

	public double getMin_price() {
		return min_price;
	}

	public int getTrading_amout() {
		return trading_amout;
	}

	public double getTrading_money() {
		return trading_money;
	}

	public String[][] getHistory_data() {
		return history_data;
	}
	public void setHistory_data(String[][] history_data) {
		this.history_data = history_data;
	}

}
