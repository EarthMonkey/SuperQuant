package PO;

public class benchmarkStatisticPO {	
	private String date;//����
	private double close;//�ձP�r��Pָ��
	private double open;//�_�P�r��Pָ��
	private double low;//��ͼ�
	private double high;//��߼�
	private double adj_price;//�󸴼�Ȩ
	private double volume;//�ɽ���
	public benchmarkStatisticPO(String date, double close, double open, double low, double high, double adj_price,
			double volume) {
		super();
		this.date = date;
		this.close = close;
		this.open = open;
		this.low = low;
		this.high = high;
		this.adj_price = adj_price;
		this.volume = volume;
	}
	public String getDate() {
		return date;
	}
	public double getClose() {
		return close;
	}
	public double getOpen() {
		return open;
	}
	public double getLow() {
		return low;
	}
	public double getHigh() {
		return high;
	}
	public double getAdj_price() {
		return adj_price;
	}
	public double getVolume() {
		return volume;
	}
	
}
