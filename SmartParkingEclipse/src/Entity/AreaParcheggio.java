package Entity;

import DAO.AreaParcheggioDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class AreaParcheggio {
	private int CodiceArea;
	private double CostoTicket;
	
	public AreaParcheggio() {
		
	}

	public int getCodiceArea() {
		return CodiceArea;
	}

	public void setCodiceArea(int codiceArea) {
		CodiceArea = codiceArea;
	}

	public double getCostoTicket() {
		return CostoTicket;
	}

	public void setCostoTicket(double costoTicket) {
		CostoTicket = costoTicket;
	}
	
	public double OttieniCostoTicket(String codiceArea) {
		AreaParcheggioDAO a = new AreaParcheggioDAO();
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();
			double costo = a.readAreaParcheggio(tm,codiceArea);
			tm.commitTransaction();
			return costo;
		}catch(Exception e) {
			tm.rollbackTransaction();
			return -1;
		}
	}
	
}
