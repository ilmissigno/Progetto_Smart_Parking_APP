package ordini;

import java.util.*;

public interface IGestoreOrdini {

	public void inserisciNuovoOrdine();

	/**
	 * @return
	 */
	public Set<Ordine> ottieniOrdini();

}