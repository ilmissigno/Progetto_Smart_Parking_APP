package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.TransactionManager.TransactionStateException;
import Entity.Valutazione;
import Entity.Brano;

//import Entity.Ordine;
import Entity.PlayList;

public class PlayListDAO {

	public PlayListDAO() {
	}

	public static boolean createPlayList(TransactionManager tm, String Titolo, String Utente,
			ArrayList<Brano> listaBrani) throws Exception {
		String idPlaylist = null;
		tm.assertInTransaction();

		try (PreparedStatement ps = tm.getConnection()
				.prepareStatement("INSERT INTO PLAYLIST(NOMEPLAYLIST,UTENTE) VALUES(?,?)")) {

			ps.setString(1, Titolo);
			ps.setString(2, Utente);

			ps.executeUpdate();
			tm.commitTransaction();

			tm.beginTransaction();
			tm.assertInTransaction();
			try (PreparedStatement pt = tm.getConnection()
					.prepareStatement("SELECT ID FROM PLAYLIST WHERE ((UTENTE=?)AND(NOMEPLAYLIST=?))")) {

				pt.setString(1, Utente);
				pt.setString(2, Titolo);
				try (ResultSet rs = pt.executeQuery()) {
					if (rs.next() == true) {
						idPlaylist = rs.getString("ID");
					}
				}

				System.out.println("idplaylist:" + idPlaylist);
			}
			tm.commitTransaction();
			for (int i = 0; i < listaBrani.size(); i++) {
				tm.beginTransaction();
				tm.assertInTransaction();
				try (PreparedStatement pc = tm.getConnection()
						.prepareStatement("INSERT INTO AFFERENZABRANI(IDPLAYLIST,CODICEBRANO) VALUES(?,?)")) {

					pc.setString(1, idPlaylist);

					pc.setString(2, listaBrani.get(i).getCodice());

					pc.executeUpdate();

					tm.commitTransaction();
				}
			}

			return true;

		} catch (SQLException e) {
			throw new SQLException("Impossibile InserirePlayList", e);
		}

	}

	@SuppressWarnings("finally")
	public static ArrayList<PlayList> readPlayList(TransactionManager tm, String titoloUtente, String utente) {
		tm.assertInTransaction();
		PlayList p = null;
		ArrayList<PlayList> PlayListUtente = new ArrayList<PlayList>();
		try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM PLAYLIST WHERE Utente=?")) {
			ps.setString(1, utente);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next() == true) {
					String ID = rs.getString("ID");
					String TitoloPlayList = rs.getString("NOMEPLAYLIST");
					p = new PlayList(ID, TitoloPlayList);
					PlayListUtente.add(p);

				}

			}
			return PlayListUtente;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return PlayListUtente;
		}

	}
}
