package PO.recommendedStock;

//���¸߻��ߴ��µ�
public class PeakPO {
	String stockId;//��Ʊ����
	String stockName;//��Ʊ��
	String riseOrDown;//�ǵ���
	String exchange;//������
	String uptodate;//���¼�
	String high;//ǰ�ڸߵ�/�͵�
	String date;//ǰ�ڸߵ�����/ǰ�ڵ͵�����
	public PeakPO(){
		
	}
	
	public PeakPO(String stockId, String stockName, String riseOrDown, String exchange, String uptodate, String high,
			String date) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.riseOrDown = riseOrDown;
		this.exchange = exchange;
		this.uptodate = uptodate;
		this.high = high;
		this.date = date;
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

	public String getUptodate() {
		return uptodate;
	}

	public void setUptodate(String uptodate) {
		this.uptodate = uptodate;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
