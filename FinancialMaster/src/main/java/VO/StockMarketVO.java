package VO;

public class StockMarketVO {
     String[][] data;
     double volume;//�ɽ���    
     double changeRange;//�ǵ���
     double ups_and_downs;//�ǵ���
     double close;//���̼�

	public StockMarketVO(String[][] data, double volume, double changeRange,
			double ups_and_downs, double close) {
		super();
		this.data = data;
		this.volume = volume;
		this.changeRange = changeRange;
		this.ups_and_downs = ups_and_downs;
		this.close = close;
	}

//	public StockMarketVO(String[][] data) {
//		super();
//		this.data = data;
//	}
	
	public double getVolume() {
		return volume;
	}

	public double getChangeRange() {
		return changeRange;
	}

	public double getUps_and_downs() {
		return ups_and_downs;
	}

	public double getClose() {
		return close;
	}

	public String[][] getData() {
		return data;
	}
	public void setData(String[][] data) {
		this.data=data;
	}	
}
