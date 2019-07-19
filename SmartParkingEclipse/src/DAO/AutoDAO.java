package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoDAO {

	public static boolean createAuto(TransactionManager tm, String Targa,String CFProprietario,String username ) throws SQLException{
		//da implementare
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("INSERT INTO auto(Targa,CFProprietario) VALUES(?,?)")) {
			pt.setString(1, Targa);
			pt.setString(2, CFProprietario);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					return true;
				}
				return false;
			}
		}
	}
			

		
		
	
	
	
	public static String deleteAuto() {
		return null;
		
	}
	
	public static String updateAuto() {
		return null;
		
	}
	
	public static boolean readAuto(TransactionManager tm,String Targa) throws SQLException{
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
