package Entity;

public class Brano {
	private String Titolo;
	private String Codice;

	public Brano(String Titolo, String Codice) {
		this.Titolo = Titolo;
		this.Codice = Codice;
	}

	@Override
	public String toString() {
		return " [Titolo=" + Titolo + ", Codice=" + Codice + "]";
	}

	public String getCodice() {
		return this.Codice;
	}

}
