package data.stockmarketdata;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import PO.benchmarkPO;
import PO.benchmarkStatisticPO;
import PO.fieldStatisticPO;
import data.stockcheckdata.HttpRequest;
import dataservice.stockcheckdataservice.BenchDataService;

public class BenchData implements BenchDataService{

	@Override
	public benchmarkPO getBenchmark() {
		// TODO Auto-generated method stub
		ArrayList<String> arrayList=new ArrayList<String>();
		try {
			String result=HttpRequest.sendGet("http://121.41.106.89:8010/api/benchmark/all", "");
			JSONObject jsonObject=new JSONObject(result);
			JSONArray jsonArray=jsonObject.getJSONArray("data");
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject2=jsonArray.getJSONObject(i);
				arrayList.add(jsonObject2.getString("name"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		benchmarkPO benchmarkPO=new benchmarkPO(arrayList);
		return benchmarkPO;
	}

	@Override
	public ArrayList<benchmarkStatisticPO> getStatisticOfBenchmark(String benchCode, String start, String end) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ArrayList<benchmarkStatisticPO> arrayList=new ArrayList<benchmarkStatisticPO>();
				try {
					String result=HttpRequest.sendGet("http://121.41.106.89:8010/api/benchmark/"+benchCode, "start="+start+"&end="+end+"&fields=open+close");
					JSONObject jsonObject=new JSONObject(result);
					JSONObject jsonObject2=jsonObject.getJSONObject("data");
					JSONArray jsonArray=jsonObject2.getJSONArray("trading_info");
					for(int i=0;i<jsonArray.length();i++){
						JSONObject jsonObject3=jsonArray.getJSONObject(i);
						benchmarkStatisticPO benchmarkStatisticPO=new benchmarkStatisticPO(jsonObject3.getString("date"), jsonObject3.getDouble("close"), jsonObject3.getDouble("open"));
						arrayList.add(benchmarkStatisticPO);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return arrayList;
	}

	
}