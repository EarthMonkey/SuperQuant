package businesslogicservice.managestockblservice;

import ENUM.ManageState;

public interface ManageStockBLService {
	// ��ӹ�ע�Ĺ�Ʊ
	public ManageState addStock(String id);

	// ɾ����ע�Ĺ�Ʊ
	public ManageState deleteStock(String id);
}
