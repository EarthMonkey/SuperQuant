package PO.recommendedStock;


//�������ǻ������µ�
public class ContinuingTrendPO {
	String stockId;//��Ʊ����
	String stockName;//��Ʊ��
	String uptodate;//���¼�
	String high;//��߼�
	String low;//��ͼ�
	String continuingDays;//��������
	String riseOrdown;//�����ǵ���
	String exchange;//�ۼƻ�����
	String industry;//������ҵ
	
	
	public ContinuingTrendPO() {
	}


	public ContinuingTrendPO(String stockId, String stockName, String uptodate, String high, String low,
			String continuingDays, String riseOrdown, String exchange, String industry) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.uptodate = uptodate;
		this.high = high;
		this.low = low;
		this.continuingDays = continuingDays;
		this.riseOrdown = riseOrdown;
		this.exchange = exchange;
		this.industry = industry;
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


	public String getHigh() {
		return high;
	}


	public void setHigh(String high) {
		this.high = high;
	}


	public String getLow() {
		return low;
	}


	public void setLow(String low) {
		this.low = low;
	}


	public String getContinuingDays() {
		return continuingDays;
	}


	public void setContinuingDays(String continuingDays) {
		this.continuingDays = continuingDays;
	}


	public String getRiseOrdown() {
		return riseOrdown;
	}


	public void setRiseOrdown(String riseOrdown) {
		this.riseOrdown = riseOrdown;
	}


	public String getExchange() {
		return exchange;
	}


	public void setExchange(String exchange) {
		this.exchange = exchange;
	}


	public String getIndustry() {
		return industry;
	}


	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	
}
