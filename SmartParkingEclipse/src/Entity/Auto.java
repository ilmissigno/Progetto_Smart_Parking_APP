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
	
	public boolean AggiungiAuto(String Targa,String CFProprietario){
		//boolean autopresente=false;
		//boolean autoassociata=false;
		AutoDAO aut = new AutoDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			if(aut.readAuto(tm,Targa)) {
				//ok significa che l'auto � gi� inserita, devo inserire o meno la corrispondenza
				tm.commitTransaction();
				return true;
			}
			else{
			//Altrimenti Creo l'auto
				if(aut.createAuto( tm, Targa, CFProprietario)) {
					tm.commitTransaction();
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
	
	}
	
	

