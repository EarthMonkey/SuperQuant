package web.blservice.simulationInfo;

import java.util.ArrayList;

import ENUM.ManageState;
import VO.SimulationRecordVO;

public interface HistoryRecordInfo {

	//����һ����ʷӯ����¼
	public int addRecord(SimulationRecordVO simulationRecordVO);
	
	//����һ����ʷӯ����¼
	public ManageState hideRecord(String id);
	
	//ɾ��һ����ʷӯ����¼
	public ManageState deleteRecord(String id);
	
	//�õ�������ʷӯ����¼
	public ArrayList<SimulationRecordVO> getHistoryRecord(String userID);
	
	//�õ�������ʷ��¼�����ӯ�����
	public double getCalculateResult(String userID);
	
}
