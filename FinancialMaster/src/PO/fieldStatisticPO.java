package PO;

public class fieldStatisticPO {
	private String open;//���̼�	
	private String high;//��߼�
	private String low;//��ͼ�
	private String close;//���̼�
	private String adj_price;//��Ȩ��
	private String volume;//�ɽ���
	private String turnover;//������
	private String pe;//��ӯ��
	private String pb;//�о���
	public fieldStatisticPO(String open, String high, String low, String close, String adj_price, String volume,
			String turnover, String pe, String pb) {
		setOpen(open);
		setHigh(high);
		setLow(low);
		setClose(close);
		setAdj_price(adj_price);
		setVolume(volume);
		setTurnover(turnover);
		setPe(pe);
		setPb(pb);
	}
	public String getOpen() {
		return open;
	}
	private void setOpen(String open) {
		this.open = open;
	}
	public String getHigh() {
		return high;
	}
	private void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	private void setLow(String low) {
		this.low = low;
	}
	public String getClose() {
		return close;
	}
	private void setClose(String close) {
		this.close = close;
	}
	public String getAdj_price() {
		return adj_price;
	}
	private void setAdj_price(String adj_price) {
		this.adj_price = adj_price;
	}
	public String getVolume() {
		return volume;
	}
	private void setVolume(String volume) {
		this.volume = volume;
	}
	public String getTurnover() {
		return turnover;
	}
	private void setTurnover(String turnover) {
		this.turnover = turnover;
	}
	public String getPe() {
		return pe;
	}
	private void setPe(String pe) {
		this.pe = pe;
	}
	public String getPb() {
		return pb;
	}
	private void setPb(String pb) {
		this.pb = pb;
	}
	
}
