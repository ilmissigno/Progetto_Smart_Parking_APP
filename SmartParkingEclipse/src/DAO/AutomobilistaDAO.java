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
	
	public boolean updateContoAutomobilista(TransactionManager tm, String username, String password, double CostoTotale) throws SQLException {
		tm.assertInTransaction();
		try(PreparedStatement pt = tm.getConnection()
				.prepareStatement("UPDATE AUTOMOBILISTI SET SALDO=SALDO-? WHERE USERNAME=? AND PASSWORD=?")){
			pt.setDouble(1, CostoTotale);
			pt.setString(2, username);
			pt.setString(3, password);
			try(ResultSet rs = pt.executeQuery()){
				if(rs.next()==true) {
					return true;
				}else {
					return false;
				}
			}
		}
	}
	
	
	public double readContoAutomobilista(TransactionManager tm, String username, String password) throws SQLException {
		double saldo = 0;
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM AUTOMOBILISTA WHERE ((USERNAME=?) (PASSWORD=?))")) {

			pt.setString(1, username);
			pt.setString(2, password);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					saldo = rs.getDouble("SALDO");
				}
			}

			System.out.println("Salto residuo:" + saldo);
		}
		
		
		return saldo;
		
	}
	
}
