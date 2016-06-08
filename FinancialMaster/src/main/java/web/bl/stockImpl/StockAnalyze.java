package web.bl.stockImpl;

import java.util.ArrayList;

import PO.UpToDateStockPO;
import PO.industriesPO;
import VO.Analyze_BasicItemsVO;
import VO.Analyze_ResultVO;
import VO.BenchVO;
import VO.BusinessItemVO;
import VO.BusinessVO;
import VO.StockDetailVO;
import web.bl.benchImpl.BenchImpl;
import web.bl.businessImpl.BusinessImpl;
import web.blservice.benchInfo.BenchInfo;
import web.blservice.businessInfo.BusinessInfo;

public class StockAnalyze {

	/**
	 * 
	 * �ۺϷ���ģ�� �ۺ������巽�棬�����ܵ�������÷�
	 * 
	 */
	public void comprehensiveAnalyze(StockDetailVO stockDetailVO) {
		technicalAnalyze(stockDetailVO);
		benchAnalyze(stockDetailVO);
		businessAnalyze(stockDetailVO);
		basicAnalyze(stockDetailVO);
		inflowsAnalyze(stockDetailVO);
	}

	/**
	 * ��������ģ�飨��k��ͼ������ͼ���ɽ�����RSI��
	 * 
	 * ��k��ͼ�� ���³ɽ�����֧���ߺ������߱Ƚ� 
	 * ����ͼ�����ƶ�ƽ���ߣ���Ҫ��MA10��MA30��
	 * ��֤ȯ�۸����ǣ��������ƶ�ƽ���ߣ�����������źš�(����ά������) ��֤ȯ�۸��µ����������ƶ�ƽ���ߣ�����������źš�
	 * -�����ƶ�ƽ���ߴ�������ͻ���г����ƶ�ƽ���ߣ��γɻƽ𽻲棬Ԥʾ�ɼ۽����ǣ�
	 * -�����ƶ�ƽ���ߴ������µ����г����ƶ�ƽ���ߣ��γ�����������Ԥʾ�ɼ۽��µ���
	 * -��������������ȶ��ڣ�5�ա�10�ա�30���ƶ�ƽ���ߴ��϶�������˳�����У������Ϸ��ƶ�����Ϊ��ͷ���С�Ԥʾ�ɼ۽�������ǡ�
	 * -���µ������У�5�ա�10�ա�30���ƶ�ƽ�������¶�������˳�����У������·��ƶ�����Ϊ��ͷ���У�Ԥʾ�ɼ۽�����µ���
	 * -�ƶ�ƽ����������תΪ�½�������ߵ㣬�����½�תΪ����������͵�ʱ�����ƶ�ƽ���ߵ�ת�۵㡣Ԥʾ�ɼ����ƽ�������ת��
	 * -��MA10�������ƶ��������·���������ʱ��MA30ȴ�������Ϸ��ƶ�����ʾ�˶��µ��Ƕ�ͷ�г��ļ����Իص������Ʋ�δ������
	 *  �ɽ�����
	 * ���³ɽ�����MA5��MA10�Ƚϣ��жϽ����г�ή�����ǻ�Ծ��
	 *  RSI�������ǿ��ָ����Ŀǰ��RSI6��RSI12��RSI24��
	 * ����һ��ʱ�������ǵ������ǵ�����֮�͵ı�����������һ�ּ������ߡ��ܹ���ӳ���г���һ��ʱ���ڵľ����̶ȣ��ʺ������߲�۲�����
	 * �򻯼��㹫ʽ��100��n�������м�������֮�͡�(n�������м�������֮��+n�������м��µ���֮�ͣ��� - ���㼰�׵� 70 ��30
	 * ͨ��Ϊ���򼰳���Ѷ�š� RSIֵ�г�����Ͷ�ʲ��� 80��100��ǿ���� 50��80ǿ���� 20��50������ 0��20��������
	 * ����RSI��20����ˮƽ���������Ͻ��泤��RSI��Ϊ����źš� ����RSI��80����ˮƽ���������½��泤��RSI��Ϊ�����źš�
	 * �۸�һ����һ���ͣ��෴�ģ�RSIȴһ����һ����ʱ���۸�����׷�ת���ǡ� �۸�һ����һ���ߣ��෴�ģ�RSIȴһ����һ����ʱ���۸�����׷�ת�µ���
	 * RSI��50����Ϊ��������50����Ϊǿ������ ��������ͻ��50��Ϊ����תǿ����������ͻ��50��Ϊ��ǿת���� һ����ΪRSI��50����׼ȷ�Խϸߡ�
	 * 
	 */
	public void technicalAnalyze(StockDetailVO stockDetailVO) {
	}

	/**
	 * ���̶Աȷ���ģ�� һ���ǵ�����ʷ��������ͼ�Ƚ� �������������ˮƽ�Ƚϣ��ǵ��ʡ������ʡ�����
	 * 
	 */

	public double[] benchAnalyze(StockDetailVO stockDetailVO) {
		//    ������7��/����7��/����6��
		double score[] = {4,3,3};
		// �õ�����300������ʷ����
		BenchInfo benchInfo = new BenchImpl();
		BenchVO benchVO = benchInfo.getStockMarket("����300");

		double[] rise_falls = stockDetailVO.getRise_fallList();
		int size = rise_falls.length;
		double[] bench_close = new double[size + 1];
		double[] bench_rise_falls = new double[size];
		String[][] historyData = benchVO.getData();
		for (int i = 0; i < size + 1; i++) {
			bench_close[i] = Double.parseDouble(historyData[i][4]);
		}
		for (int i = 0; i < size; i++) {
			bench_rise_falls[i] = (bench_close[i] - bench_close[i + 1]) / bench_close[i + 1];
		}
		stockDetailVO.setBench_rise_fallList(bench_rise_falls);

		UpToDateStockPO upToDateStockPO = stockDetailVO.getUpToDateMessage();
		Analyze_BasicItemsVO analyze_BasicItemsVO = stockDetailVO.getAnalyze_BasicItemsVO();



		String result = "���ڱ���������";
		if (analyze_BasicItemsVO.getUps_and_downs() > benchVO.getRise_fall_percent()) {
			result += "�������ڴ����������ơ�\n";
			score[0]+=3;
		}else{
			result += "�������ڴ����������ơ�\n";
			score[0]-=3;
		}
				
		double stock_avg = 0;
		double bench_avg = 0;
		int higher=0;
		int length = size / 6;

		for (int i = 0; i < length; i++) {
			if(rise_falls[i]>bench_rise_falls[i]){
				higher++;
			}
			stock_avg += rise_falls[i];
			bench_avg += bench_rise_falls[i];
		}
		stock_avg /= length;
		bench_avg /= length;
		
		result = "���ڱ���������";
		if(higher>=length/2){
			result += "�Ƿ��������ڴ��̣�";
			score[1]+=2;
		}else{
			result += "�Ƿ��������ڴ��̣�";
			score[1]-=2;
		}
		if (analyze_BasicItemsVO.getUps_and_downs() > benchVO.getRise_fall_percent()) {
			result += "�����������ڴ��̡�\n";
			score[1]+=2;
		}else{
			result += "�����������ڴ��̡�\n";
			score[1]-=2;
		}
		
		stock_avg = 0;
		bench_avg = 0;
		higher=0;

		for (int i = 0; i < size; i++) {
			if(rise_falls[i]>bench_rise_falls[i]){
				higher++;
			}
			stock_avg += rise_falls[i];
			bench_avg += bench_rise_falls[i];
		}
		stock_avg /= size;
		bench_avg /= size;
		
		result = "���ڱ���������";
		if(higher>=size/2){
			result += "�Ƿ��������ڴ��̣�";
			score[2]+=2;
		}else{
			result += "�Ƿ��������ڴ��̣�";
			score[2]-=2;
		}
		if (analyze_BasicItemsVO.getUps_and_downs() > benchVO.getRise_fall_percent()) {
			result += "�����������ڴ��̡�\n";
			score[2]+=1;
		}else{
			result += "�����������ڴ��̡�\n";
			score[2]-=1;
		}
		
		Analyze_ResultVO analyze_ResultVO=stockDetailVO.getAnalyze_ResultVO();
		analyze_ResultVO.setResult_of_bench_analyze(result);
		double sumScore=score[0]+score[1]+score[2];
		analyze_ResultVO.setScore_of_bench_analyze(sumScore);
		
		return score;
	}

	/**
	 * ��ҵ�Աȷ���ģ�� �ԱȰ�����
	 *  һ������ҵ����ˮƽ�Աȣ�ƽ���۸��ǵ��ʡ������ʣ�
	 *  �����ǵ�����ͬ��ҵ���й�˾�е�����
	 *  ��������ҵ�ǵ�����ʷ��������ͼ�Ƚ�
	 * 
	 */
	public double[] businessAnalyze(StockDetailVO stockDetailVO) {
          //	    ������12��/����4��/����4��
		double score[] = {6,2,2};
		
		UpToDateStockPO upToDateMessage = stockDetailVO.getUpToDateMessage();
		Analyze_BasicItemsVO analyze_BasicItemsVO = stockDetailVO.getAnalyze_BasicItemsVO();

		// �õ�������ҵ��vo
		BusinessInfo businessInfo = new BusinessImpl();
		String businessName=upToDateMessage.getIndustry();
		BusinessVO businessVO = businessInfo.getBusiness(businessName);
		//��ҵ����������Ϣ
		industriesPO businessMessage=businessVO.getUptodate_message();
		//��ҵ���й�˾������Ϣ
		ArrayList<BusinessItemVO> businessItemVOs=businessVO.getBusinessItemVOs();
		int companyNum=businessItemVOs.size();
		double rank=0;
		for (BusinessItemVO businessItemVO : businessItemVOs) {
			rank++;
			if(businessItemVO.getStockId().equals(upToDateMessage.getStockId())){
				break;
			}
		}
		rank/=companyNum;
				
		// ����������ҵVO
		stockDetailVO.setBusinessVO(businessVO);
		
		//����ҵ����ˮƽ�Ա�
		String result = "���ڱ���������";
		if (analyze_BasicItemsVO.getUps_and_downs() > businessMessage.getRise_fall()) {
			result += "����������ҵ��������,";
			score[0]+=3;
		}else{
			result += "����������ҵ��������,";
			score[0]-=3;
		}
		
		result+="�Ƿ�����ͬ��ҵ������˾��λ��";
		score[0]+=rank*6-3;
		if (rank<0.33) {
			result += "ǰ�С�\n";
		}else if (rank>0.67) {
			result += "���С�\n";
		}else{
			result += "���Ρ�\n";
		}
		
		Analyze_ResultVO analyze_ResultVO=stockDetailVO.getAnalyze_ResultVO();
		analyze_ResultVO.setResult_of_business_analyze(result);
		double sumScore=score[0]+score[1]+score[2];
		analyze_ResultVO.setScore_of_business_analyze(sumScore);
		
		return score;
	}

	/**
	 * ��������ģ��
	 * 
	 * ����������� 
	 * �ɽ����ȶ��ԣ���30�������ճɽ��۱�׼��/��ֵ-------�����ԣ�Խ�ӽ�1��������ԽС
	 * ���ȣ���������---------Խ��������ʽ�Խ�࣬��Ծ��Խ�� 
	 * �����ʣ���10�������ջ����ʾ�ֵ-------Խ���Ծ��Խ��
	 *       ���в�����3%�Ļ�����������ƽ���в�����5%��ţ�в�����8%
	 * �ǵ��������¹ɼ��ǵ��� 
	 * �о��ʣ������о���---pb����Ʊ�۸���ÿ�ɾ��ʲ��ı�����Խ�͵Ĺ�Ʊ����ȫϵ��Խ�ߡ�ͨ��ԽС��Ͷ�ʼ�ֵԽ�ߡ�
	 *         ���м۵��������ֵʱ��Ҫ������ҵ��Ӫ����� 
	 * ��ӯ�ʣ�������ӯ��---pe����Ʊ�۸��ÿ������ı�����ԽСԽ���͹�������Ǳ�������ռ�Խ��
	 *  <0��ָ�ù�˾ӯ��Ϊ������ӯ��Ϊ����������ӯ��û�����壬����һ�������ʾΪ��������
	 *  0-13 ������ֵ���͹� 
	 *  14-20��������ˮƽ
	 *  21-28������ֵ���߹�
	 *  28+ ����ӳ���г���Ͷ������ĭ 
	 *  Ͷ�ʽ糣������ţ���Ƴ���ӯ�ʣ����������������о���˵��
	 * 
	 */

	public double basicAnalyze(StockDetailVO stockDetailVO) {
       
		Analyze_BasicItemsVO analyze_BasicItemsVO=stockDetailVO.getAnalyze_BasicItemsVO();
		double quantity_relative_ratio=analyze_BasicItemsVO.getQuantity_relative_ratio();  //����
		double priceStability=analyze_BasicItemsVO.getPriceStability();   //�ɼ��ȶ���
		double turnOver=analyze_BasicItemsVO.getTurnOver();   //������
		double ups_and_downs=analyze_BasicItemsVO.getUps_and_downs();  //�ǵ���
		double pe=analyze_BasicItemsVO.getPe();  //��ӯ��
		double pb=analyze_BasicItemsVO.getPb();  //�о���
		
		String result="�͸ù�Ʊ���ڱ����������۸񲨶�";
		if(priceStability>0.7){
			result+="��С��Ͷ�ʷ�����Խϵͣ�";			
		}else{
			result+="�ϴ�Ͷ�ʷ�����Խϸߣ�";	
		}
		if(turnOver>5){
			result+="����Ƶ�������ֻ�Ծ��";
		}else if(turnOver<2){
			result+="���ֲ��࣬���ֵ��ԡ�";
		}else{
			result+="����Ƶ��������";
		}
		
		result="\n��������Ϣ��ʾ�����ոù�";
		
		if(ups_and_downs>3){
			result+="�ɼ��������ԣ�";			
		}else if(ups_and_downs<-2){
			result+="�ɼ��µ����ԣ�";			
		}else {
			result+="�ɼ�����������";	
		}
		if(quantity_relative_ratio>1.5){
			result+="�����г���Ծ���ɽ������������";			
		}else if(quantity_relative_ratio<0.8){
			result+="�����г�ή�����ɽ�������½���";			
		}else {
			result+="�����г��������ɽ�����΢������";	
		}
		
		result="\n�͹�Ʊ��ֵ������";
		String[] pe_message={"��˾ӯ��Ϊ��","��Ʊ��ֵ���͹� ","��Ʊ��ֵ��������ˮƽ","��Ʊ��ֵ���߹�","���г���Ͷ������ĭ"};
		int index=(int) (pe/12);
		index++;
		if(index>4){
			index=4;
		}
		result+=pe_message[index];

		 result=";\n��Ͷ�ʼ�ֵ������";
		if(pb>5){
			result+="�ùɰ�ȫϵ���ͣ�Ͷ�ʼ�ֵ������Բ��١�";
		}else if(pb<1){
			result+="�����ƾ����󣬽���ο���ҵ��Ӫ����������ǡ�";
		}else{
			result+="�ùɰ�ȫϵ���ϸߣ��нϸ������ռ䡣";
		}
		Analyze_ResultVO analyze_ResultVO=stockDetailVO.getAnalyze_ResultVO();
		analyze_ResultVO.setResult_of_business_analyze(result);
		double sumScore=quantity_relative_ratio-4-index+pb+ups_and_downs+turnOver+priceStability*3;
		if(sumScore>20){
			sumScore=20;
		}
		analyze_ResultVO.setScore_of_business_analyze(sumScore);
		return sumScore;
	}

	/**
	 * �ʽ��������ģ��
	 * 
	 * DDX/DDY/DDZ����Խ��Խ��
	 * 
	 * DDXָ��ʵ����ָ�ľ��Ǵ󵥶���ͨ�����ǽ�ί�е��Ĵ�С�� ��ӳ��ͬ�ʽ�������Ͷ���ߵĽ��׷����ΪDDXָ�ꡣ
	 * DDXָ����һ����Level-2���𵥷���Ϊ�����Ķ����߼�˵ļ���ָ�ꡣ
	 * DDXָ������̬���ú���������ʾ��������ʾ���������ϴ�������ʾ���������ϴ� ͨ������£�DDXָ�귭��������ĺ�ʱ����
	 * 
	 * DDY����������ÿ���������������뵥���Ĳ�ռ�ֲ������ı���������ֵ���� DDY�ǵ������60��ƽ���ۼ�ֵ
	 *
	 * DDZ���󵥲��ָ���ɫ�ʴ���ʾ�˴��ʽ�����ǿ�ȣ�ɫ��Խ�� Խ�߱�ʾ����ǿ��Խ�� ���ʴ�ͻȻ���߷ſ�ʱ����Ԥʾ���߽��������ǡ�
	 * 
	 * ���
	 * 
	 * �ش󵥲����λ��%�����ش�����-�ش�����������ֵԽ��˵�����������Խ�࣬��ֵԽ��Խ�ã�
	 * �󵥲����λ��%����������-������������ֵԽ��˵���л�������Խ�࣬��ֵԽ��Խ�ã�
	 * �е������λ��%�����е�����-�е�����������ֵԽ��˵����ɢ������Խ�࣬��ֵԽ��Խ�ã�
	 * С�������λ��%����С������-С������������ֵԽ��˵��Сɢ������Խ�࣬��ֵԽ��Խ�ã�
	 */
	public double inflowsAnalyze(StockDetailVO stockDetailVO) {
		String result="�͸ù�Ʊ�ʽ�����������";
		UpToDateStockPO upToDateMessage = stockDetailVO.getUpToDateMessage();
		double ddx=upToDateMessage.getDdx();
		double ddy=upToDateMessage.getDdy();
		double ddz=upToDateMessage.getDdz();
		double gaps[]=new double[4];
		gaps[0]=upToDateMessage.getExtraGap();
		gaps[1]=upToDateMessage.getLargeGap();
		gaps[2]=upToDateMessage.getMediumGap();
		gaps[3]=upToDateMessage.getSmallGap();
		
		if(ddx>0.3){
			result+="���������ϴ󣬽������룻";
		}else if(ddx<-0.22){
			result+="���������ϴ󣬲��������룻";
		}
		if(ddz>10){
			result+="���ʽ�����ǿ�ȸߣ�Ԥ�ƶ��߽��������ǡ�";
		}else if(ddz<-0.22){
			result+="���ʽ�����ǿ�ȵͣ�Ԥ�ƶ��߽���ʼ�½���";
		}
		
		double score=ddx+ddy+ddz/5+gaps[0]+gaps[1]-gaps[2]-gaps[3];
		if(score<0){
			score=0;
		}else if(score>20){
			score=20;
		}
		Analyze_ResultVO analyze_ResultVO=stockDetailVO.getAnalyze_ResultVO();
		analyze_ResultVO.setResult_of_business_analyze(result);
		analyze_ResultVO.setScore_of_business_analyze(score);
		return score;
	}
}
