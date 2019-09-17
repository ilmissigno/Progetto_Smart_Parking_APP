package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Auto;

public class AutomobilistaDAO extends UtenteDAO{
	
	private double Credito;
	
	public AutomobilistaDAO() {
		
	}
	
	public AutomobilistaDAO(String username, String Password) {
		this.setUsername(username);
		this.setPassword(Password);
	}
	
	public double getCredito() {
		return Credito;
	}

	public void setCredito(double credito) {
		Credito = credito;
	}

	public static String createAutomobilista() {
		return null;
		
	}
	
	public static String deleteAutomobilista() {
		return null;
		
	}
	
	public boolean updateContoAutomobilista(TransactionManager tm, double CostoTotale) throws SQLException {
		tm.assertInTransaction();
		try(PreparedStatement pt = tm.getConnection()
				.prepareStatement("UPDATE automobilisti SET Credito=Credito-? WHERE Username=? AND Password=?")){
			pt.setDouble(1, CostoTotale);
			pt.setString(2, this.getUsername());
			pt.setString(3, this.getPassword());
			if(pt.executeUpdate()==1) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	
	public void readContoAutomobilista(TransactionManager tm, String username, String password) throws SQLException {
		
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM automobilisti WHERE Username=? AND Password=?")) {

			pt.setString(1, username);
			pt.setString(2, password);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					this.setUsername(username);
					this.setPassword(password);
					this.setCredito(rs.getDouble("Credito"));
				}
			}

			System.out.println("Saldo residuo:" + this.getCredito());
		}
		
	}
	
	public void readCFAutomobilista(TransactionManager tm, String username, String password) throws SQLException {
	tm.assertInTransaction();
	try (PreparedStatement pt = tm.getConnection()
			.prepareStatement("SELECT * FROM automobilisti WHERE ((Username=?) (Password=?))")) {

		pt.setString(1, username);
		pt.setString(2, password);
		try (ResultSet rs = pt.executeQuery()) {
			if (rs.next() == true) {
				this.setCodiceFiscale(rs.getString("CF"));
				this.setUsername(username);
				this.setPassword(password);
			}
		}
}
	}
	
	public void readAutomobilista(TransactionManager tm,String username,String password) throws SQLException  {
		// TODO Auto-generated method stub
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT * FROM automobilisti WHERE ((Username=?) AND (Password=?))")) {

			pt.setString(1, username);
			pt.setString(2, password);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					this.setCodiceFiscale(rs.getString("CF"));
					this.setCognome(rs.getString("Cognome"));
					this.setEmail(rs.getString("Email"));
					this.setNome(rs.getString("Nome"));
					this.setPassword(rs.getString("Password"));
					this.setUsername(rs.getString("Username"));
					this.setCredito(rs.getDouble("Credito"));
					
				}
			}
	}
	}
	
/*
		public void readAutomobilista(TransactionManager tm,String username) throws SQLException  {
	
			tm.assertInTransaction();
			try (PreparedStatement pt = tm.getConnection()
					.prepareStatement("SELECT * FROM automobilisti WHERE Username=?")) {

				pt.setString(1, username);
				try (ResultSet rs = pt.executeQuery()) {
					if (rs.next() == true) {
						this.setCodiceFiscale(rs.getString("CF"));
						this.setCognome(rs.getString("Cognome"));
						this.setEmail(rs.getString("Email"));
						this.setNome(rs.getString("Nome"));
						this.setPassword(rs.getString("Password"));
						this.setUsername(username);
						this.setCredito(rs.getDouble("Credito"));
						
					}
				}
		}
		
	}
		*/
	
	
	}
		
		
		
		
		
		
		
