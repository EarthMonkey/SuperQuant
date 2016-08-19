package servlet.stockcheck.TechnicalChart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import VO.StockDetailVO;

/**
 * Servlet implementation class MACDservlet
 */
@WebServlet("/MACDservlet")
public class MACDservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MACDservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		 String[][] historyData=sv.getHistoryData();
		 int length=historyData.length;
		 double close[]=new double[length];
		 for(int i=0;i<length;i++){
			
			 close[i]=Double.parseDouble(historyData[i][2]);
//			 System.out.println(close[i]);
		 }
		 double EMA12[]=getEMA(close,12);
		 double EMA26[]=getEMA(close,26);
		 
		 length=EMA26.length;
		 double DIFs[]=new double[length];
		 for (int i = 0; i < length; i++) {
			DIFs[i]=EMA12[i]-EMA26[i];
		 }
		 double DEAs[]=getEMA(DIFs, 9);
		 length=DEAs.length;
		 double DIFFs[]=new double[length];
		 for (int i = 0; i < length; i++) {
			DIFFs[i]=DIFs[i]-DEAs[i];
			System.out.println(DEAs);
		}
		String data="[";
		for (int i = length-1; i >=0; i--) {
			data=data+"{'date':"+historyData[i][0]+
					",'DIF':"+DIFs[i]+
					",'DEA':"+DEAs[i]+
					",'DIFF':"+DIFFs[i]+"},";
		}
		data+="]";
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private double[] getEMA(double[] close,int dayCount){
		int size=close.length-dayCount+1;
		double result[]=new double[size];
		for (int i = 0; i < size; i++) {
			double sum=0;
			for (int j = 0; j < dayCount; j++) {
				sum+=close[i+j];
			}
			result[i]=sum/dayCount;
		}
		return result;
	}
}
