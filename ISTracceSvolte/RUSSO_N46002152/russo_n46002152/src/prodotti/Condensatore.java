package prodotti;

public class Condensatore extends AComponenteElementare {

	public Condensatore() {
	}

	private float valoreCapacita;

	private float incertezzaCapacita;

	/**
	 * @param cod
	 * @param prod
	 * @param prezzo
	 * @param val
	 * @param inc
	 */
	public Condensatore(int cod, String prod, float prezzo, float val, float inc) {
		// TODO implement here
	}

	public float getValoreCapacita() {
		return valoreCapacita;
	}

	public void setValoreCapacita(float valoreCapacita) {
		this.valoreCapacita = valoreCapacita;
	}

	public float getIncertezzaCapacita() {
		return incertezzaCapacita;
	}

	public void setIncertezzaCapacita(float incertezzaCapacita) {
		this.incertezzaCapacita = incertezzaCapacita;
	}

}