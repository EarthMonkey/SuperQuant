package servlet.strategy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;
import VO.MyStrategyVO;
import VO.UserVO;
import web.bl.StrategyHandle.GetStrategyImpl;
import web.blservice.StrategyHandleService.GetStrategyInfo;

/**
 * Servlet implementation class StrategyPageServlet
 */
@WebServlet("/StrategyPageServlet")
public class StrategyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StrategyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("MyStrategy", null);
		response.sendRedirect(request.getContextPath()+"/Web_Pages/StrategyPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strategyName=request.getParameter("StrategyName");
		UserVO userVO=(UserVO)request.getSession().getAttribute("User");
		GetStrategyInfo getStrategyInfo=new GetStrategyImpl();
		List<UserStrategy> strategies=getStrategyInfo.getStrategy(userVO.getUsername(), strategyName);
		MyStrategyVO myStrategyVO=new MyStrategyVO();
		UserStrategy userStrategy=strategies.get(0);
		UserStrategyId userStrategyId=userStrategy.getId();
		String name=userStrategyId.getStrategyName();//������
	    String totalcost=userStrategy.getCost()+"";//�ܳɱ�
		String perST="";//��Ʊ���ơ�Ͷ�ʳɱ�����ʼ���ڡ��������ڡ�����Ƶ��
		String BuyList="";// �۸����䡢�ɽ������䡢���������䡢pe���䡢pb����
		String SoldList="";
		
		for (UserStrategy userStrategy2 : strategies) {
			userStrategyId=userStrategy2.getId();
			perST+=userStrategyId.getStockId()+","+userStrategy2.getWeight()+","+
			       userStrategy2.getStarttime()+","+userStrategy2.getEndtime()+","+
					userStrategy2.getFrequency()+";";
			BuyList+=userStrategy2.getBuystrategy()+";";
			SoldList+=userStrategy2.getSellstrategy()+";";
		}
		
		myStrategyVO.setName(name);
		myStrategyVO.setTotalcost(totalcost);
		myStrategyVO.setPerST(perST);
		myStrategyVO.setBuyList(BuyList);
		myStrategyVO.setSoldList(SoldList);
		request.getSession().setAttribute("MyStrategy", myStrategyVO);
	}

}
