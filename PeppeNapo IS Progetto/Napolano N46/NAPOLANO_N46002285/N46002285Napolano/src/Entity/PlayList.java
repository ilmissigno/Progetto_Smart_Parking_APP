package Entity;



public class PlayList {
	private String TitoloPlayList;
	private String ID;

	public PlayList(String ID, String TitoloPlayList) {
		this.TitoloPlayList = TitoloPlayList;
		// this.Utente=Utente; // dovrei fare GetUtente
		this.ID = ID;
	}

	public String GetTitolo() {
		return TitoloPlayList;
	}
}
	
