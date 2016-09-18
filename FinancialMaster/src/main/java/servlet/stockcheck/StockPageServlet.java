package servlet.stockcheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import DAO.pojo.Stock;
import PO.recommendedStock.ContinuingQuantityPO;
import PO.recommendedStock.ContinuingTrendPO;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.PricePO;
import PO.recommendedStock.breakthroughPO;
import VO.StockDetailVO;
import VO.StockListVO;
import VO.UserVO;
import servlet.factory.InitFactoryServlet;
import web.bl.stockImpl.StockListImpl;
import web.blservice.stockInfo.StockListInfo;

/**
 * Servlet implementation class StockPageServlet
 */
@WebServlet("/StockPageServlet")
public class StockPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StockPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StockListVO stockListVO = new StockListVO();
		StockListInfo stockListInfo = new StockListImpl();
		stockListVO = stockListInfo.getStockList();
		request.getSession().setAttribute("StockList", stockListVO);
		response.sendRedirect(request.getContextPath() + "/Web_Pages/StockPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		String kind = request.getParameter("kind");
		// Peak ContinuingTrend ContinuingQuantity Breakthrough Price
		String kid_kind = request.getParameter("kidKind");
		// Up Down
		StockListInfo stockListInfo=new StockListImpl();
		String data = "[";

		if (kind.equals("Peak")) {
            
			ArrayList<PeakPO> peakPOs=new ArrayList<PeakPO>();
			data+="{'value':['��Ʊ����','��Ʊ��','�ǵ���','������','���¼�',";
							
			if (kid_kind.equals("Up")) {
				peakPOs=stockListInfo.getPeakUp();
				data+="'ǰ�ڸߵ�','ǰ�ڸߵ�����']},";
			} else {
				peakPOs=stockListInfo.getPeakDown();
				data+="'ǰ�ڵ͵�','ǰ�ڵ͵�����']},";
			}
			for (PeakPO peakPO : peakPOs) {
				data+="{'value':["+peakPO.getStockId()+","+peakPO.getStockName()+","+
						peakPO.getRiseOrDown()+","+peakPO.getExchange()+","+
						peakPO.getUptodate()+","+peakPO.getHigh()+","+
						peakPO.getDate()+"]},";
			}
		} else if (kind.equals("ContinuingTrend")) {

			ArrayList<ContinuingTrendPO> continuingTrendPOs=new ArrayList<ContinuingTrendPO>();
			data+="{'value':['��Ʊ����','��Ʊ��','���¼�','��߼�','��ͼ�',";
							
			if (kid_kind.equals("Up")) {
				continuingTrendPOs=stockListInfo.getContinuingTrendUp();
				data+="'��������','�����ǵ���','�ۼƻ�����','������ҵ']},";
			} else {
				continuingTrendPOs=stockListInfo.getContinuingTrendDown();
				data+="'��������','�����ǵ���','�ۼƻ�����','������ҵ']},";
			}

			for (ContinuingTrendPO continuingTrendPO : continuingTrendPOs) {
				data+="{'value':["+continuingTrendPO.getStockId()+","+continuingTrendPO.getStockName()+","+
						continuingTrendPO.getUptodate()+","+continuingTrendPO.getHigh()+","+
						continuingTrendPO.getLow()+","+continuingTrendPO.getContinuingDays()+","+
						continuingTrendPO.getRiseOrdown()+","+continuingTrendPO.getExchange()+","+
						continuingTrendPO.getIndustry()+"]},";
			}

		} else if (kind.equals("ContinuingQuantity")) {
			ArrayList<ContinuingQuantityPO> continuingQuantityPOs=new ArrayList<ContinuingQuantityPO>();
			
			data+="{'value':['��Ʊ����','��Ʊ��','�ǵ���','���¼�','�ɽ���','��׼�ճɽ���',";
							
			if (kid_kind.equals("Up")) {
				continuingQuantityPOs=stockListInfo.getContinuingQuantityUp();
				data+="'��������','�׶��ǵ���','������ҵ']},";
			} else {
				continuingQuantityPOs=stockListInfo.getContinuingQuantityDown();
				data+="'��������','�׶��ǵ���','������ҵ']},";
			}
			for (ContinuingQuantityPO continuingQuantityPO : continuingQuantityPOs) {
				data+="{'value':["+continuingQuantityPO.getStockId()+","+continuingQuantityPO.getStockName()+","+
						continuingQuantityPO.getRiseOrDown()+","+continuingQuantityPO.getUptodate()+","+
						continuingQuantityPO.getVolumn()+","+continuingQuantityPO.getBaseDateVolumn()+","+
						continuingQuantityPO.getDays()+","+continuingQuantityPO.getStageRiseOrDown()+","+
						continuingQuantityPO.getIndustry()+"]},";
			}

		} else if (kind.equals("Breakthrough")) {
			
			ArrayList<breakthroughPO> breakthroughPOs=new ArrayList<breakthroughPO>();
			data+="{'value':['��Ʊ����','��Ʊ��','���¼�','�ɽ���','�ɽ���','�ǵ���','������']},";
							
			if (kid_kind.equals("Up")) {
				breakthroughPOs=stockListInfo.getBreakthroughUp();
			} else {
				breakthroughPOs=stockListInfo.getBreakthroughDown();
			}
			for (breakthroughPO btPO : breakthroughPOs) {
				data+="{'value':["+btPO.getStockId()+","+btPO.getStockName()+","+
						btPO.getUptodate()+","+btPO.getPrice()+","+
						btPO.getVolumn()+","+btPO.getRiseOrDown()+","+
						btPO.getExchange()+"]},";
			}

		} else {
			ArrayList<PricePO> pricePOs=new ArrayList<PricePO>();
			data+="{'value':['��Ʊ����','��Ʊ��','���¼�',";							
			if (kid_kind.equals("Up")) {
				pricePOs=stockListInfo.getPriceUp();
				data+="'������������','�׶��Ƿ�','�ۼƻ�����','������ҵ']},";
			} else {
				pricePOs=stockListInfo.getPriceDown();
				data+="'�����뽵����','�׶��Ƿ�','�ۼƻ�����','������ҵ']},";
			}
			for (PricePO pricePO : pricePOs) {
				data+="{'value':["+pricePO.getStockId()+","+pricePO.getStockName()+","+
						pricePO.getUptodate()+","+pricePO.getDays()+","+
						pricePO.getStageRiseOrDown()+","+pricePO.getExchange()+","+
						pricePO.getIndustry()+"]},";
			}
		}

		data += "]";

		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
}
