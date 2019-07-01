package corebusiness.pianiVacanze.exception;

public class PianoVacanzaNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2527478685940668468L;
	public void printErrorMessage() {
		System.err.println("Piano Vacanza non Trovato");
	}
}
