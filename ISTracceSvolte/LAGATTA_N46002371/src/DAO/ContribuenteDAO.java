package DAO;

import java.sql.*;
import coreBusiness.utenti.Contribuente;

public class ContribuenteDAO {
	public static Contribuente read(String codFis) throws SQLException{
		Connection conn= DBManager.getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT * FROM CONTRIBUENTI WHERE CODICEFISCALE=" + codFis);
		Contribuente contr=null;
		if(rs.first()){
			contr=new Contribuente(codFis);
			
			contr.setNome(rs.getString("NOME"));
			contr.setCognome(rs.getString("COGNOME"));
			contr.setDataNascita(rs.getString("DATANASCITA"));
			contr.setComune(rs.getString("COMUNE"));
			contr.setUsername(rs.getString("USERNAME"));
			contr.setPassword(rs.getString("PASSWORD"));
			contr.setStato(rs.getString("STATO"));
		}
		return contr;
	}
	
	public static void create(Contribuente c) throws SQLException{
		Connection conn=DBManager.getConnection();
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement("INSERTO INTO CONTRIBUENTI VALUES ?,?,?,?,?,?,?,?");
			pstmt.setString(1, c.getCodFis());
			pstmt.setString(2, c.getNome());
			pstmt.setString(3, c.getCognome());
			pstmt.setString(4, c.getDataNascita());
			pstmt.setString(5, c.getComune());
			pstmt.setString(6, c.getUsername());
			pstmt.setString(7, c.getPassword());
			pstmt.setString(8, c.getStato());
		}finally{
			if(pstmt!=null){
				pstmt.close();
			}
		}

	}
	
	public static Contribuente create(String cf) throws SQLException{
		Connection conn=DBManager.getConnection();
		PreparedStatement pstmt= conn.prepareStatement("INSERT INTO CONTRIBUENTI(CODICEFISCALE) VALUES ?");
		pstmt.setString(1, cf);
		pstmt.executeUpdate();
		pstmt.close();
		Contribuente contr=new Contribuente(cf);
		return contr;
	}

	public static void update(Contribuente c) throws SQLException{		
		Connection conn= DBManager.getConnection();
		PreparedStatement pstmt=conn.prepareStatement("UPDATE CONTRIBUENTI SET NOME=?,COGNOME=?,DATANASCITA=?,COMUNE=?,USERNAME=?,PASSWORD=?,STATO=? WHERE CODICEFISCALE=?");
		pstmt.setString(1, c.getNome());
		pstmt.setString(2, c.getCognome());
		pstmt.setString(3, c.getDataNascita());
		pstmt.setString(4, c.getComune());
		pstmt.setString(5, c.getUsername());
		pstmt.setString(6, c.getPassword());
		pstmt.setString(7, c.getStato());
		pstmt.setString(8, c.getCodFis());
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public static void delete(Contribuente c) throws SQLException{
		if(c.getCodFis()!=null){
			Connection conn=DBManager.getConnection();
			Statement stmt= conn.createStatement();
			stmt.executeUpdate("DELETE FROM CONTRIBUENTI WHERE ID="+c.getCodFis().toString());
			stmt.close();
		}
	}
	
}
