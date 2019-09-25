package Entity;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import DAO.CorrispondenzaDAO;

public class Corrispondenza {

	private Auto auto;
	private Automobilista automobilista;

	public Corrispondenza(Automobilista autom) {
		this.setAutomobilista(autom);
	}

	public Corrispondenza(Auto auto, Automobilista automobilista) {
		this.setAuto(auto);
		this.setAutomobilista(automobilista);
		CorrispondenzaDAO corrispondenza=new CorrispondenzaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(corrispondenza.readCorrispondenza( tm, this.getAuto().getTarga(),this.getAutomobilista().getUsername())) {
				tm.commitTransaction();
			}else {
				if(corrispondenza.createCorrispondenza(tm,this.getAuto().getTarga(),this.getAutomobilista().getUsername())) {
					tm.commitTransaction();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public Automobilista getAutomobilista() {
		return automobilista;
	}

	public void setAutomobilista(Automobilista automobilista) {
		this.automobilista = automobilista;
	}

	public ArrayList<String> GetList() throws SQLException {
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		CorrispondenzaDAO corrispondenza = new CorrispondenzaDAO();
		ArrayList<String> listaAuto = new ArrayList<String>();
		try {
			tm.beginTransaction();

			listaAuto = corrispondenza.readList(tm, this.getAutomobilista().getUsername());
			tm.commitTransaction();
			return listaAuto;
		}

		catch (Exception e) {
			tm.rollbackTransaction();
			throw new SQLException("Impossibile ottenere Lista.");
		}

	}
	public boolean EliminaCorrispondenza() {
		CorrispondenzaDAO corrispondenza = new CorrispondenzaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(corrispondenza.deleteCorrispondenza(tm,this.getAuto().getTarga(),this.getAutomobilista().getUsername()));
			tm.commitTransaction();
			return true;
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}

}




