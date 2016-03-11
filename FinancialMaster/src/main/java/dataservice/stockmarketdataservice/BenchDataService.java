package dataservice.stockmarketdataservice;

import java.util.ArrayList;

import PO.benchmarkPO;
import PO.benchmarkStatisticPO;
import PO.fieldStatisticPO;

public interface BenchDataService {
	//��ȡ���п��õ�benchmark������ָ����Ŀǰֻ�л���300ָ��
	public benchmarkPO getBenchmark();
	
	//��ȡָ������ָ��������,start:��ʼʱ��,��ʽ YYYY-mm-dd;end: ����ʱ��,��ʽ YYYY-mm-dd
	//ʱ�䣬����ָ��������ָ��
	public ArrayList<benchmarkStatisticPO> getStatisticOfBenchmark (String benchCode , String start,String end);
}
