package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import coreBusiness.GestioneFumetti.Ordine;

public class OrdineDAO {

	static Ordine create(String cliente){
		Connection c = DBManager.getConnection();
		Ordine o = new Ordine();
		
		try(Statement stmt = c.createStatement()){
			
			stmt.executeUpdate("INSERT INTO ORDINI (CLIENTE) VALUES ('"+cliente+"')");
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next() == false){
				throw new SQLException("ID non autogenerato");
			}
			o.setId(rs.getInt(1));
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		if (o.getId() == null)
			o = null;
		return o;
	}
	
	static Ordine read(Integer id){
		if (id == null) return null;
		Connection c = DBManager.getConnection();
		Ordine o = null;
		try(Statement stmt = c.createStatement()){
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM ORDINI WHERE ID=" +id+";");
			if (rs.next() == false){
				throw new SQLException("ID non trovato");
			}
			o = new Ordine(rs.getString("CLIENTE"), rs.getString("BACKORDER"));
			o.setId(id);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return o;
	}
	
	static void update(Ordine o){
		if (o==null) return;
		
		if (o.getId() == null){
			Ordine newEntity = create(o.getCliente());
			o.setId(newEntity.getId());
		}else{
			Connection c = DBManager.getConnection();
			try(Statement stmt = c.createStatement()){
				
				stmt.executeUpdate("UPDATE ORDINI SET PREZZOVENDITA='"+o.getCliente()+"' WHERE ID=" +o.getId()+";");
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	static void delete(Ordine o){
		if (o == null || o.getId() == null) return;
		Connection c = DBManager.getConnection();
		try(Statement stmt = c.createStatement()){
			
			stmt.executeUpdate("DELETE FROM ORDINI WHERE ID=" +o.getId()+";");
			o.setId(null);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
}
