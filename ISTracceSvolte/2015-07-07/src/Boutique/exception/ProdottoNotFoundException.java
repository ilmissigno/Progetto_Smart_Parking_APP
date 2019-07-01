package Boutique.exception;

public class ProdottoNotFoundException extends Exception{

	private static final long serialVersionUID = 6073017300211513217L;
	
	public String getMessage(){
		return "Prodotto non presente!";
	}
	
}
