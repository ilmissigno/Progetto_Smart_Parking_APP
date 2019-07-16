package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoDAO {

	public static String createAuto() {
		return null;
		
	}
	
	public static String deleteAuto() {
		return null;
		
	}
	
	public static String updateAuto() {
		return null;
		
	}
	
	public boolean readAuto(TransactionManager tm,String Targa) throws SQLException{
		tm.assertInTransaction();
		try(PreparedStatement pt = tm.getConnection().prepareStatement("SELECT * FROM AUTO WHERE TARGA=?")){
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
