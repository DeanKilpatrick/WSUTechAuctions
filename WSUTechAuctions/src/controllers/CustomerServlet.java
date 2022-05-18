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

import daos.CustomerDao;
import models.Customer;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/Register")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private CustomerDao customerDao = new CustomerDao();
	
    public CustomerServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/customerRegister.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerNickName = request.getParameter("customerNickName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String postcode = request.getParameter("postcode");
		
		Customer customer = new Customer();
		customer.setCustomerNickName(customerNickName);
		customer.setPassword(password);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPhoneNumber(phoneNumber);
		customer.setEmail(email);
		customer.setAddress(address);
		customer.setPostcode(postcode);
		
		boolean validForm = false;
		
		
		try {
			if(checkForm(customerNickName)) {
				customerDao.registerCustomer(customer);
				validForm = true;
			}else {
				String errorMessage = "The Nickname you entered is already taken";
				request.setAttribute("errorMessage", errorMessage);
				request.getRequestDispatcher("/customerRegister.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(validForm) {
			HttpSession session = request.getSession();
			session.setAttribute("customerNickName", customerNickName);
			response.sendRedirect("Index");
		}else {
			response.sendRedirect("Register");
		}
	}
	
	public boolean checkForm(String user) throws ClassNotFoundException {
		Connection connection = null;
		String customerNickName = null;
		String SELECT_USERS_SQL = "SELECT customerNickName FROM customer where customerNickName='"+user+"'";
		boolean valid = true;
				
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
				customerNickName=rs.getString(1);			
			}
			statement.close();
			
			if(customerNickName.equals(user)) {
				valid = false;
			}
					
		}			
		catch(Exception e){
			System.out.println("Exception in verifyPassword()=" + e.toString());
			
		}
		return valid;
	}
}
