package Entity;

import DAO.AutoDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class Auto {
	
	private String Targa;
	private String Proprietario;
	
	public Auto() {
		
	}

	public String getTarga() {
		return Targa;
	}

	public void setTarga(String targa) {
		Targa = targa;
	}

	public String getProprietario() {
		return Proprietario;
	}

	public void setProprietario(String proprietario) {
		Proprietario = proprietario;
	}
	
	public boolean checkAuto(String Targa) {
		AutoDAO auto = new AutoDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			return auto.readAuto(tm,Targa);
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}
	
}
