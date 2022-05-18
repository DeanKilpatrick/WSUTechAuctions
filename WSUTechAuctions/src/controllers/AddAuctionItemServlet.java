package controllers;

import java.io.IOException;
import java.sql.Date;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import daos.AuctionItemDao;
import models.AuctionItem;

/**
 * Servlet implementation class AuctionItemServlet
 */
@WebServlet("/AddAuctionItem")
public class AddAuctionItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name="jdbc/auctiondb")
	DataSource dataSource;
    private AuctionItemDao auctionItemDao;
   
    
    @Override
    public void init() {
    	auctionItemDao = new AuctionItemDao(dataSource);
	}
    
    public AddAuctionItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("/addAuctionItem.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemName = request.getParameter("itemName");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		//String bid = request.getParameter("bid");
		
		AuctionItem auctionItem = new AuctionItem();
		auctionItem.setItemName(itemName);
		auctionItem.setPrice(Float.valueOf(price));
		auctionItem.setDescription(description);
		auctionItem.setStartTime(Date.valueOf(startTime));
		auctionItem.setEndTime(Date.valueOf(endTime));
		//auctionItem.setBid(Float.valueOf(bid));
		
		try {
			auctionItemDao.addAuctionItem(auctionItem);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
	}
}
