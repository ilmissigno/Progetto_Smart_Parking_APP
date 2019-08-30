package Entity;

import java.sql.SQLException;

import DAO.AutoDAO;
import DAO.AutomobilistaDAO;
import DAO.CorrispondenzaDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class Automobilista extends Utente {
private double Credito;
	
	public Automobilista() {
		
	}
	
	public Automobilista(String username, String Password) {
		this.setUsername(username);
		this.setPassword(Password);
	}
	
	public double getCredito() {
		return Credito;
	}

	public void setCredito(double credito) {
		Credito = credito;
	}
	
	public double getConto(String username,String password) {
		AutomobilistaDAO auto = new AutomobilistaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			auto.readContoAutomobilista(tm, username, password);
			tm.commitTransaction();
			this.setUsername(username);
			this.setPassword(password);
			this.setCredito(auto.getCredito());
			return this.getCredito();
		}catch(Exception e) {
			tm.rollbackTransaction();
			return -1;
		}
	}
	
	public boolean AggiornaConto(double CostoTotale) {
		AutomobilistaDAO auto = new AutomobilistaDAO(this.getUsername(),this.getPassword());
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(auto.updateContoAutomobilista(tm,CostoTotale)) {
				tm.commitTransaction();
				this.setUsername(this.getUsername());
				this.setPassword(this.getPassword());
				this.setCredito(this.getCredito()-CostoTotale);
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}
	
	public boolean CaricaConto(double Importo) {
		AutomobilistaDAO auto = new AutomobilistaDAO(this.getUsername(),this.getPassword());
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		double CostoTotale = Importo*-1;
		try {
			tm.beginTransaction();
			if(auto.updateContoAutomobilista(tm,CostoTotale)) {
				tm.commitTransaction();
				this.setUsername(this.getUsername());
				this.setPassword(this.getPassword());
				this.setCredito(this.getCredito()+Importo);
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}
	
}
		
	
	
	

	
	
	

	

