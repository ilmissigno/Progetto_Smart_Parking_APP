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
			//devo mettere il comando che mi dice quando � arrivata una tupla
			if(corrispondenza.readCorrispondenza( tm, this.getAuto().getTarga(),this.getAutomobilista().getUsername())) {
				tm.commitTransaction();
			}else {
				//devo avvisare che l'associazione di quell'auto all'utente c'� gi�
				//altrimenti devo creare la corrispondenza
				if(corrispondenza.createCorrispondenza(tm,this.getAuto().getTarga(),this.getAutomobilista().getUsername())) {
					//AutoAggiunta
					tm.commitTransaction();
		
					
				}
			}
		}catch(Exception e) {
			
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
	
	/*public boolean InserisciCorrispondenza(String Targa,String username)  {


		CorrispondenzaDAO ListaAuto=new CorrispondenzaDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			//devo mettere il comando che mi dice quando � arrivata una tupla
			if(ListaAuto.readCorrispondenza( tm, Targa,username)) {
				tm.commitTransaction();
				this.addAuto(Targa);
				return true;
			}else {
				//devo avvisare che l'associazione di quell'auto all'utente c'� gi�
				//altrimenti devo creare la corrispondenza
				if(ListaAuto.createCorrispondenza(tm, Targa,username)) {
					//AutoAggiunta
					tm.commitTransaction();
					this.setListaAuto(new ArrayList<String>());
					this.addAuto(Targa);
					return true;
				}else {
					return false;
				}
			}
		}

		catch(Exception e) {
			tm.rollbackTransaction();
			return false;
		}
	}*/




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
		// TODO Auto-generated method stub
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




