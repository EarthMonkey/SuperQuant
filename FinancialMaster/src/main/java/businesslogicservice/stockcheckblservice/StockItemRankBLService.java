package businesslogicservice.stockcheckblservice;

import java.util.ArrayList;

import VO.StockItemVO;

public interface StockItemRankBLService {
	//����string������ָ��鿴�ĵ������ݣ���ù۲��Ʊ�����У������޸�Ϊ"����string����������й��е�ǰʮ"��
	public ArrayList<StockItemVO> getRank();
}
