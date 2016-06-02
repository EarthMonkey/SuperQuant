package web.bl.businessImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import DAO.pojo.Industries;
import DAO.pojo.Stock;
import PO.industriesPO;
import PO.industryPO;
import VO.BusinessItemVO;
import VO.BusinessListVO;
import VO.BusinessVO;
import data.IndustryData.IndustryData;
import dataservice.IndustryDataService.IndustryDataService;
import servlet.factory.InitFactoryServlet;
import web.blservice.businessInfo.BusinessInfo;

public class BusinessImpl implements BusinessInfo {
	IndustryDataService industryDataService=new IndustryData();
	
	@Override
	public BusinessListVO getBusinessList() {
		ArrayList<industriesPO> industriesPOs=new ArrayList<industriesPO>();
		BusinessListVO businessListVO=new BusinessListVO();
		
		try {
			industriesPOs = industryDataService.getIndustryData();
			businessListVO.setBusinessList(industriesPOs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return businessListVO;
	}

	@Override
	public BusinessVO getBusiness(String businessname) {
		
		BusinessVO businessVO=new BusinessVO();
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String endtime=format.format(calendar.getTime());
		calendar.add(Calendar.MONTH, -1);
		String starttime=format.format(calendar.getTime());
		
		
		ArrayList<Industries> historyData=new ArrayList<Industries>();
		ArrayList<industryPO> industryPOs=new ArrayList<industryPO>();
		ArrayList<BusinessItemVO> businessItemVOs=new ArrayList<BusinessItemVO>();
		try {
			industryDataService.getIndustryDuringTime(businessname, starttime, endtime);
			industryPOs=industryDataService.getIndustry(businessname);
			businessVO.setHistoryData(historyData);
			
			for (industryPO company : industryPOs) {
				Stock stock=InitFactoryServlet.getStock(company.getStockI());
				BusinessItemVO businessItemVO=new BusinessItemVO(
						stock.getStockId(), stock.getStockName(),
						stock.getIndustry(), company.getCurrent_price(), 
						company.getRise_fall_price(), company.getRise_fall_percent(), 
						company.getYesterday_close(), company.getOpen(), 
						company.getHigh(), company.getLow(),
						company.getInflows(), company.getVolume(), 
						company.getPrice(), company.getTurnover());
				businessItemVOs.add(businessItemVO);
			}
			
			businessVO.setBusinessItemVOs(businessItemVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return businessVO;
	}

}
