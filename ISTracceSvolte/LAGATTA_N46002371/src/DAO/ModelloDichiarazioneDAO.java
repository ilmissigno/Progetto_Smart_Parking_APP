package DAO;

import java.sql.*;

import coreBusiness.modelliDichiarazione.*;
import coreBusiness.utenti.*;

public class ModelloDichiarazioneDAO {
	
	public static ModelloDichiarazione restoreDichiarazione(ResultSet rs) throws SQLException{
		Integer id=rs.getInt("ID");
		String tipo=rs.getString("TIPO");
		Integer annoFiscale=rs.getInt("ANNOFISCALE");
		String stato=rs.getString("STATO");
		float redditoLavDip=rs.getFloat("REDDITOLAVORODIPENDENTE");
		String codFis=rs.getString("CONTRIBUENTE");
		Contribuente contr=new Contribuente();
		contr=ContribuenteDAO.read(codFis);
		float totPlusMinusValenza=rs.getFloat("TOTALEPLUSMINUSVALENZA");
		if(tipo.equals("modello 730")){
			ModelloDichiarazione md= new Modello730(annoFiscale,stato,redditoLavDip,contr);
			md.setId(id);
			return md;
		}else if(tipo.equals("modello unico")){
			ModelloDichiarazione md= new ModelloUnico(annoFiscale,stato,redditoLavDip,contr,totPlusMinusValenza);
			md.setId(id);
			return md;
		}
		return null;
	}
	public static ModelloDichiarazione read(String codFis,int annoFiscale) throws SQLException{
		Connection conn= DBManager.getConnection();
		PreparedStatement pstmt=conn.prepareStatement("SELECT * FROM MODELLI WHERE ANNOFISCALE=? AND CONTRIBUENTE=?");
		pstmt.setInt(1, annoFiscale);
		pstmt.setString(2, codFis);
		ResultSet rs= pstmt.executeQuery();
		ModelloDichiarazione md=null;
		if(rs.first()){
			md=restoreDichiarazione(rs);
		}
		pstmt.close();
		return md;
	}
}
