package Control;

import java.sql.SQLException;
import java.util.*;

import ControllerImpl.*;
import Entity.PlayList; //da creare
//import Entity.Pneumatico;
import Entity.Brano;

public class GestoreStreamingMusica implements IGestoreStreamingMusica {

	private static GestoreStreamingMusica instance;

	private PlayListManager playlistmanager;
	private BraniManager branimanager;
	private AuthenticationManager authenticationmanager;

	protected GestoreStreamingMusica(PlayListManager playlistmanager, BraniManager branimanager,
			AuthenticationManager authenticationmanager) {
		super();
		this.playlistmanager = playlistmanager;
		this.branimanager = branimanager;
		this.authenticationmanager = authenticationmanager;
	}

	public static GestoreStreamingMusica getInstance() {
		if (instance == null) {
			instance = new DefaultGestoreStreamingBuilder().build();
		}
		return instance;
	}

	public abstract static class GestoreStreamingBuilder {

		protected GestoreStreamingMusica build(PlayListManager playlistmanager, BraniManager branimanager,
				AuthenticationManager authenticationmanager) {
			return new GestoreStreamingMusica(playlistmanager, branimanager, authenticationmanager);
		}

		public abstract GestoreStreamingMusica build();
	}

	public ArrayList<Brano> CreaPlayList(String tipo, String target) {

		try {
			return playlistmanager.CreaPlayList(tipo, target);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean VerificaTitolo(String titoloUtente, String utente) throws SQLException {
		// TODO Auto-generated method stub
		
		return playlistmanager.VerificaTitolo(titoloUtente, utente);
	}

	public boolean MemorizzaPlaylist(String titoloPlayListUtente, String Utente, ArrayList<Brano> listaBrani)
			throws SQLException {
		// TODO Auto-generated method stub
	
		return playlistmanager.MemorizzaPlayList(titoloPlayListUtente, Utente, listaBrani);

	}

	public String Login(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return authenticationmanager.Login(username, password);

	}

}
