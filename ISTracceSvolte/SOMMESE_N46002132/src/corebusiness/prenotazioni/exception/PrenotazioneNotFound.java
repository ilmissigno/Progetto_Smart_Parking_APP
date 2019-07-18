package corebusiness.prenotazioni.exception;

public class PrenotazioneNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1163772848610374696L;
	public void printErrorMessage() {
		System.err.println("Prenotazione non Trovata");
	}
}
