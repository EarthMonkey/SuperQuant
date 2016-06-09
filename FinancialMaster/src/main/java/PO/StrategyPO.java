package PO;

public class StrategyPO {
	private String stockId;// ��Ʊ����
	private String starttime;// ��ʼʱ��
	private String endtime;// ����ʱ��
	private double priceLow;// ��ͼ۸�
	private double priceHigh;//��߼۸�
	private double volumeLow;// ��ͳɽ���
	private double volumeHigh;//��߳ɽ���
	private double turnoverLow;//��ͻ�����
 	private double turnoverHigh;// ��߻�����
 	private double peLow;//�����ӯ��
	private double peHigh;// �����ӯ��
	private double pbLow;// ����о���
	private double pbHigh;//����о���
	private int frequency;//Ƶ��
	private double cost;//����ʱ���㻨�ѵĳɱ�
	
	
	public StrategyPO(String stockId, String starttime, String endtime, double priceLow, double priceHigh,
			double volumeLow, double volumeHigh, double turnoverLow, double turnoverHigh, double peLow, double peHigh,
			double pbLow, double pbHigh, int frequency, double cost) {
		super();
		this.stockId = stockId;
		this.starttime = starttime;
		this.endtime = endtime;
		this.priceLow = priceLow;
		this.priceHigh = priceHigh;
		this.volumeLow = volumeLow;
		this.volumeHigh = volumeHigh;
		this.turnoverLow = turnoverLow;
		this.turnoverHigh = turnoverHigh;
		this.peLow = peLow;
		this.peHigh = peHigh;
		this.pbLow = pbLow;
		this.pbHigh = pbHigh;
		this.frequency = frequency;
		this.cost = cost;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public double getPriceLow() {
		return priceLow;
	}
	public void setPriceLow(double priceLow) {
		this.priceLow = priceLow;
	}
	public double getPriceHigh() {
		return priceHigh;
	}
	public void setPriceHigh(double priceHigh) {
		this.priceHigh = priceHigh;
	}
	public double getVolumeLow() {
		return volumeLow;
	}
	public void setVolumeLow(double volumeLow) {
		this.volumeLow = volumeLow;
	}
	public double getVolumeHigh() {
		return volumeHigh;
	}
	public void setVolumeHigh(double volumeHigh) {
		this.volumeHigh = volumeHigh;
	}
	public double getTurnoverLow() {
		return turnoverLow;
	}
	public void setTurnoverLow(double turnoverLow) {
		this.turnoverLow = turnoverLow;
	}
	public double getTurnoverHigh() {
		return turnoverHigh;
	}
	public void setTurnoverHigh(double turnoverHigh) {
		this.turnoverHigh = turnoverHigh;
	}
	public double getPeLow() {
		return peLow;
	}
	public void setPeLow(double peLow) {
		this.peLow = peLow;
	}
	public double getPeHigh() {
		return peHigh;
	}
	public void setPeHigh(double peHigh) {
		this.peHigh = peHigh;
	}
	public double getPbLow() {
		return pbLow;
	}
	public void setPbLow(double pbLow) {
		this.pbLow = pbLow;
	}
	public double getPbHigh() {
		return pbHigh;
	}
	public void setPbHigh(double pbHigh) {
		this.pbHigh = pbHigh;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
