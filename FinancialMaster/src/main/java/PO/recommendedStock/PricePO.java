package PO.recommendedStock;


//��������/�����뽵
public class PricePO {
	String stockId;//��Ʊ����
	String stockName;//��Ʊ��
	String uptodate;//���¼�
	String days;//������������/�����뽵����
	String stageRiseOrDown;//�׶��Ƿ�
	String exchange;//�ۼƻ�����
	String industry;//������ҵ
	public PricePO(String stockId, String stockName, String uptodate, String days, String stageRiseOrDown,
			String exchange, String industry) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.uptodate = uptodate;
		this.days = days;
		this.stageRiseOrDown = stageRiseOrDown;
		this.exchange = exchange;
		this.industry = industry;
	}
	public PricePO() {
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
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getStageRiseOrDown() {
		return stageRiseOrDown;
	}
	public void setStageRiseOrDown(String stageRiseOrDown) {
		this.stageRiseOrDown = stageRiseOrDown;
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
