package coreBusiness.exception;

public class AnnoNotValidException extends Exception {
	
	private static final long serialVersionUID = 3272597149195956538L;
	
	public void printMessage(){
		System.out.println("Anno inserito non valido");
	}
	
}
