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
				.prepareStatement("UPDATE conto c join automobilisti aut on c.IDconto=aut.Conto SET c.Saldo=c.Saldo-? WHERE aut.username=? AND aut.password=?")){
			pt.setDouble(1, CostoTotale);
			pt.setString(2, username);
			pt.setString(3, password);
			if(pt.executeUpdate()==1) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	
	public double readContoAutomobilista(TransactionManager tm, String username, String password) throws SQLException {
		double saldo = 0;
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM automobilisti aut join conto c on c.IDconto=aut.Conto where ((aut.Username=?) (aut.Password=?))")) {

			pt.setString(1, username);
			pt.setString(2, password);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					saldo = rs.getDouble("Saldo");
				}
			}

			System.out.println("Saldo residuo:" + saldo);
		}
		
		
		return saldo;
		
	}
	
	public String readCFAutomobilista(TransactionManager tm, String username, String password) throws SQLException {
	String CF="";
	tm.assertInTransaction();
	try (PreparedStatement pt = tm.getConnection()
			.prepareStatement("SELECT * FROM automobilisti WHERE ((Username=?) (Password=?))")) {

		pt.setString(1, username);
		pt.setString(2, password);
		try (ResultSet rs = pt.executeQuery()) {
			if (rs.next() == true) {
				CF = rs.getString("CF");
			}
		}
	return CF;
}
	}
}