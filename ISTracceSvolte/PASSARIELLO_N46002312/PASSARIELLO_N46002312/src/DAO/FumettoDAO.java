package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import coreBusiness.GestioneFumetti.Fumetto;

public class FumettoDAO {

	static Fumetto create(Integer prezzoVendita){
		Connection c = DBManager.getConnection();
		Fumetto f = new Fumetto();
		
		try(Statement stmt = c.createStatement()){
			
			stmt.executeUpdate("INSERT INTO FUMETTI (PREZZOVENDITA) VALUES ('"+prezzoVendita+"')");
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next() == false){
				throw new SQLException("ID non autogenerato");
			}
			f.setId(rs.getInt(1));
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		if (f.getId() == null)
			f = null;
		return f;
	}
	
	static Fumetto read(Integer id){
		if (id == null) return null;
		Connection c = DBManager.getConnection();
		Fumetto f = null;
		try(Statement stmt = c.createStatement()){
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM FUMETTI WHERE ID=" +id+";");
			if (rs.next() == false){
				throw new SQLException("ID non trovato");
			}
			f = new Fumetto(rs.getString("COLLANA"), rs.getString("STATOCOLLANA"), rs.getInt("PREZZOVENDITA"),
					rs.getInt("PREZZOACQUISTO"), rs.getString("FORNITORE"), rs.getString("STATO"),
					rs.getString("ETICHETTANOVITA"));
			f.setId(id);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return f;
	}
	
	static void update(Fumetto f){
		if (f==null) return;
		
		if (f.getId() == null){
			Fumetto newEntity = create(f.getPrezzoVendita());
			f.setId(newEntity.getId());
		}else{
			Connection c = DBManager.getConnection();
			try(Statement stmt = c.createStatement()){
				
				stmt.executeUpdate("UPDATE FUMETTI SET PREZZOVENDITA='"+f.getPrezzoVendita()+"' WHERE ID=" +f.getId()+";");
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	static void delete(Fumetto f){
		if (f == null || f.getId() == null) return;
		Connection c = DBManager.getConnection();
		try(Statement stmt = c.createStatement()){
			
			stmt.executeUpdate("DELETE FROM FUMETTI WHERE ID=" +f.getId()+";");
			f.setId(null);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
}
