package it.polito.tdp.Yelp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DBConnect {
	
	
	static private final String jdbcUrl = "jdbc:mysql://localhost/yelp";
	static private HikariDataSource ds = null;
	
	public static Connection getConnection() {
		
		if(ds==null)	//SINGLETON, PER NON CREARE PIU POOL DI CONNESIONI
		{
			//CREA NUOVO DATA SOURCE
			ds = new HikariDataSource();
			ds.setJdbcUrl(jdbcUrl);
			ds.setUsername("root");
			ds.setPassword("root");
		}
		try {
		return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot get a connection " + jdbcUrl, e);
		}
	}
	
}
