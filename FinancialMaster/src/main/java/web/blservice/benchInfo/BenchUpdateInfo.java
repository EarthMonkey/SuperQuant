package web.blservice.benchInfo;

import ENUM.ManageState;
import VO.BenchVO;

public interface BenchUpdateInfo {

	//���´��̵�������Ϣ
	public ManageState update(BenchVO benchVO,String benchName);
}
