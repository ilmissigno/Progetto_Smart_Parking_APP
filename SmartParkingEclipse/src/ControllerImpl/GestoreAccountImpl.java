package ControllerImpl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.GestoreAccount;
import DAO.CorrispondenzaDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import Entity.Auto;
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
		if(u.AggiornaConto(CostoTotale)) {
			return true;
		}else {
			return false;
		}
	}
/*
	@Override
	public boolean CaricaConto(String username ,String password,double Importo) {
		Automobilista a = new Automobilista(username,password);
		if(a.AggiornaConto(-Importo)) {
			return true;
		}else {
			return false;
		}
	}*/
	
	public boolean AggiungiAuto(String Targa,String CFProprietario,String username, String password)  {
		Automobilista aut= new Automobilista(username, password);
		//Corrispondenza ListaAuto=new Corrispondenza(Targa,username);
		if(aut.AggiungiAuto(Targa, CFProprietario)) {
			//Significa che ho aggiunto l'auto
				return true;
			}
			else { 
				return false;
			}
		
		
	}
	
	//vedere...
	public ArrayList<String> OttieniLista(String username, String password) throws SQLException {
		Automobilista autom = new Automobilista(username,password);
		return autom.OttieniListaAuto();
		
		
		
	}

	public boolean EliminaAuto(String targa,String username,String password) {
		return false;
		// TODO Auto-generated method stub
		
					
				}
			
	
	

//RISOLTO
	@Override
	public double getConto(String username, String password) {
		// TODO Auto-generated method stub
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

	
	


	
	
	

