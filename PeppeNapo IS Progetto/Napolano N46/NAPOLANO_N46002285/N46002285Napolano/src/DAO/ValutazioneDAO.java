package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entity.Brano;

import Entity.Valutazione;

public class ValutazioneDAO {

	public ValutazioneDAO() {
	}

	public static ArrayList<Brano> readValutazione(TransactionManager tm, String Tipo, String Target)
			throws SQLException {

		tm.assertInTransaction();

		PreparedStatement ps = null;
		ArrayList<Brano> listabrani = new ArrayList<>();

		try {
			if (Tipo.equals("BasePunteggio")) {

				ps = tm.getConnection().prepareStatement(
						"SELECT *FROM VALUTAZIONI  RIGHT JOIN BRANI ON CODICEBRANO=BRANO  WHERE UTENTE=? ORDER BY VALOREPUNTEGGIO DESC");
			} else {
				ps = tm.getConnection().prepareStatement(
						"SELECT *FROM VALUTAZIONI  RIGHT JOIN BRANI ON CODICEBRANO=BRANO  WHERE UTENTE=? ORDER BY NUMEROVOLTE DESC");

			}

			ps.setString(1, Target);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next() == true) {
					String CodiceBrano = rs.getString("BRANO");
					String TitoloBrano = rs.getString("TITOLO");
					Brano b = new Brano(TitoloBrano, CodiceBrano);

					listabrani.add(b);
				}

			}
		} catch (SQLException e) {
			throw new SQLException("Impossibile accedere alle Valutazioni");
		}

		return listabrani;

	}
}
