package PO;

public class benchmarkStatisticPO {	
	private String date;//����
	private double close;//�ձP�r��Pָ��
	private double open;//�_�P�r��Pָ��
	public String getDate() {
		return date;
	}
	private void setDate(String date) {
		this.date = date;
	}
	public double getClose() {
		return close;
	}
	private void setClose(double close) {
		this.close = close;
	}
	public double getOpen() {
		return open;
	}
	private void setOpen(double open) {
		this.open = open;
	}
	public benchmarkStatisticPO(String date, double close, double open) {
		setDate(date);
		setClose(close);
		setOpen(open);
	}
	
}
