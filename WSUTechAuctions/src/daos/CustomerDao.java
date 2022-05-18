package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Customer;

public class CustomerDao {
	
	Connection connection;

	public int registerCustomer(Customer customer) throws ClassNotFoundException {
		
		String INSERT_USERS_SQL = "INSERT INTO customer" +
		" (customerNickName, password, firstName, lastName, phoneNumber, email, address, postcode) VALUES " +
		" (?, ?, ?, ?, ?, ?, ?, ?);";
		
		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try(Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/auctiondb", "root", "Maximus77");
				
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			preparedStatement.setString(1, customer.getCustomerNickName());
			preparedStatement.setString(2, customer.getPassword());
			preparedStatement.setString(3, customer.getFirstName());
			preparedStatement.setString(4, customer.getLastName());
			preparedStatement.setString(5, customer.getPhoneNumber());
			preparedStatement.setString(6, customer.getEmail());
			preparedStatement.setString(7, customer.getAddress());
			preparedStatement.setString(8, customer.getPostcode());
			
			System.out.println(preparedStatement);
			
			result = preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}
}
