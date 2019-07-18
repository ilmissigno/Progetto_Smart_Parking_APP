package coreBusiness.exception;

public class FumettiNotFoundException extends Exception {
	
	private static final long serialVersionUID = -1150997600478663672L;

	public void gestisciErrore(){
		System.out.println("Nessun fumetto è stato venduto ieri.");
	}
}
