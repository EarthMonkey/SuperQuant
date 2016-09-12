package web.blservice.simulationInfo;

import java.util.ArrayList;

import ENUM.ManageState;
import VO.SimulationStockVO;

public interface SimulationStockInfo {

	
	//����һ���ֲֹ�Ʊ
	public int buyStock(SimulationStockVO simulationStockVO); 
	
	//����һ�����й�Ʊ����ϸӯ�����
	public String[][] getResuleDetail(String id);
	
	//����һ���ֲֹ�Ʊ
	public ManageState sellStock(String id);
	
	//�õ����гֲֹ�Ʊ
	public ArrayList<SimulationStockVO> getStockList(String userID);
}
