package data.IndustryData;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import PO.industryPO;
import data.IO.HttpRequest;
import dataservice.IndustryDataService.IndustryDataService;

public class IndustryData implements IndustryDataService{
	public static final String[] string={"http://q.10jqka.com.cn/interface/stock/thshy/zdf/desc/","/quote/quote"};
	public static final int[] number={1,2};
	@Override
	public ArrayList<industryPO> getIndustryData() {
		ArrayList<industryPO> arrayList=new ArrayList<>();
		try {
			for(int i=0;i<number.length;i++){
				String url=string[0]+number[i]+string[1];
				String temp=HttpRequest.sendGet(url, "");
				System.out.println(temp);
				JSONObject jsonObject=new JSONObject(temp);
				
				JSONArray jsonArray=jsonObject.getJSONArray("data");
				for(int j=0;j<jsonArray.length();j++){
					JSONObject jObject=jsonArray.getJSONObject(j);
					industryPO industryPO=new industryPO(
							jObject.getString("platename"),
							Integer.parseInt(jObject.getString("num")),
							Double.parseDouble(jObject.getString("jj")), 
							Double.parseDouble(jObject.getString("zdf")), 
							Double.parseDouble(jObject.getString("cjl")), 
							Double.parseDouble(jObject.getString("cje")), 
							Double.parseDouble(jObject.getString("jlr")), 
							jObject.getString("gainername"), 
							Double.parseDouble(jObject.getString("gainerzxj")), 
							Double.parseDouble(jObject.getString("gainerzdf")));
					arrayList.add(industryPO);
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
		}

		return arrayList;
	}

}