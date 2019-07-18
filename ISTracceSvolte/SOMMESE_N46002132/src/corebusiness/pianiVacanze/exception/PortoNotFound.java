package corebusiness.pianiVacanze.exception;

public class PortoNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -297832315225534420L;

	public void printErrorMessage() {
		System.err.println("Porto non Trovato");
	}

}
