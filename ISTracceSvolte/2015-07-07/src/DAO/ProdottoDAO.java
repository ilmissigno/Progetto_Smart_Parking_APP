package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Boutique.exception.ProdottoNotFoundException;

public class ProdottoDAO {
	protected static java.util.Map<Integer, Boutique.Prodotto> restoredObjects = new java.util.HashMap<Integer, Boutique.Prodotto>();
	
	public static Boutique.Prodotto create(Integer id) throws SQLException {
		Connection conn=DBManager.getConnection();
		PreparedStatement pstmt= conn.prepareStatement("INSERT INTO PRODOTTI(ID) VALUES ?");
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		pstmt.close();
		Boutique.Prodotto p=new Boutique.Prodotto(id);
		restoredObjects.put(id, p);
		return p;
	}
	
	public static Boutique.Prodotto create() throws SQLException{
		Connection conn= DBManager.getConnection();
		Statement stmt= conn.createStatement();
		Integer id;
		try{
			stmt.executeUpdate("INSERT INTO PRODOTTI(NOME,ZONAGEOGRAFICA,PREZZO,QTA) VALUES ('','',0,0)");
			ResultSet rs= stmt.getGeneratedKeys();
			if(!rs.next()){
				throw new SQLException("ID non è stato generato");
			}
			id=rs.getInt(1);
			
		}finally{
			stmt.close();
		}
		Boutique.Prodotto p=new Boutique.Prodotto(id);
		restoredObjects.put(id, p);
		return p;
	}
	
	public static Boutique.Prodotto read(Integer id) throws SQLException,ProdottoNotFoundException{	
		if(id==null){
			throw new ProdottoNotFoundException();
		}
		
		if(restoredObjects.containsKey(id)){
			return restoredObjects.get(id);
		}
		
		Connection conn= DBManager.getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT * FROM PRODOTTI WHERE ID=" + id.toString());
		Boutique.Prodotto p=null;
		if(rs.first()){
			p=new Boutique.Prodotto(id);
			p.setNome(rs.getString("NOME"));
			p.setZonaGeografica(rs.getString("ZONAGEOGRAFICA"));
			p.setPrezzo(rs.getInt("PREZZO"));
			p.setQta(rs.getInt("QTA"));
			restoredObjects.put(id, p);
		}else{
			throw new ProdottoNotFoundException();
		}
		stmt.close();
		return p;
	}
	
	public static void update(Boutique.Prodotto p) throws SQLException{
		if(p.getId()==null){
			Boutique.Prodotto newProd=ProdottoDAO.create();
			p.setId(newProd.getId());
			restoredObjects.put(p.getId(), p);
		}
		
		Connection conn= DBManager.getConnection();
		PreparedStatement pstmt=conn.prepareStatement("UPDATE PRODOTTI SET NOME=?,ZONAGEOGRAFICA=?,PREZZO=?,QTA=? WHERE ID=?");
		pstmt.setString(1, p.getNome());
		pstmt.setString(2, p.getZonaGeografica());
		pstmt.setInt(3, p.getPrezzo());
		pstmt.setInt(4, p.getQta());
		pstmt.setInt(5, p.getId());
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public static void delete(Boutique.Prodotto p) throws SQLException{
		if(p.getId()!=null){
			Connection conn=DBManager.getConnection();
			Statement stmt= conn.createStatement();
			stmt.executeUpdate("DELETE FROM PRODOTTI WHERE ID="+p.getId().toString());
			stmt.close();
			restoredObjects.remove(p);
			p.setId(null);
		}	
	}
	
	public static ArrayList<Boutique.Prodotto> readAll() throws SQLException{
		Connection conn=DBManager.getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT * FROM PRODOTTI");
		ArrayList<Boutique.Prodotto> listaProdotti=new ArrayList<Boutique.Prodotto>();
		rs.beforeFirst();
		while(rs.next()){
			try{
				listaProdotti.add(new Boutique.Prodotto(rs.getString("NOME"), rs.getString("ZONAGEOGRAFICA"),
						rs.getInt("PREZZO"), rs.getInt("QTA"), rs.getInt("ID")));
			}catch(SQLException e){
				throw e;
			}
		}
		
		stmt.close();
		return listaProdotti;
	}
}
