package ordini;

import java.util.*;

public class Ordine {

	public Ordine() {
	}

	private Date dataDiEmissione;

	private int idOrdine;

	/**
	 * @param data
	 */
	public Ordine(Date data) {
		// TODO implement here
	}

	/**
	 * @param cod
	 * @param qta
	 */
	public void aggiungiProdotto(String cod, int qta) {
		// TODO implement here
	}

	public Date getDataDiEmissione() {
		return dataDiEmissione;
	}

	public void setDataDiEmissione(Date dataDiEmissione) {
		this.dataDiEmissione = dataDiEmissione;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

}