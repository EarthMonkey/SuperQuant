package servlet.factory;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import DAO.connection.DBconnection;
import DAO.pojo.Stock;
import PO.StockPO;
import data.Initialize.Init;
import web.bl.helperImpl.GetFirstCharImpl;
import web.bl.serchInfo.IdListImpl;
import web.blservice.helperInfo.GetFirstCharInfo;
import web.blservice.searchInfo.IdListInfo;


/**
 * Servlet implementation class InitFactory
 */
@WebServlet("/InitFactory")
public class InitFactoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String path="src/main/resources/";
	private static ArrayList<Stock> allIdList=new ArrayList<Stock>();; 
    private static ArrayList<StockPO> wholeIdList=new ArrayList<StockPO>();;   
	
    public InitFactoryServlet() {
        super();
    }

	@Override
	public void init() throws ServletException {
//		path=this.getServletContext().getRealPath("/")+"WEB-INF/classes/";
        //��ʼ������
		Init initData=new Init();
		//��ʼ�����й�Ʊid�б�
		IdListInfo idListInfo=new IdListImpl();
		allIdList=idListInfo.getIdList();
		GetFirstCharInfo getFirstCharInfo=new GetFirstCharImpl();
		for (Stock s:allIdList) {
			StockPO stockPO=new StockPO();
			stockPO.setStockId(s.getStockId());
			stockPO.setStockName(s.getStockName());
			stockPO.setIndustry(s.getIndustry());
			stockPO.setStockShortName(getFirstCharInfo.getFirstLetter(s.getStockName()));
			wholeIdList.add(stockPO);
		}
	}
	
	public static String getPath(){
		return path;
	}

	public static ArrayList<Stock> getSerchList(String key) {
		ArrayList<Stock> filterList = new ArrayList<Stock>();
		
		for (StockPO stock : wholeIdList) {
			if(stock.getStockId().contains(key)||stock.getStockName().contains(key)||stock.getStockShortName().contains(key.toLowerCase())){
				Stock s=new Stock();
				s.setStockId(stock.getStockId());
				s.setStockName(stock.getStockName());
				s.setIndustry(stock.getIndustry());
				filterList.add(s);
			}
		}
		return filterList;		
	}
	public static Stock getStock(String id){
		Stock result=null;
		for (Stock stock : allIdList) {
			if(stock.getStockId().equals(id)){
				result=stock;
				break;
			}
		}
		return result;
	}
	
	public static boolean isExist(String id){
		for (Stock stock : allIdList) {
			if(stock.getStockId().equals(id)){
				return true;
			}
		}
		return false;
	}
}
