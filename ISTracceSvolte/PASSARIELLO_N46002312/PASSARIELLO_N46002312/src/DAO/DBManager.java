package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	protected final static String dbPath = "~/test";
	protected final static String url = "jdbc:h2:" + dbPath;
	
	protected static Connection conn;
	
	public static Connection getConnection(){
		if (conn == null){
			try {
				conn = DriverManager.getConnection(url, "sa", "");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	public static void closeConnection() throws SQLException{
		if (conn != null){
			conn.close();
			conn = null;
		}
	}
}
