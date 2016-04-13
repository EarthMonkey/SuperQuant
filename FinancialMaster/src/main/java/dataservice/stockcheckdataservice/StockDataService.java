package dataservice.stockcheckdataservice;

import java.util.ArrayList;

import PO.codeNamePO;
import PO.stockStatisticPO;

public interface StockDataService {
	//�������й�Ʊ����
    //year��ѡ������е����й�Ʊ�б�
    //exchange����������Ŀǰ����Ϊ sz��sh������������Ͻ���
	public codeNamePO getCodeName(int year,String exchange);
	
	
	//���ݹ�Ʊ����codeName����ʼʱ��start����ֹʱ��end��ʱ���ʽ'YYYY-mm-dd'�����ع�Ʊ��������
	//�������ݰ������ڡ����̼ۡ���߼ۡ���ͼۡ����̼ۡ���Ȩ�ۡ��ɽ����������ʡ���ӯ�ʡ��о���
	public ArrayList<stockStatisticPO> getStatisitcOfStock(String codeName,String start,String end);
	
	//��ʼ��ʱ���õķ���
	public ArrayList<stockStatisticPO> getStatisitcOfStock(String codeName); 
}
