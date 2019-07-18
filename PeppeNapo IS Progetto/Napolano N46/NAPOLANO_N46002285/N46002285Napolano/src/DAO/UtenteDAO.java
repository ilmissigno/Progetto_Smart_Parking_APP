package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {

	public static String readUtente(TransactionManager tm, String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		String Utente = new String();
		Utente = "valorenullo";
		tm.assertInTransaction();
		try (PreparedStatement pt = tm.getConnection()
				.prepareStatement("SELECT CF FROM UTENTI WHERE ((USERNAME=?)AND(PASSWORD=?))")) {

			pt.setString(1, username);
			pt.setString(2, password);
			try (ResultSet rs = pt.executeQuery()) {
				if (rs.next() == true) {
					Utente = rs.getString("CF");
				}
			}

			System.out.println("Utente:" + Utente);
		}

		return Utente;
	}

}
