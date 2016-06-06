package PO;

public class industryPO {
	private String stockId;//��Ʊ����
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
	
	
	public industryPO(String stockI, double current_price, double rise_fall_price, double rise_fall_percent,
			double yesterday_close, double open, double high, double low, double inflows, double volume, double price,
			double turnover) {
		super();
		this.stockId = stockI;
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


	public void setStockId(String stockI) {
		this.stockId = stockI;
	}


	public double getCurrent_price() {
		return current_price;
	}


	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}


	public double getRise_fall_price() {
		return rise_fall_price;
	}


	public void setRise_fall_price(double rise_fall_price) {
		this.rise_fall_price = rise_fall_price;
	}


	public double getRise_fall_percent() {
		return rise_fall_percent;
	}


	public void setRise_fall_percent(double rise_fall_percent) {
		this.rise_fall_percent = rise_fall_percent;
	}


	public double getYesterday_close() {
		return yesterday_close;
	}


	public void setYesterday_close(double yesterday_close) {
		this.yesterday_close = yesterday_close;
	}


	public double getOpen() {
		return open;
	}


	public void setOpen(double open) {
		this.open = open;
	}


	public double getHigh() {
		return high;
	}


	public void setHigh(double high) {
		this.high = high;
	}


	public double getLow() {
		return low;
	}


	public void setLow(double low) {
		this.low = low;
	}


	public double getInflows() {
		return inflows;
	}


	public void setInflows(double inflows) {
		this.inflows = inflows;
	}


	public double getVolume() {
		return volume;
	}


	public void setVolume(double volume) {
		this.volume = volume;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getTurnover() {
		return turnover;
	}


	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
	
	
}
