package PO.recommendedStock;


//����ͻ��/����ͻ��
public class breakthroughPO {
	String stockId;//��Ʊ����
	String stockName;//��Ʊ��
	String uptodate;//���¼�
	String price;//�ɽ���
	String volumn;//�ɽ���
	String riseOrDown;//�ǵ���
	String exchange;//������
	public breakthroughPO(String stockId, String stockName, String uptodate, String price, String volumn,
			String riseOrDown, String exchange) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.uptodate = uptodate;
		this.price = price;
		this.volumn = volumn;
		this.riseOrDown = riseOrDown;
		this.exchange = exchange;
	}
	public breakthroughPO() {
		super();
	}
	
	
	
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getUptodate() {
		return uptodate;
	}
	public void setUptodate(String uptodate) {
		this.uptodate = uptodate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVolumn() {
		return volumn;
	}
	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}
	public String getRiseOrDown() {
		return riseOrDown;
	}
	public void setRiseOrDown(String riseOrDown) {
		this.riseOrDown = riseOrDown;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
	
}
