package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import models.AuctionItem;

public class AuctionItemDao {
	
	private DataSource dataSource;
	
	public AuctionItemDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<AuctionItem> list() throws SQLException {
		List<AuctionItem> auctionItems = new ArrayList<AuctionItem>();
		
		try(
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT itemID, itemName, price, description, start_time, end_time, bid FROM auction_items");
			ResultSet rs = statement.executeQuery();
		){
			while(rs.next()) {
				AuctionItem auctionItem = new AuctionItem();
				auctionItem.setItemID(rs.getInt("itemID"));
				auctionItem.setItemName(rs.getString("itemName"));
				auctionItem.setPrice(rs.getFloat("price"));
				auctionItem.setDescription(rs.getString("description"));
				auctionItem.setStartTime(rs.getDate("start_time"));
				auctionItem.setEndTime(rs.getDate("end_time"));
				auctionItem.setBid(rs.getFloat("bid"));
				auctionItems.add(auctionItem);
			}
		}
		return auctionItems;
	}
	public int addAuctionItem(AuctionItem auctionItem) throws ClassNotFoundException {
		
		String INSERT_AUCTION_ITEMS_SQL = "INSERT INTO auction_items" +
		" (itemName, price, description, start_time, end_time, bid) VALUES " +
		" (?, ?, ?, ?, ?, ?);";
		
		int result = 0;
		
		try(
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_AUCTION_ITEMS_SQL);		
			){
			//statement.setInt(1, auctionItem.getItemID());
			statement.setString(1, auctionItem.getItemName());
			statement.setFloat(2, auctionItem.getPrice());
			statement.setString(3, auctionItem.getDescription());
			statement.setDate(4, auctionItem.getStartTime());
			statement.setDate(5, auctionItem.getEndTime());
			statement.setFloat(6, auctionItem.getBid());
	
			System.out.println(statement);
			
			result = statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;	
	}
	public boolean updateBid(AuctionItem auctionItem) {
		boolean rowUpdated = false;
		
		try(
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE auction_items set bid = ? where itemId = ?;")	
			){
			statement.setFloat(1, auctionItem.getBid());
			statement.setInt(2, auctionItem.getItemID());
			
			rowUpdated= statement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowUpdated;
		
	}
	public AuctionItem getAuctionItem(int itemID) throws SQLException {
		AuctionItem auctionItem = new AuctionItem();
		
		try(
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT itemID, itemName, price, description, start_time, end_time, bid FROM auction_items WHERE itemId = " + itemID);
			ResultSet rs = statement.executeQuery();
			){
			while(rs.next()) {
				auctionItem.setItemID(rs.getInt("itemID"));
				auctionItem.setItemName(rs.getString("itemName"));
				auctionItem.setPrice(rs.getFloat("price"));
				auctionItem.setDescription(rs.getString("description"));
				auctionItem.setStartTime(rs.getDate("start_time"));
				auctionItem.setEndTime(rs.getDate("end_time"));
				auctionItem.setBid(rs.getFloat("bid"));
			}
		}
		return auctionItem;
	}
}
