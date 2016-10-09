package data.BenchData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import DAO.DAOimpl.BenchdataAccurateDaoImpl;
import DAO.dao.BenchdataAccurateDao;
import DAO.pojo.BenchdataAccurate;
import DAO.pojo.BenchdataAccurateId;
import PO.benchCurrentDataPO;

public class BenchdataAccurateUpdate implements Runnable{
	public static final String[] BenchId={"sh000001","sz399001","sh000300"};
	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
	BenchdataAccurateDao benchdataAccurateDao;
	
	public BenchdataAccurateUpdate(){
		Thread thread=new Thread(this);
		thread.start();
		benchdataAccurateDao=new BenchdataAccurateDaoImpl();
	}
	
	public void persist(){
		for (int i=0;i<3;i++) {
			
			benchCurrentDataPO bench=BenchRecordUpdate.getBenchCurrentDataPO(BenchId[i]);
			BenchdataAccurate benchdataAccurate=new BenchdataAccurate();
			BenchdataAccurateId benchdataAccurateId=new BenchdataAccurateId();
			benchdataAccurateId.setBenchId(BenchId[i]);
			benchdataAccurateId.setDate(new Date());
			benchdataAccurate.setId(benchdataAccurateId);
			benchdataAccurate.setPrice(bench.getNow());
			benchdataAccurateDao.persist(benchdataAccurate);
		}
	}
	
	@Override
	public void run() {
		while (true) {
			try {

				Thread.sleep(10000);
				if(BenchRecordUpdate.getStatus().equals("½»Ò×ÖÐ")){
					persist();
				}				
				Calendar calendar=Calendar.getInstance();
				int hour=calendar.get(Calendar.HOUR_OF_DAY);
				int minute=calendar.get(Calendar.MINUTE);
				if(hour==3&&minute==0){
					benchdataAccurateDao.clean();
					for (int i=0;i<3;i++) {						
						benchCurrentDataPO bench=BenchRecordUpdate.getBenchCurrentDataPO(BenchId[i]);
						BenchdataAccurate benchdataAccurate=new BenchdataAccurate();
						BenchdataAccurateId benchdataAccurateId=new BenchdataAccurateId();
						benchdataAccurateId.setBenchId(BenchId[i]);
						try {
							benchdataAccurateId.setDate(simpleDateFormat.parse(bench.getTime()));
						} catch (ParseException e) {
                            benchdataAccurateId.setDate(new Date());
						}
						benchdataAccurate.setId(benchdataAccurateId);
						benchdataAccurate.setPrice(bench.getNow());
						benchdataAccurateDao.persist(benchdataAccurate);
					}
					Thread.sleep(100000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
