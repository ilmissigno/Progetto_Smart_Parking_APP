package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Paziente.Paziente;
import Terapia.InfoTerapia;
import Terapia.Terapia;

public class DAO_InfoTerapia {
	
	public static ArrayList<InfoTerapia> readAll(){
	ArrayList<InfoTerapia> listaInfoTerapia= new ArrayList<InfoTerapia>();
	Connection con= DBManager.getConnection();
	PreparedStatement stat=null;
	ResultSet rs=null;
	try{
		stat=con.prepareStatement("SELECT * FROM INFO_TERAPIA");
		rs=stat.executeQuery();
		while(rs.next()){
			listaInfoTerapia.add(new InfoTerapia(new Terapia(rs.getInt("ID_TERAPIA"),null,null,null),new Paziente(rs.getString("CF_PAZIENTE"),null,null,null)));
		}
		rs.close();
		stat.close();
		return listaInfoTerapia;
	}catch(SQLException e){
		e.printStackTrace();
	}
	return null;
	}
}
