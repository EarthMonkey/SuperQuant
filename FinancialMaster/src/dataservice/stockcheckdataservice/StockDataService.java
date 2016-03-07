package dataservice.stockcheckdataservice;

import java.util.ArrayList;

import PO.codeNamePO;
import PO.stockStatisticPO;

public interface StockDataService {
	//�������й�Ʊ����
	public codeNamePO getCodeName(int year,String exchange);
	
	//���ݹ�Ʊ����codeName����ʼʱ��start����ֹʱ��end���ع�Ʊ��������
	public ArrayList<stockStatisticPO> getStatisitcOfStock(String codeName,String start,String end);
}
