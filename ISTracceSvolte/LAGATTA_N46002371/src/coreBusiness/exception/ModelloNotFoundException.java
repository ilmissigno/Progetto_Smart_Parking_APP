package coreBusiness.exception;

public class ModelloNotFoundException extends Exception{

	private static final long serialVersionUID = 8121605482506964727L;
	
	public void printMessage(){
		System.out.println("Non è stato trovato nessun modello associato ai dati inseriti");
	}
	
}
