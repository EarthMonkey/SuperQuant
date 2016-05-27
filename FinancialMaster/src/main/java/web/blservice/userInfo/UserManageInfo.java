package web.blservice.userInfo;

import ENUM.ManageState;
import VO.StrategyVO;
import VO.UserVO;

public interface UserManageInfo {

	//���ӹ�ע�Ĺ�Ʊ
	public ManageState addStock(String code);
	
	//ɾ����ע�Ĺ�Ʊ
	public ManageState deleteStock(String code);
	
	//����һ������
	public ManageState addStrategy(StrategyVO sv);
	
	//ɾ��һ������
	public ManageState deleteStrategy(StrategyVO sv);
	
	//���µ�ǰ��¼�û��ĸ�����Ϣ
	public ManageState updateUser(UserVO user);
}
