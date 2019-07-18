package DAO;

import java.sql.*;
import java.util.ArrayList;

import coreBusiness.utenti.*;

public class FabbricatoDAO {
	
	public static ArrayList<Fabbricato> read(String codFis) throws SQLException{
		Connection conn= DBManager.getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT * FROM FABBRICATI WHERE CONTRIBUENTE=" + codFis);
		ArrayList<Fabbricato> fabbricati=new ArrayList<Fabbricato>();
		while(rs.next()){
			Fabbricato f=new Fabbricato();
			f.setId(rs.getInt("ID"));
			f.setRendita(rs.getFloat("RENDITA"));
			f.setComune(rs.getString("COMUNE"));
			f.setGiorniPossesso(rs.getInt("GIORNIPOSSESSO"));
			f.setPercProprietaPossesso(rs.getFloat("PERCENTUALEPROPRIETAPOSSESSO"));
			Contribuente contr=new Contribuente();
			contr=ContribuenteDAO.read(rs.getString("CONTRIBUENTE"));
			f.setContr(contr);
			f.setAnnoCessione(rs.getInt("ANNOCESSIONE"));
		    /* ho provato ad agigungere e costruire il fabbricato in una istruzione perchè
		     * mi pare che non funzioni se passo solo l'oggetto
		     * bisogna aggiustare l'ultimo parametro passato al costruttore
			fabbricati.add(new Fabbricato(rs.getInt("ID"),rs.getFloat("RENDITA"),rs.getInt("GIORNIPOSSESSO"),
					rs.getFloat("PERCENTUALEPOSSESSO"),rs.getString("COMUNE"),
					new Contribuente(ContribuenteDAO.read(rs.getString("CODICEFISCALE")))
					))
			*/
			fabbricati.add(f);
		}
		return fabbricati;
	}
}
