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

	@Override
	public boolean CaricaConto(String username ,String password,double Importo) {
		Automobilista a = new Automobilista(username,password);
		if(a.CaricaConto(Importo)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean AggiungiAuto(String Targa,String CFProprietario,String username)  {
		Auto a = new Auto();
		Corrispondenza ListaAuto=new Corrispondenza(Targa,username);
		if(a.AggiungiAuto(Targa, CFProprietario)) {
			//Significa che ho aggiunto l'auto
			if(ListaAuto.InserisciCorrispondenza(Targa,username)) {
				return true;
			}
			else { return false;
				
			}
		}
		return false;
			
			
		
	}
	
	public ArrayList<String> OttieniLista(String username) throws SQLException {
		Corrispondenza c= new Corrispondenza(username);
		return c.GetList(username);
		
		
	}

	public boolean EliminaAuto(String targa,String username) {
		// TODO Auto-generated method stub
			Corrispondenza ListaAuto=new Corrispondenza(username);
				if(ListaAuto.EliminaCorrispondenza(targa,username)) {
					return true;
				}
				else { return false;
					
				}
			
	
	}


	@Override
	public double getConto(String username, String password) {
		// TODO Auto-generated method stub
		Automobilista u = new Automobilista();
		return u.getConto(username,password);
		
	}

	
	}


	
	
	

