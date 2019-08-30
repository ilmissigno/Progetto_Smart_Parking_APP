package DAO;


//gh/jdbhwdw
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Corrispondenza;

public class CorrispondenzaDAO {
	//dopo penso alla lista ora penso ad una generica read
	
	private ArrayList<String> listaAuto;
	private String username;
	
	public CorrispondenzaDAO() {
		
	}
	
	public CorrispondenzaDAO(String Targa,String Username) {
		this.setListaAuto(new ArrayList<String>());
		this.addAuto(Targa);
		this.setUsername(Username);
	}
	
	public ArrayList<String> getListaAuto() {
		return listaAuto;
	}

	public void setListaAuto(ArrayList<String> listaAuto) {
		this.listaAuto = listaAuto;
	}
	
	public void addAuto(String t) {
		this.listaAuto.add(t);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean readCorrispondenza( TransactionManager tm, String Targa,String username) throws SQLException {
		tm.assertInTransaction();

		try (PreparedStatement ps = tm.getConnection()
				.prepareStatement("SELECT * FROM CORRISPONDENZA WHERE TARGA=? AND USERNAME=?")) {

			ps.setString(1, Targa);
			ps.setString(2, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next() == true) {
					this.addAuto(rs.getString("TARGA"));
					this.setUsername(rs.getString("USERNAME"));
					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	
	//@SuppressWarnings("finally")
	public ArrayList<String> readList(TransactionManager tm, String username) {
		tm.assertInTransaction();
		try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM Corrispondenza WHERE Username=?")) {
			ps.setString(1,username);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next() == true) {
					String Targa = rs.getString("Targa");
					this.addAuto(Targa);

				}
				return this.getListaAuto();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //finally {
			//return CorrispondenzaUtente;
		//}
		return this.getListaAuto();
		
		
		
		}
	

		public boolean createCorrispondenza(TransactionManager tm, String Targa) throws SQLException {
			tm.assertInTransaction();

			try (PreparedStatement ps = tm.getConnection()
					.prepareStatement("INSERT INTO corrispondenza(username,Targa) VALUES(?,?)")) {

				ps.setString(1, this.getUsername());
				ps.setString(2, Targa);
				if(ps.executeUpdate()==1) {
					return true;
				}else {
					return false;
				}
		}
		
	}

		public boolean deleteCorrispondenza(TransactionManager tm, String targa, String username) throws SQLException {
			tm.assertInTransaction();
			try (PreparedStatement ps = tm.getConnection()
					.prepareStatement("DELETE FROM corrispondenza WHERE Targa=? AND username=?")) {

				ps.setString(1, targa);
				ps.setString(2, username);

				if(ps.executeUpdate()==1) {
					return true;
				}else {
					return false;
				}
			}
		}
}
			

