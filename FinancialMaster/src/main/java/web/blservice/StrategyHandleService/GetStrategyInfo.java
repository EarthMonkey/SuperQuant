package web.blservice.StrategyHandleService;

import java.util.List;

import DAO.pojo.UserStrategy;

public interface GetStrategyInfo {

	//�����û����Ͳ��������õ���Ӧ�Ų�����Ϣ
	public List<UserStrategy> getStrategy(String userName,String StrategyName);
	
}
