package dataservice.stockmarketdataservice;

import java.util.ArrayList;

import ENUM.ManageState;
import PO.benchmarkStatisticPO;

public interface BenchKLineDataService {
    //��ȡ�������µ����ݣ����������ļ��е�����
	public ManageState update();
	//����k��ͼ����Ҫ������
	public ArrayList<benchmarkStatisticPO> getStatisticData(String kLine_enum);
}