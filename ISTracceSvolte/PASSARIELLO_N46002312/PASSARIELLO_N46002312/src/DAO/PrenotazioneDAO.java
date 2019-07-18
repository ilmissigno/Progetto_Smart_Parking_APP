package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import coreBusiness.GestioneFumetti.Prenotazione;

public class PrenotazioneDAO {

	public static Prenotazione read(Date data){
		Connection c = DBManager.getConnection();
		Prenotazione p = null;
		try(Statement stmt = c.createStatement()){
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM PRENOTAZIONI WHERE DATA = '"+data+"';");
			while(rs.next()){
			p = new Prenotazione(rs.getInt("ORDINE"), rs.getInt("FUMETTO"), rs.getInt("QUANTITAFUMETTO"),
					rs.getDate("DATA"));
			System.out.println("Fumetto: "+p.getFumetto()+" Quantità: "+p.getQuantitaFumetto()+" Data: "+p.getData());
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return p;
	}
}
