package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prodotti.Formazione;

public class Formazione_DAO {

	public Formazione_DAO() {
	}

	protected static java.util.Map<Integer, Formazione> prodottiCaricati = new java.util.HashMap<Integer, Formazione>();

	static public Formazione create(Formazione f) throws SQLException {
		Connection cn = DBManager.getConnection();
		PreparedStatement s = cn.prepareStatement(
				"INSERT INTO FORMAZIONI (CODICECIRCUITO, CODICECOMPONENTE, NUMEROUNITA) VALUES (?,?,?)");
		s.setInt(1, f.getCodiceCircuitoStampato());
		s.setInt(2, f.getCodiceComponente());
		s.setFloat(3, f.getNumeroUnitaImpiegate());

		s.executeUpdate();
		s.close();

		prodottiCaricati.put(f.getCodiceCircuitoStampato(), f);
		return f;
	}

	static public Formazione read(int codice) throws SQLException {
		Connection c = DBManager.getConnection();
		Formazione f = null;
		PreparedStatement s = c.prepareStatement("SELECT * FROM FORMAZIONI WHERE CODICECIRCUITO=?");
		s.setInt(1, codice);
		ResultSet rs = s.executeQuery();
		if (rs.first()) {
			if (!rs.wasNull()) {
				f = new Formazione(rs.getInt("CODICECIRCUITO"), rs.getInt("CODICECOMPONENTE"),
						rs.getInt("NUMEROUNITA"));
				prodottiCaricati.put(f.getCodiceCircuitoStampato(), f);
			}
		}
		return f;
	}

	public static void update(Formazione f) throws SQLException {
		Formazione tmp = Formazione_DAO.read(f.getCodiceCircuitoStampato());
		if (tmp == null) {
			Formazione nuovaFormazione = Formazione_DAO.create(f);
			prodottiCaricati.put(nuovaFormazione.getCodiceCircuitoStampato(), nuovaFormazione);
		}
		Connection conn = DBManager.getConnection();
		PreparedStatement stmt = conn.prepareStatement("UPDATE FORMAZIONI SET NUMEROUNITA=? WHERE CODICECIRCUITO=?");
		stmt.setInt(1, f.getNumeroUnitaImpiegate());
		stmt.setInt(2, f.getCodiceCircuitoStampato());
		stmt.executeUpdate();

	}

	static public void delete(Formazione f) throws SQLException {
		if (Formazione_DAO.read(f.getCodiceCircuitoStampato()) != null) {
			Connection c = DBManager.getConnection();
			PreparedStatement stmt = c.prepareStatement("DELETE FROM FORMAZIONI WHERE CODICECIRCUITO=?");
			stmt.setInt(1, f.getCodiceCircuitoStampato());
			stmt.executeUpdate();
			stmt.close();
			prodottiCaricati.remove(f);
		}
	}

	public static ArrayList<Integer> readAllComp(int cod) throws SQLException {
		Connection conn = DBManager.getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM FORMAZIONI WHERE CODICECIRCUITO=?");
		preparedStatement.setInt(1, cod);
		ArrayList<Integer> listaComponenti = new ArrayList<Integer>();
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			listaComponenti.add(rs.getInt("CODICECOMPONENTE"));
		}
		rs.close();
		preparedStatement.close();
		return listaComponenti;
	}

	public static ArrayList<Integer> readAllQta(int cod) throws SQLException {
		Connection conn = DBManager.getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM FORMAZIONI WHERE CODICECIRCUITO=?");
		preparedStatement.setInt(1, cod);
		ArrayList<Integer> listaNumeroUnita = new ArrayList<Integer>();
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			listaNumeroUnita.add(rs.getInt("NUMEROUNITA"));
		}
		rs.close();
		preparedStatement.close();
		return listaNumeroUnita;
	}
}
