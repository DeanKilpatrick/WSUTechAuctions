package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	boolean validatePassword;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/customerLogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String customerNickName = request.getParameter("customerNickName");
		String password = request.getParameter("password");
	
		try {
			validatePassword = checkPassword(customerNickName, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(validatePassword) {
			HttpSession session = request.getSession();
			session.setAttribute("customerNickName", customerNickName);
			response.sendRedirect("Index");
		}else {
			String errorMessage = "The password you entered was incorrect";
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/customerLogin.jsp").forward(request, response);
		}
	}
	
	public boolean checkPassword(String user, String password) throws ClassNotFoundException {
		Connection connection = null;
		String correctPassword = null;
		String SELECT_USERS_SQL = "SELECT password FROM customer where customerNickName='"+user+"'";
				
		Class.forName("com.mysql.jdbc.Driver");
		try {
		
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/auctiondb", "root", "Maximus77");
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
							
			Statement statement = connection.createStatement();
			statement.executeQuery(SELECT_USERS_SQL);
			ResultSet rs = statement.getResultSet();
						
			if(rs.next()) {
				correctPassword=rs.getString(1);			
			}
			statement.close();
			
			if(correctPassword.equals(password)) {
				return true;
			}
			else {
				return false;
			}
					
		}			
		catch(Exception e){
			System.out.println("Exception in verifyPassword()=" + e.toString());
			return false;
		}
	}
}
