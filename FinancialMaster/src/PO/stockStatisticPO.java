package PO;

public class stockStatisticPO {
	private String date;//����
	private double high;//���ָ��
	private double open;//����ָ��
	private double close;//����ָ��
	public stockStatisticPO(String date, double high, double open, double close) {
		setDate(date);
		setHigh(high);
		setOpen(open);
		setClose(close);
	}
	public String getDate() {
		return date;
	}
	private void setDate(String date) {
		this.date = date;
	}
	public double getHigh() {
		return high;
	}
	private void setHigh(double high2) {
		this.high = high2;
	}
	public double getOpen() {
		return open;
	}
	private void setOpen(double open2) {
		this.open = open2;
	}
	public double getClose() {
		return close;
	}
	private void setClose(double close) {
		this.close = close;
	}
}
