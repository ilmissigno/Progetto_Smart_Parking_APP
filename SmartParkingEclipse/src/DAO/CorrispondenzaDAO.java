package DAO;


//gh/jdbhwdw
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Corrispondenza;

public class CorrispondenzaDAO {
	//dopo penso alla lista ora penso ad una generica read

	public boolean readCorrispondenza( TransactionManager tm, String Targa,String username) throws SQLException {
		String TargaAuto=null;
		tm.assertInTransaction();

		try (PreparedStatement ps = tm.getConnection()
				.prepareStatement("SELECT * FROM CORRISPONDENZA WHERE TARGA=? AND USERNAME=?")) {

			ps.setString(1, Targa);
			ps.setString(2, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next() == true) {
					TargaAuto = rs.getString("Targa");
					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	
	//@SuppressWarnings("finally")
	public static ArrayList<String> readList(TransactionManager tm, String username) {
		tm.assertInTransaction();
		Corrispondenza c = null;
		ArrayList<String> CorrispondenzaUtente = new ArrayList<String>();
		try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM Corrispondenza WHERE Username=?")) {
			ps.setString(1,username);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next() == true) {
					String Targa = rs.getString("Targa");
					CorrispondenzaUtente.add(Targa);

				}
return CorrispondenzaUtente;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //finally {
			//return CorrispondenzaUtente;
		//}
		return CorrispondenzaUtente;
		
		
		
		}
	

		public boolean createCorrispondenza(TransactionManager tm, String Targa,String username) throws SQLException {
			tm.assertInTransaction();

			try (PreparedStatement ps = tm.getConnection()
					.prepareStatement("INSERT INTO corrispondenza(username,Targa) VALUES(?,?)")) {

				ps.setString(1, username);
				ps.setString(2, Targa);
				if(ps.executeUpdate()==1) {
					return true;
				}else {
					return false;
				}
		}
		
	}
}
