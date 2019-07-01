package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Paziente.Paziente;

public class DAO_Paziente {
	
	public static ArrayList<Paziente> readAll(){
		ArrayList<Paziente> listaPaziente= new ArrayList<Paziente>();
		Connection con= DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try{
			stat=con.prepareStatement("SELECT * FROM PAZIENTE");
			rs=stat.executeQuery();
			while(rs.next()){
				listaPaziente.add(new Paziente(rs.getString("CODICE_FISCALE"),rs.getString("NOME"),rs.getString("COGNOME"),rs.getString("DOMICILIO")));
			}
			rs.close();
			stat.close();
			return listaPaziente;
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return null;
	}
}
