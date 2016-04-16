package data.manageStockData;

import java.util.ArrayList;

import ENUM.ManageState;
import data.IO.FileManager;
import dataservice.manageStockService.manageStockDataService;

public class ManageStockData implements manageStockDataService{
	//�����Թ۲�����й�Ʊ�Ĵ���
	@Override
	public ArrayList<String> getCodeOfStock(){
		// TODO Auto-generated method stub
		try {
			ArrayList<String>  arrayList = FileManager.ReadFile("src/main/resources/Data/ObservedStock.txt");
			return arrayList;
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<String>();
		}

	}

	//����Ҫ�۲�Ĺ�Ʊ
	@Override
	public ManageState addStock(String code) {
		// TODO Auto-generated method stub
		try {
			ArrayList<String> arrayList=FileManager.ReadFile("src/main/resources/Data/ObservedStock.txt");
//			//���ڵ���10ʱ��Ч
//			if(arrayList.size()>9){
//				return ManageState.Fail;
//			}else{
				//���ظ�ʱ��Ч
				for (String string : arrayList) {
					if (string.equals(code)) {
						return ManageState.Fail;
					}
				}
				
				arrayList.add(code);
				FileManager.WriteFile(arrayList, "src/main/resources/Data/ObservedStock.txt", false);
				return ManageState.Succeed;
//			}
		} catch (Exception e) {
			return ManageState.Succeed;
		}
	}
	
	//ɾ���ѹ۲�Ĺ�Ʊ
	@Override
	public ManageState deleteStock(String code) {
		// TODO Auto-generated method stub
		try {
			//������ͬ�ģ�ɾ���������ʧ��
			ArrayList<String> arrayList=FileManager.ReadFile("src/main/resources/Data/ObservedStock.txt");
			for (int i=0;i<arrayList.size();i++) {
				if(arrayList.get(i).equals(code)){
					arrayList.remove(i);
					FileManager.WriteFile(arrayList, "src/main/resources/Data/ObservedStock.txt", false);
					return ManageState.Succeed;
				}
			}
			return ManageState.Fail;
		} catch (Exception e) {
			// TODO: handle exception
			return ManageState.Fail;
		}
	}
	
}
