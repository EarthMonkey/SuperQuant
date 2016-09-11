package web.blservice.simulationInfo;

import java.util.ArrayList;

import ENUM.ManageState;
import VO.SimulationStrategyVO;

public interface SimulationStrategy {

	//����һ���ز���Եļ�¼
	public ManageState addSimulationStrategy(SimulationStrategyVO simulationStrategyVO);
	
	//ֹͣʹ��һ������
	public ManageState deleteSimulationStrategy(String id);
	
	//�õ������ű���������
	public ArrayList<SimulationStrategyVO> getAllSimulationStrategy(String userID);
	
	//����һ���ز�Ĳ��Ե�ӯ�����
	public double getResult(String id);
	
	//�õ�һ���ز���Ե���ϸӯ�����
	public String[][] getResultDetail(String id);
	
	
}
