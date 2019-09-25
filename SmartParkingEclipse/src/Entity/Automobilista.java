package Entity;

import java.sql.SQLException;
import java.util.ArrayList;
import DAO.AutomobilistaDAO;
import DAO.CorrispondenzaDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class Automobilista extends Utente {
	private double Credito;
	private ArrayList<Auto> ListaAuto;

	public Automobilista() {

	}

	public Automobilista(String username, String Password) {
		this.ListaAuto = new ArrayList<Auto>();
		AutomobilistaDAO autoDAO=new AutomobilistaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			autoDAO.readAutomobilista(tm,username,Password);
			tm.commitTransaction();
			this.CodiceFiscale=autoDAO.getCodiceFiscale();
			this.Cognome=autoDAO.getCognome();
			this.Email=autoDAO.getEmail();
			this.Nome=autoDAO.getNome();
			this.password=autoDAO.getPassword();
			this.username=autoDAO.getUsername();
			this.Credito=autoDAO.getCredito();
		}catch(Exception e) {
			tm.rollbackTransaction();

		}
	}

	public double getCredito() {
		return Credito;
	}

	public void setCredito(double credito) {
		this.Credito = credito;
	}

	public ArrayList<Auto> getListaAuto() {
		return ListaAuto;
	}

	public void setListaAuto(ArrayList<Auto> listaAuto) {
		this.ListaAuto = listaAuto;
	}

	public boolean AggiornaContoInDB(double CostoTotale) {
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

	public boolean AggiungiAutoAtList(String targa) {
		// TODO Auto-generated method stub
		//this.ListaAuto.add(auto);
		CorrispondenzaDAO corrispondenza = new CorrispondenzaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(corrispondenza.createCorrispondenza(tm, targa, this.getUsername())) {
				tm.commitTransaction();
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}

	public ArrayList<String> OttieniListaAuto() throws SQLException {
		CorrispondenzaDAO corrispondenzaDAO=new CorrispondenzaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		ArrayList<String> listaAuto = new ArrayList<String>();
		try {
			tm.beginTransaction();

			listaAuto = corrispondenzaDAO.readList(tm, this.getUsername());
			tm.commitTransaction();
			for(int i=0;i<listaAuto.size();i++) {
				Auto auto= new Auto(listaAuto.get(i));
				this.ListaAuto.add(auto);
			}

		}catch (Exception e) {
			tm.rollbackTransaction();
			throw new SQLException("Impossibile ottenere Lista.");
		}
		return listaAuto;
	}



	public boolean EliminaAuto(String targa,String username,String password) {
		//Not Implemented
		return false;
	}
}











