package businesslogicservice.stockcheckblservice;

import java.util.ArrayList;

import VO.StockItemVO;

public interface StockItemRankBLService {
	// ����string������ָ��鿴�ĵ������ݣ���ù۲��Ʊ���ʱ�䣨һ�����ڣ������У������޸�Ϊ"����string����������й��е�ǰʮ"��
	// ����open,high,low,close,adj_price,volume,turnover,pe,pb
	// ���̼ۣ���߼ۣ���ͼۣ����̼ۣ���Ȩ�ۣ��ɽ����������ʣ���ӯ�ʣ��о���
	public ArrayList<StockItemVO> getRank(String item);
}
