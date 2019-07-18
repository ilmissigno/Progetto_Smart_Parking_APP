package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Terapia.Terapia;

public class DAO_Terapia {
	
	public static ArrayList<Terapia> readAll(){
		ArrayList<Terapia> listaTerapia= new ArrayList<Terapia>();
		Connection con= DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try{
			stat=con.prepareStatement("SELECT * FROM TERAPIA");
			rs=stat.executeQuery();
			while(rs.next()){
				listaTerapia.add(new Terapia(rs.getInt("ID_TERAPIA"),rs.getString("DESCRIZIONE"),rs.getDate("DATA_INIZIO"),rs.getDate("DATA_FINE")));
			}
			rs.close();
			stat.close();
			return listaTerapia;
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return null;
	}

}
