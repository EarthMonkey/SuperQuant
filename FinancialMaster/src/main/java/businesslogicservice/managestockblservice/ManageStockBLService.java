package businesslogicservice.managestockblservice;

import ENUM.ManageState;
import ENUM.attentionState;

public interface ManageStockBLService {
	
	//�ж�ĳ֧��Ʊ�Ƿ񱻹�ע
	public attentionState isAttented(String id);
	
	// ��ӹ�ע�Ĺ�Ʊ
	public ManageState addStock(String id);

	// ɾ����ע�Ĺ�Ʊ
	public ManageState deleteStock(String id);
}
