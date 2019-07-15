package ControllerImpl;

import java.io.DataOutputStream;
import java.io.IOException;

import Controller.GestoreAccount;
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
		Utente u = new Utente();
		return u.getConto(username,password);
	}

	@Override
	public boolean AggiornaConto(String username, String password, double CostoTotale) {
		Utente u = new Utente();
		if(u.AggiornaConto(username,password,CostoTotale)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
}
