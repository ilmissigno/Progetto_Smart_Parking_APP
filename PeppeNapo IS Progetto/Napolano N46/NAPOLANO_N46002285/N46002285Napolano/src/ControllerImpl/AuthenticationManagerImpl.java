package ControllerImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import Control.AuthenticationManager;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import DAO.UtenteDAO;
import DAO.ValutazioneDAO;
import Entity.Brano;

public class AuthenticationManagerImpl implements AuthenticationManager {

	public AuthenticationManagerImpl() {
	}

	@Override
	public String Login(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		// serve a collegarsi
		String Codice = new String();

		try {
			tm.beginTransaction();

			Codice = UtenteDAO.readUtente(tm, username, password);
			tm.commitTransaction();
		}

		catch (Exception e) {
			tm.rollbackTransaction();
			throw new SQLException("Impossibile Effettuare Login.");
		}

		return Codice;

	}

}
