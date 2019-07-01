import coreBusiness.GestioneFumetti.GestoreBusiness;

public class Main {

	public static void main (String args[]){
		
		GestoreBusiness gb = GestoreBusiness.getGestoreBusiness();
		gb.inviaEmailRiepilogo();
	}
}