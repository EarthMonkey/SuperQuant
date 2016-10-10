package servlet.strategy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;
import PO.StrategyPO;
import PO.profitPO;
import VO.UserVO;
import web.bl.StrategyHandle.GetStrategyImpl;
import web.bl.StrategyHandle.PerfectStrategyHandle;
import web.bl.StrategyHandle.StrategyHandle;
import web.blservice.StrategyHandleService.GetStrategyInfo;
import web.blservice.StrategyHandleService.StrategyHandleService;
import web.blservice.StrategyHandleService.perfectStrategyService;

/**
 * Servlet implementation class PerfectStrategyServlet
 */
@WebServlet("/PerfectStrategyServlet")
public class PerfectStrategyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfectStrategyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		perfectStrategyService psService=new PerfectStrategyHandle();
		String stockId[]=request.getParameterValues("stockId[]");
		String startTime[]=request.getParameterValues("startTime[]");
		String endTime[]=request.getParameterValues("endTime[]");
		String cost[]=request.getParameterValues("cost[]");
		double profit=0;
		for(int i=0;i<stockId.length;i++){
		     ArrayList<profitPO> result=psService.getProfit(stockId[i], startTime[i], endTime[i], Double.parseDouble(cost[i]));
	        int size=result.size();
	        profitPO po=result.get(size-1);
	        profit+=po.getProfit();
		}
	    String data = "[{'profit':" +  profit + "}]";
		
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
