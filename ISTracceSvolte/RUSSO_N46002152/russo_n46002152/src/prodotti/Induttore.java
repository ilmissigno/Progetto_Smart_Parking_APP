package prodotti;

public class Induttore extends AComponenteElementare {

	public Induttore() {
	}

	private float valoreResistenza;

	private float valoreInduttanza;

	private float incertezzaResistenza;

	private float incertezzaInduttanza;

	/**
	 * @param cod
	 * @param prod
	 * @param prezzo
	 * @param valr
	 * @param valin
	 * @param incr
	 * @param incin
	 */
	public Induttore(int cod, String prod, float prezzo, float valr, float valin, float incr, float incin) {
		// TODO implement here
	}

	public float getValoreResistenza() {
		return valoreResistenza;
	}

	public void setValoreResistenza(float valoreResistenza) {
		this.valoreResistenza = valoreResistenza;
	}

	public float getValoreInduttanza() {
		return valoreInduttanza;
	}

	public void setValoreInduttanza(float valoreInduttanza) {
		this.valoreInduttanza = valoreInduttanza;
	}

	public float getIncertezzaResistenza() {
		return incertezzaResistenza;
	}

	public void setIncertezzaResistenza(float incertezzaResistenza) {
		this.incertezzaResistenza = incertezzaResistenza;
	}

	public float getIncertezzaInduttanza() {
		return incertezzaInduttanza;
	}

	public void setIncertezzaInduttanza(float incertezzaInduttanza) {
		this.incertezzaInduttanza = incertezzaInduttanza;
	}

}