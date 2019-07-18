package ordini;

import prodotti.AProdotto;

public class ElencoProdotti {

	public ElencoProdotti() {
	}

	private int quantita;

	private float prezzoUnitario;

	/**
	 * @param p
	 * @param qta
	 */
	public ElencoProdotti(AProdotto p, int qta) {
		// TODO implement here
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public float getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(float prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

}