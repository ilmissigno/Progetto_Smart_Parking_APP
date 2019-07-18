package ControllerImpl;

import java.util.ArrayList;
import java.sql.SQLException;
import Control.PlayListManager;
import DAO.PlayListDAO;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import Entity.Brano;
import Entity.PlayList;
import Entity.Valutazione;
import DAO.ValutazioneDAO;

public class PlayListManagerImpl implements PlayListManager {

	public PlayListManagerImpl() {
	}

	public ArrayList<Brano> CreaPlayList(String Tipo, String Target) throws SQLException {
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		// serve a collegarsi
		ArrayList<Brano> listaBrani = new ArrayList<>();

		try {
			tm.beginTransaction();

			listaBrani = ValutazioneDAO.readValutazione(tm, Tipo, Target);
			tm.commitTransaction();
		}

		catch (Exception e) {
			tm.rollbackTransaction();
			throw new SQLException("Impossibile ottenere Brani.");
		}

		return listaBrani;
	}

	@Override
	public boolean VerificaTitolo(String titoloUtente, String Utente) throws SQLException {
		// TODO Auto-generated method stub

		ArrayList<PlayList> PlayListUtente = null;
		
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		

		try {
			tm.beginTransaction();

			PlayListUtente = PlayListDAO.readPlayList(tm, titoloUtente, Utente);

			tm.commitTransaction();

			System.out.println("SizePlayList:" + PlayListUtente.size());
			for (int i = 0; i < PlayListUtente.size(); i++) {
				if (PlayListUtente.get(i).GetTitolo().equals(titoloUtente)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			tm.rollbackTransaction();
			throw new SQLException("Impossibile Verificare Titolo.");
		}

	}

	@Override
	public boolean MemorizzaPlayList(String titoloPlayListUtente, String Utente, ArrayList<Brano> listaBrani)
			throws SQLException {
		// TODO Auto-generated method stub
		boolean valore;
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		try {
			tm.beginTransaction();

			valore = PlayListDAO.createPlayList(tm, titoloPlayListUtente, Utente, listaBrani);
			

			return valore;

		} catch (Exception e) {
			tm.rollbackTransaction();
			throw new SQLException("Impossibile MemorizzarePlaylist.");
		}
	}
}

// }
