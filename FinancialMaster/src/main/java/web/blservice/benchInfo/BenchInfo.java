package web.blservice.benchInfo;

import VO.BenchListVO;
import VO.BenchVO;

public interface BenchInfo {

	//�������п��õĴ���ָ��
	public BenchListVO getBenchCode();
	
	//����ָ���Ĵ���ָ�����ض�Ӧ�Ĵ���VO
	public BenchVO getStockMarket(String benchCode);
	
}
