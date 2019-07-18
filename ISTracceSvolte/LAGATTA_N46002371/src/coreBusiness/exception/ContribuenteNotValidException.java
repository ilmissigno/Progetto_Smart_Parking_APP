package coreBusiness.exception;

public class ContribuenteNotValidException extends Exception {

	private static final long serialVersionUID = 9189273159533118084L;
	
	public void printMessage(){
		  System.out.println("I dati inseriti per il contribuente non sono validi! ");
	}

	
}
