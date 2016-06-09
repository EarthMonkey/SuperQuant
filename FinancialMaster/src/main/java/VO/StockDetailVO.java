package VO;

import PO.UpToDateStockPO;

public class StockDetailVO {
    //  ������Ϣ���������ݼ�PO
	private UpToDateStockPO upToDateMessage;
	
	//������������
	private  Analyze_BasicItemsVO analyze_BasicItemsVO;
	
	//������������
	private Analyze_TechnicalVO analyze_TechnicalVO;

   //	���ڡ����̼ۡ����̼ۡ���߼ۡ���ͼۡ���Ȩ�ۡ��ɽ����������ʡ���ӯ�ʡ��о���
	private String[][] historyData;
	
	//�����ǵ�����ʷ����
	private double[] rise_fallList;
	
    //�����ǵ�����ʷ����
    private double[] bench_rise_fallList;
		
	//������ҵ��ϢVO
	private BusinessVO businessVO;
	
	//�߼��ı��������
	private Analyze_ResultVO analyze_ResultVO;
	
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
	public double[] getRise_fallList() {
		return rise_fallList;
	}
	public void setRise_fallList(double[] rise_fallList) {
		this.rise_fallList = rise_fallList;
	}
	public BusinessVO getBusinessVO() {
		return businessVO;
	}
	public void setBusinessVO(BusinessVO businessVO) {
		this.businessVO = businessVO;
	}
	public Analyze_ResultVO getAnalyze_ResultVO() {
		return analyze_ResultVO;
	}
	public void setAnalyze_ResultVO(Analyze_ResultVO analyze_ResultVO) {
		this.analyze_ResultVO = analyze_ResultVO;
	}
	public double[] getBench_rise_fallList() {
		return bench_rise_fallList;
	}
	public void setBench_rise_fallList(double[] bench_rise_fallList) {
		this.bench_rise_fallList = bench_rise_fallList;
	}
	public Analyze_TechnicalVO getAnalyze_TechnicalVO() {
		return analyze_TechnicalVO;
	}
	public void setAnalyze_TechnicalVO(Analyze_TechnicalVO analyze_TechnicalVO) {
		this.analyze_TechnicalVO = analyze_TechnicalVO;
	}
	
	
}
