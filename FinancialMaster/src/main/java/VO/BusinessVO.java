package VO;

import java.util.ArrayList;

import DAO.pojo.Industries;
import PO.industriesPO;
import PO.industryPO;

public class BusinessVO {
	//����ҵ������Ϣ
	private industriesPO uptodate_message;
	
	//����ҵ����ʷ����
	private ArrayList<Industries> historyData;
	
	//����ҵ�����Ĺ�˾�����µ�����
	private ArrayList<BusinessItemVO> businessItemVOs;

	public BusinessVO() {
		super();
	}

	public industriesPO getUptodate_message() {
		return uptodate_message;
	}

	public void setUptodate_message(industriesPO uptodate_message) {
		this.uptodate_message = uptodate_message;
	}

	public ArrayList<Industries> getHistoryData() {
		return historyData;
	}

	public void setHistoryData(ArrayList<Industries> historyData) {
		this.historyData = historyData;
	}

	public ArrayList<BusinessItemVO> getBusinessItemVOs() {
		return businessItemVOs;
	}

	public void setBusinessItemVOs(ArrayList<BusinessItemVO> businessItemVOs) {
		this.businessItemVOs = businessItemVOs;
	}


	
	
	
}
