package web.blservice.businessInfo;

import VO.BusinessListVO;
import VO.BusinessVO;

public interface BusinessInfo {

	//����������ҵ��������Ϣ
	public BusinessListVO getBusinessList();
	
	//ͨ����ҵ�������ض�Ӧ��ҵ����Ϣ����ʷ����
	public BusinessVO getBusiness(String businessname);
}
