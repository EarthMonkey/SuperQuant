package VO;

public class StockItemVO {
	private String stockname;//��Ʊ����
	private String code;//��Ʊ����
	private String item;//�������ֵ
	public StockItemVO(String stockname, String code, String item) {
		super();
		this.stockname = stockname;
		this.code = code;
		this.item = item;
	}
	public String getStockname() {
		return stockname;
	}
	public String getCode() {
		return code;
	}
	public String getItem() {
		return item;
	}
	
}
