package prodotti;

import prodotti.AProdotto;

public class Formazione {

	public Formazione() {
	}

	public Formazione(int codci, int codco, int numuni) {
		numeroUnitaImpiegate = numuni;
		codiceComponente.setCodice(codco);
		codiceCircuitoStampato.setCodice(codci);
	}

	private int numeroUnitaImpiegate;
	private AComponenteElementare codiceComponente;
	private CircuitoStampato codiceCircuitoStampato;

	/**
	 * @param p
	 * @param qta
	 */
	public Formazione(AProdotto p, int qta) {
		// TODO implement here
	}

	/**
	 * @return
	 */
	public int getNumeroUnitaImpiegate() {
		// TODO implement here
		return numeroUnitaImpiegate;
	}

	/**
	 * @return
	 */
	public int getCodiceCircuitoStampato() {
		// TODO implement here
		return codiceCircuitoStampato.getCodice();
	}

	/**
	 * @return
	 */
	public int getCodiceComponente() {
		// TODO implement here
		return codiceComponente.getCodice();
	}

}