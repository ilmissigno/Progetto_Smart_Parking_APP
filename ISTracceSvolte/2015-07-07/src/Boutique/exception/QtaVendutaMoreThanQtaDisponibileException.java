package Boutique.exception;

public class QtaVendutaMoreThanQtaDisponibileException extends Exception {

	private static final long serialVersionUID = -2251495144374829491L;
	
	public String getMessage(){
		return "Quantità venduta inserita maggiore di quella disponibile!";
	}
	
}
