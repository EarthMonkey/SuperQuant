package PO;

public class stockStatisticPO {
	private String name;//��Ʊ��
	private String date;//����
	private double open;//���̼�
	private double high;//��߼�
	private double low;//��ͼ�
	private double close;//���̼�
	private double adj_price;//��Ȩ��
	private int volume;//�ɽ���
	private double turnover;//������
	private double pe;//��ӯ��
	private double pb;//�о���
	
	public stockStatisticPO(String name,String date, double open, double high, double low,
			double close, double adj_price, int volume, double turnover,
			double pe, double pb) {
		super();
		this.name = name;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adj_price = adj_price;
		this.volume = volume;
		this.turnover = turnover;
		this.pe = pe;
		this.pb = pb;
	}
	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
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

	public double getClose() {
		return close;
	}

	public double getAdj_price() {
		return adj_price;
	}

	public int getVolume() {
		return volume;
	}

	public double getTurnover() {
		return turnover;
	}

	public double getPe() {
		return pe;
	}

	public double getPb() {
		return pb;
	}

	
}
