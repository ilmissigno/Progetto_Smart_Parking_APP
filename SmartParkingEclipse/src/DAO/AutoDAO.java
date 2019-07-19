package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoDAO {

	public boolean createAuto(TransactionManager tm, String Targa,String CFProprietario,String username ) throws SQLException{
		//da implementare
		String query = "INSERT INTO AUTO(TARGA,CFPROPRIETARIO) VALUES(?,?)";
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement(query)) {
			pt.setString(1, Targa);
			pt.setString(2, CFProprietario);
			if(pt.executeUpdate()==1) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			throw e;
		}
	}
			

		
		
	
	
	
	public static String deleteAuto() {
		return null;
		
	}
	
	public static String updateAuto() {
		return null;
		
	}
	
	public boolean readAuto(TransactionManager tm,String Targa) throws SQLException{
		tm.assertInTransaction();
		try(PreparedStatement pt = tm.getConnection().prepareStatement("SELECT * FROM auto WHERE Targa=?")){
			pt.setString(1, Targa);
			try(ResultSet rs = pt.executeQuery()){
				if(rs.next()==true) {
					return true;
				}else {
					return false;
				}
			}
		}
	}
	
}
