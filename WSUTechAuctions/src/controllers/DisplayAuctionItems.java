package controllers;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
 * Servlet implementation class DisplayAuctionItems
 */
@WebServlet("/DisplayAuctionItems")
public class DisplayAuctionItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/auctiondb")
	private DataSource dataSource;
	private AuctionItemDao auctionItemDao;
       
	
	@Override
	public void init() {
		auctionItemDao = new AuctionItemDao(dataSource);
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAuctionItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			List<AuctionItem> auctionItems = auctionItemDao.list();
			request.setAttribute("auctionItems", auctionItems);
			request.getRequestDispatcher("/displayAuctionItems.jsp").forward(request, response);
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
		doGet(request, response);
	}

}
