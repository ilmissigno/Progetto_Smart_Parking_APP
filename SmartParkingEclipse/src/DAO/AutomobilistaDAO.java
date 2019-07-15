package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutomobilistaDAO {
	
	
	
	public static String createAutomobilista() {
		return null;
		
	}
	
	public static String deleteAutomobilista() {
		return null;
		
	}
	
	public static String updateAutomobilista() {
		return null;
		
	}
	
	public static int readContoAutomobilista(TransactionManager tm, String username, String password) throws SQLException {
		int saldo = 0;
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM AUTOMOBILISTA WHERE ((USERNAME=?) (PASSWORD=?))")) {

			pt.setString(1, username);
			pt.setString(1, password);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					saldo = rs.getInt("SALDO");
				}
			}

			System.out.println("Salto residuo:" + saldo);
		}
		
		
		return saldo;
		
	}
	
}
