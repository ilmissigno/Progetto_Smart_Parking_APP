package Control;

import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Brano;

public interface PlayListManager {

	public ArrayList<Brano> CreaPlayList(String tipo, String target) throws SQLException;

	public boolean VerificaTitolo(String titoloUtente, String utente) throws SQLException;

	public boolean MemorizzaPlayList(String titoloPlayListUtente, String Utente, ArrayList<Brano> listaBrani)
			throws SQLException;
}
