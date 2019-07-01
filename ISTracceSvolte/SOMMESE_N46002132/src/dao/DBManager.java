package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
	
	protected static Connection connection;
	
	final protected static IConnectionFactory CONNECTION_FACTORY = new H2ConnectionFactory(); 
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
		        connection = CONNECTION_FACTORY.createConnection();
		    } catch(Exception e) {
		        e.printStackTrace();
	        }
		}
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
}
