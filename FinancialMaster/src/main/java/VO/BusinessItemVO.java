package VO;

public class BusinessItemVO {
	private String stockId;//��Ʊ����
	private String stockName;//��Ʊ����
	private String stockBusiness;//��Ʊ������ҵ
	private double current_price;//���¼�
	private double rise_fall_price;//�ǵ���
	private double rise_fall_percent;//�ǵ���
	private double yesterday_close;//��������
	private double open;//���̼�
	private double high;//��߼�
	private double low;//��ͼ�
	private double inflows;//�����루��
	private double volume;//�ɽ���
	private double price;//�ɽ���
	private double turnover;//������
	
	public BusinessItemVO(String stockId, String stockName, String stockBusiness, double current_price,
			double rise_fall_price, double rise_fall_percent, double yesterday_close, double open, double high,
			double low, double inflows, double volume, double price, double turnover) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.stockBusiness = stockBusiness;
		this.current_price = current_price;
		this.rise_fall_price = rise_fall_price;
		this.rise_fall_percent = rise_fall_percent;
		this.yesterday_close = yesterday_close;
		this.open = open;
		this.high = high;
		this.low = low;
		this.inflows = inflows;
		this.volume = volume;
		this.price = price;
		this.turnover = turnover;
	}
	public String getStockId() {
		return stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public String getStockBusiness() {
		return stockBusiness;
	}
	public double getCurrent_price() {
		return current_price;
	}
	public double getRise_fall_price() {
		return rise_fall_price;
	}
	public double getRise_fall_percent() {
		return rise_fall_percent;
	}
	public double getYesterday_close() {
		return yesterday_close;
	}
	public double getOpen() {
		return open;
	}
	public double getHigh() {
		return high;
	}
	public double getLow() {
		return low;
	}
	public double getInflows() {
		return inflows;
	}
	public double getVolume() {
		return volume;
	}
	public double getPrice() {
		return price;
	}
	public double getTurnover() {
		return turnover;
	}
	

}
