package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	protected final static String dburl = "jdbc:h2:~/elettronica";
	protected static Connection connection;

	public static Connection getConnection() {
		try {
			if (connection == null) {
				Class.forName("org.h2.Driver");
				connection = DriverManager.getConnection(dburl, "sa", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
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