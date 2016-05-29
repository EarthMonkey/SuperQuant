package web.blservice.userInfo;

import ENUM.ManageState;
import VO.StrategyVO;
import VO.UserVO;

public interface UserManageInfo {

	//���ӹ�ע�Ĺ�Ʊ
	public ManageState addStock(UserVO user,String code);
	
	//ɾ����ע�Ĺ�Ʊ
	public ManageState deleteStock(UserVO user,String code);
	
	//����һ������
	public ManageState addStrategy(UserVO user,StrategyVO sv);
	
	//ɾ��һ������
	public ManageState deleteStrategy(UserVO user,StrategyVO sv);
}
