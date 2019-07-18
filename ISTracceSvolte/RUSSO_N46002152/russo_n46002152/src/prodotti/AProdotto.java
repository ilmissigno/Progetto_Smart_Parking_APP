package prodotti;

public abstract class AProdotto {

	public AProdotto() {
	}

	protected int Codice;
	protected String Produttore;
	protected float Prezzo;

	/**
	 * @return
	 * 
	 */
	public int getCodice() {
		return Codice;
	}

	public void setCodice(int cod) {
		Codice = cod;
	}
}