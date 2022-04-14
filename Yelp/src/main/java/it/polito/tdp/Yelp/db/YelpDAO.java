package it.polito.tdp.Yelp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.Yelp.model.Business;

public class YelpDAO {
	
	public List<Business> readAllBusiness(){
		String sql = "SELECT * FROM Business";
		
		List<Business> result = new ArrayList<Business>();
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				result.add(new Business(rs.getString("business_id"),
						rs.getString("full_address"), rs.getString("active"), rs.getString("categories"),
						rs.getString("city"), rs.getInt("reviewcount"), rs.getString("business_name"), rs.getString("neighborhood"),
						rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("state"), rs.getDouble("stars")));	
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public double averageStars(Business business) {
		String sql = "SELECT AVG(stars) AS avg_stars, COUNT(*) AS number "
				+ "FROM reviews "
				+ "WHERE businessId=?";
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, business.getBusinessId());
			ResultSet rs = st.executeQuery();
			rs.first();
			double stars = rs.getDouble("avg_stars");
			conn.close();
			return stars;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

}
