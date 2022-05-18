package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import daos.AuctionItemDao;
import models.AuctionItem;

/**
 * Servlet implementation class BidServlet
 */
@WebServlet("/AuctionItem")
public class AuctionItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/auctiondb")
	DataSource dataSource;
    private AuctionItemDao auctionItemDao;
   
    
    @Override
    public void init() {
    	auctionItemDao = new AuctionItemDao(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String itemID = request.getParameter("itemID");
		try {
			AuctionItem auctionItem = auctionItemDao.getAuctionItem(Integer.valueOf(itemID));
			request.setAttribute("auctionItem", auctionItem);
			request.getRequestDispatcher("/auctionItem.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String itemID = request.getParameter("itemID");
		AuctionItem auctionItem = new AuctionItem();
		try {
			auctionItem = auctionItemDao.getAuctionItem(Integer.valueOf(itemID));
			request.setAttribute("auctionItem", auctionItem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String bid = request.getParameter("bid");
		String customerNickName = request.getParameter("customerNickName");
		 
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		Date currentDate = new Date(); 
		Date startDate = auctionItem.getStartTime(); 
		Date endDate = auctionItem.getEndTime();
		formatter.format(currentDate);
		formatter.format(startDate);
		formatter.format(endDate);

		if(!customerNickName.isBlank()) {
			if(currentDate.before(startDate)) {
				String errorMessage = "You can not place a bid before the start date";
				request.setAttribute("errorMessage", errorMessage);			
			}else if(currentDate.after(endDate)) {
				String errorMessage = "You can not place a bid after the end date";
				request.setAttribute("errorMessage", errorMessage);
			}else if(!bid.isBlank() && Float.valueOf(bid) > auctionItem.getBid()) {
				auctionItem.setItemID(Integer.valueOf(itemID));
				auctionItem.setBid(Float.valueOf(bid));
				
				auctionItemDao.updateBid(auctionItem);
				request.setAttribute("auctionItem", auctionItem);			
			}else{			
				String errorMessage = "Your bid must be higher than the previous bid";
				request.setAttribute("errorMessage", errorMessage);			
			}
		}else {
			String errorMessage = "You must be a registered user to bid";
			request.setAttribute("errorMessage", errorMessage);	
		}
		request.getRequestDispatcher("/auctionItem.jsp").forward(request, response);
	}
}
