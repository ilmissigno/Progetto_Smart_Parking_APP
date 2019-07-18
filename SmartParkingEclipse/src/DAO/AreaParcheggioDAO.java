package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AreaParcheggioDAO {

	
	public static String createAreaParcheggio() {
		return null;
		
	}
	
	public static String deleteAreaParcheggio() {
		return null;
		
	}
	
	public static String updateAreaParcheggio() {
		return null;
		
	}
	
	public double readAreaParcheggio(TransactionManager tm,String CodiceArea) throws SQLException{
		tm.assertInTransaction();
		try(PreparedStatement pt = tm.getConnection().prepareStatement("SELECT CostoOrario FROM areaparcheggio WHERE CodiceArea=?")){
			pt.setString(1, CodiceArea);
			try(ResultSet rs = pt.executeQuery()){
				if(rs.next()==true) {
					return rs.getDouble("CostoOrario");
				}
			}
		}
		return -1;
	}
	
}
