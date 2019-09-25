package ControllerImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import Controller.GestoreAccount;
import Entity.Auto;
import Entity.Automobilista;
import Entity.Utente;

public class GestoreAccountImpl implements GestoreAccount {

	@Override
	public boolean Login(String username, String password) {
		Utente u = new Utente();
		if(u.Login(username,password)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password,
			String email) {
		Utente u = new Utente(CodiceFiscale,Cognome,Nome,username,password,email);
		if(u.RegistraUtente(CodiceFiscale,Cognome,Nome,username,password,email)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean AggiornaConto(String username, String password, double CostoTotale) {
		Automobilista u = new Automobilista(username,password);
		if(u.AggiornaContoInDB(CostoTotale)) {
			return true;
		}else {
			return false;
		}
	}

	public boolean AggiungiAuto(String Targa,String CFProprietario,String username, String password)  {
		Automobilista aut= new Automobilista(username, password);
		new Auto(Targa,CFProprietario);
		if(aut.AggiungiAutoAtList(Targa)) {
			return true;
		}
		else { 
			return false;
		}
	}

	public ArrayList<String> OttieniLista(String username, String password) throws SQLException {
		Automobilista autom = new Automobilista(username,password);
		return autom.OttieniListaAuto();	
	}

	public boolean EliminaAuto(String targa,String username,String password) {
		//not implemented
		return false;
	}

	@Override
	public double getConto(String username, String password) {
		Automobilista u = new Automobilista(username,password);
		return u.getCredito();
	}

	public boolean ControllaConto(String username, String password, double CostoTotale) {
		Automobilista u = new Automobilista(username,password);
		if(u.getCredito()>=CostoTotale) {
			return true;
		}
		else { return false;
		}
	}
}









