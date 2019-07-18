package prodotti;

public class Resistenza extends AComponenteElementare {

	public Resistenza() {
	}

	private float valoreResistenza;

	private float incertezzaResistenza;

	/**
	 * @param cod
	 * @param prod
	 * @param prezzo
	 * @param val
	 * @param inc
	 */
	public Resistenza(int cod, String prod, float prezzo, float val, float inc) {
		// TODO implement here
	}

	public float getValoreResistenza() {
		return valoreResistenza;
	}

	public void setValoreResistenza(float valoreResistenza) {
		this.valoreResistenza = valoreResistenza;
	}

	public float getIncertezzaResistenza() {
		return incertezzaResistenza;
	}

	public void setIncertezzaResistenza(float incertezzaResistenza) {
		this.incertezzaResistenza = incertezzaResistenza;
	}

}