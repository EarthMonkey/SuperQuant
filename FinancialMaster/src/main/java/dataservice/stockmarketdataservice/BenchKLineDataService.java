package dataservice.stockmarketdataservice;

import ENUM.ManageState;

public interface BenchKLineDataService {
    //��ȡ�������µ����ݣ����������ļ��е�����
	public ManageState update();
	//����k��ͼ����Ҫ������
	public String[][] getStatisticData(String kLine_enum);
}
