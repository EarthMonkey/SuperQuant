package VO;

import PO.UpToDateStockPO;

public class StockDetailVO {
    //  ������Ϣ���������ݼ�PO
	private UpToDateStockPO upToDateMessage;
	
	//�������������
	private  Analyze_BasicItemsVO analyze_BasicItemsVO;

   //	���ڡ����̼ۡ����̼ۡ���߼ۡ���ͼۡ���Ȩ�ۡ��ɽ����������ʡ���ӯ�ʡ��о���
	private String[][] historyData;
	
	public StockDetailVO() {
		super();
	}
	public UpToDateStockPO getUpToDateMessage() {
		return upToDateMessage;
	}
	public void setUpToDateMessage(UpToDateStockPO upToDateMessage) {
		this.upToDateMessage = upToDateMessage;
	}
	public String[][] getHistoryData() {
		return historyData;
	}
	public void setHistoryData(String[][] historyData) {
		this.historyData = historyData;
	}
	public Analyze_BasicItemsVO getAnalyze_BasicItemsVO() {
		return analyze_BasicItemsVO;
	}
	public void setAnalyze_BasicItemsVO(Analyze_BasicItemsVO analyze_BasicItemsVO) {
		this.analyze_BasicItemsVO = analyze_BasicItemsVO;
	}

	
	
}
