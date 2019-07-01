package prodotti;

import java.sql.SQLException;
import java.util.*;

public interface IGestoreProdotti {

	/**
	 * @return
	 */
	public Set<AProdotto> ottieniProdotti();

	/**
	 * @param cod
	 * @param prod
	 * @param prezzo
	 */
	public void modificaProdotto(int cod, String prod, float prezzo);

	/**
	 * @param cod
	 * @return
	 * @throws SQLException
	 */

	public void inserisciFormazione(Formazione f);

	public void eliminaFormazione(Formazione f);

	ArrayList<Integer> ricercaComponenti(int cod) throws SQLException;

}