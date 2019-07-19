package ControllerImpl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.GestoreAccount;
import DAO.CorrispondenzaDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import Entity.Automobilista;
import Entity.Corrispondenza;
import Entity.Utente;

public class GestoreAccountImpl implements GestoreAccount {

	@Override
	public boolean Login(String username, String password) {
		// TODO Auto-generated method stub
		Utente u = new Utente();
		if(u.Login(username,password)) {
			//ok
			return true;
		}else {
			//errore
			return false;
		}
	}

	@Override
	public boolean RegistraUtente(String CodiceFiscale, String Cognome, String Nome, String username, String password,
			String email) {
		Utente u = new Utente();
		if(u.RegistraUtente(CodiceFiscale,Cognome,Nome,username,password,email)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public double getConto(String username, String password) {
		Automobilista u = new Automobilista();
		return u.getConto(username,password);
	}

	@Override
	public boolean AggiornaConto(String username, String password, double CostoTotale) {
		Automobilista u = new Automobilista();
		if(u.AggiornaConto(username,password,CostoTotale)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean CaricaConto(String username, String password, double Importo) {
		Automobilista a = new Automobilista();
		if(a.CaricaConto(username,password,Importo)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean AggiungiAuto(String Targa,String CFProprietario,String username)  {
		Automobilista a = new Automobilista();
		Corrispondenza ListaAuto=new Corrispondenza(username,Targa);
		if(a.AggiungiAuto(Targa, CFProprietario, username)) {
			//Significa che ho aggiunto l'auto
			if(ListaAuto.InserisciCorrispondenza( Targa,username)) {
				return true;
			}
			else { return false;
				
			}
		}
		return false;
			
			
		
	}
	
	public ArrayList<Corrispondenza> OttieniLista(String username) throws SQLException {
		Corrispondenza c= new Corrispondenza();
		return c.GetList(username);
		
		
	}
	}
	
	
	
	

