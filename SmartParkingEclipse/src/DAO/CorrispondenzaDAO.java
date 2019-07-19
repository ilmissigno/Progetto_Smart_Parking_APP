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
				.prepareStatement("Select * from  Corrispondenza c join automobilisti aut join auto automobile on c.Targa=automobile.Targa AND c.CF=aut.CF where c.Targa=? AND aut.Username=? ")) {

			ps.setString(1, Targa);
			ps.setString(2, username);

			ps.executeUpdate();
			tm.commitTransaction();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next() == true) {
					TargaAuto = rs.getString("Targa");
					return true;
				}
				}
		
		
		return false;
		}
	}
	
	//@SuppressWarnings("finally")
	public static ArrayList<Corrispondenza> readList(TransactionManager tm, String username) {
		tm.assertInTransaction();
		Corrispondenza c = null;
		ArrayList<Corrispondenza> CorrispondenzaUtente = new ArrayList<Corrispondenza>();
		try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM Corrispondenza WHERE Username=?")) {
			ps.setString(1,username);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next() == true) {
					String Targa = rs.getString("Targa");
					c = new Corrispondenza(username, Targa);
					CorrispondenzaUtente.add(c);

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
					.prepareStatement("INSERT INTO Corrispondenza(Targa,username) VALUES(?,?)")) {

				ps.setString(1, Targa);
				ps.setString(2, username);
				ps.executeUpdate();
				tm.commitTransaction();
				//non ho fatto una query di controllo poi si vede
				return true;
		}
		
	}
}
