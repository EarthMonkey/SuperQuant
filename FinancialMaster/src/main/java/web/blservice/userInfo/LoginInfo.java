package web.blservice.userInfo;

import ENUM.ManageState;
import VO.UserVO;

public interface LoginInfo {
	//ע�����û�.�������Succeed������ע��ɹ����������fail�������û����Ѵ���
	public ManageState add(UserVO user);
	
	//��¼.�������Succeed��������¼�ɹ����������fail�������û��������ڻ��������
	public ManageState login(UserVO user);
}
