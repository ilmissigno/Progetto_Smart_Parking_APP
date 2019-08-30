package Entity;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import DAO.CorrispondenzaDAO;

public class Corrispondenza {
	
	private ArrayList<String> listaAuto;
	private String username;
	
	public Corrispondenza(String username) {
		this.setUsername(username);
	}
	
	public Corrispondenza(String targa,String username) {
		this.setListaAuto(new ArrayList<String>());
		this.setUsername(username);
		this.addAuto(targa);
	}
	
	public ArrayList<String> getListaAuto() {
		return listaAuto;
	}

	public void setListaAuto(ArrayList<String> listaAuto) {
		this.listaAuto = listaAuto;
	}
	
	public void addAuto(String t) {
		this.listaAuto.add(t);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean InserisciCorrispondenza(String Targa,String username)  {


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
	}




	public ArrayList<String> GetList(String Username) throws SQLException {
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		CorrispondenzaDAO corrispondenza = new CorrispondenzaDAO();
		try {
			tm.beginTransaction();

			this.setListaAuto(corrispondenza.readList(tm, Username));
			tm.commitTransaction();
		}

		catch (Exception e) {
			tm.rollbackTransaction();
			throw new SQLException("Impossibile ottenere Lista.");
		}

		return this.getListaAuto();
	}
	public boolean EliminaCorrispondenza(String targa, String username) {
		// TODO Auto-generated method stub
					CorrispondenzaDAO corrispondenza = new CorrispondenzaDAO();
					TransactionManager tm = TransactionManagerFactory.createTransactionManager();
					try {
						tm.beginTransaction();
						if(corrispondenza.deleteCorrispondenza(tm,targa,username));
						tm.commitTransaction();
						return true;
					}catch(Exception e) {
						tm.rollbackTransaction();
						return false;
					}
			
	
		
		
		
	}




}




