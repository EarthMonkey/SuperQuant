package dataservice.manageStockService;

import java.util.ArrayList;

import ENUM.ManageState;
import PO.ObservedStockPO;

public interface manageStockDataService {
	//��ȡ�ѱ��۲�Ĺ�Ʊ����
	public ArrayList<String> getCodeOfStock();
	
	//���ӹ۲�Ĺ�Ʊ,���ɹ�������Succeed,��֮������Fail������Ϊ�������
	public ManageState addStock(String code);
	
	//ɾ���ѹ۲�Ĺ�Ʊ,���ɹ�������Success,��֮������Fail������Ϊ�������
	public ManageState deleteStock(String code);
}
