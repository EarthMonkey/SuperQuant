package data.StockData;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOimpl.TradeRecordAccurateDaoImpl;
import DAO.dao.TradeRecordAccurateDao;
import DAO.pojo.BenchdataAccurate;
import DAO.pojo.BenchdataAccurateId;
import DAO.pojo.TradeRecordAccurate;
import DAO.pojo.TradeRecordAccurateId;
import PO.UpToDateStockPO;
import PO.benchCurrentDataPO;
import data.BenchData.BenchRecordUpdate;

public class TradeRecordAccurateUpdate implements Runnable{
	public static final String[] UpToDateStocks={"http://www.shdjt.com/",".htm"};
	
	public static final String[] exchanges={"sh","sz","cy"};
	
	public TradeRecordAccurateDao tradeRecordAccurateDao;
	
	public TradeRecordAccurateUpdate(){
		tradeRecordAccurateDao=new TradeRecordAccurateDaoImpl();
		Thread thread=new Thread(this);
		thread.start();
	}
	
	public ArrayList<TradeRecordAccurate> getDatas(){
		
		
		
		ArrayList<TradeRecordAccurate> arrayList=new ArrayList<>();
		for (int i=0;i<3;i++) {
			
			ArrayList<UpToDateStockPO> list=UpToDateStocksUpdate.arrayLists[i];
			for (UpToDateStockPO upToDateStockPO : list) {
				TradeRecordAccurate tradeRecordAccurate=new TradeRecordAccurate();
				TradeRecordAccurateId tradeRecordAccurateId=new TradeRecordAccurateId();
				tradeRecordAccurateId.setStockId(upToDateStockPO.getStockId());
				tradeRecordAccurateId.setDate(new Date());
				tradeRecordAccurate.setId(tradeRecordAccurateId);
				tradeRecordAccurate.setPrice(upToDateStockPO.getNow());
				arrayList.add(tradeRecordAccurate);
			}

		}
		return arrayList;
	}
	@Override
	public void run() {
		while (true) {
			try {
				
				Thread.sleep(300000);	
			    
			    if(BenchRecordUpdate.getStatus().equals("½»Ò×ÖÐ")){
				   tradeRecordAccurateDao.persist(getDatas());
			    }								
				Calendar calendar=Calendar.getInstance();
				int hour=calendar.get(Calendar.HOUR_OF_DAY);
				int minute=calendar.get(Calendar.MINUTE);
				if(hour==3&&minute==0){
					tradeRecordAccurateDao.clean();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
