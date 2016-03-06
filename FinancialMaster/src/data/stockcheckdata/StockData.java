package data.stockcheckdata;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import PO.codeNamePO;
import PO.stockStatisticPO;
import dataservice.stockcheckdataservice.StockDataService;

public class StockData implements StockDataService{
	public codeNamePO getCodeName(int year,String exchange){
		   ArrayList<String> arrayList=new ArrayList<String>();
		   try {
			   String result=HttpRequest.sendGet("http://121.41.106.89:8010/api/stocks/", "year="+year+"&exchange="+exchange);
			   JSONObject jsonObject=new JSONObject(result);
			   JSONArray jsonArray=jsonObject.getJSONArray("data");
			   
			   for(int i=0;i<jsonArray.length();i++){
				   arrayList.add(jsonArray.getJSONObject(i).getString("name"));
			   }
		   } catch (Exception e) {
		// TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   codeNamePO codeNamePO=new codeNamePO(arrayList);
		   return codeNamePO;
	}

	@Override
	public ArrayList<stockStatisticPO> getStatisitcOfStock(String codeName, String start, String end) {
		// TODO Auto-generated method stub
		ArrayList<stockStatisticPO> arrayList=new ArrayList<stockStatisticPO>();
		try {
			String result=HttpRequest.sendGet("http://121.41.106.89:8010/api/stock/"+codeName, "start="+start+"&end="+end+"&fields=open+high+close");
			JSONObject jsonObject=new JSONObject(result);
			JSONObject jsonObject2=jsonObject.getJSONObject("data");
			JSONArray jsonArray=jsonObject2.getJSONArray("trading_info");
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject3=jsonArray.getJSONObject(i);
				stockStatisticPO stockStatisticPO=new stockStatisticPO(jsonObject3.getString("date"), jsonObject3.getDouble("high"), jsonObject3.getDouble("open"), jsonObject3.getDouble("close"));
				arrayList.add(stockStatisticPO);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}
}