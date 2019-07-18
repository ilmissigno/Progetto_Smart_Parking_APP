package prodotti;

import java.sql.SQLException;
import java.util.*;
import dao.Formazione_DAO;

public class GestoreProdotti implements IGestoreProdotti {

	public static GestoreProdotti getIstance() {
		if (istance == null)
			istance = new GestoreProdotti();
		return istance;
	}

	protected GestoreProdotti() {
	}

	private static GestoreProdotti istance;

	/**
	 * @return
	 */

	public void inserisciFormazione(Formazione f) {
		// prodotti.put(f.nome, f);
		try {
			Formazione_DAO.create(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore nella scrittura del prodotto sul database.");
		}
	}

	public void eliminaFormazione(Formazione f) {
		try {
			Formazione_DAO.delete(f);
		} catch (SQLException ex) {
			System.out.println("Errore interno alla base di dati. Impossibile eliminare il prodotto nel database.");
		}
	}

	@Override
	public Set<AProdotto> ottieniProdotti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificaProdotto(int cod, String prod, float prezzo) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Integer> ricercaComponenti(int cod) throws SQLException {
		ArrayList<Integer> listaComponenti = new ArrayList<Integer>();

		ArrayList<Integer> app = Formazione_DAO.readAllComp(cod);
		for (int i = 0; i < app.size(); i++) {
			listaComponenti.add(app.get(i));
		}
		return listaComponenti;
	}

	public ArrayList<Integer> ricercaQta(int cod) throws SQLException {
		ArrayList<Integer> listaQta = new ArrayList<Integer>();
		ArrayList<Integer> app = Formazione_DAO.readAllQta(cod);
		for (int i = 0; i < app.size(); i++) {
			listaQta.add(app.get(i));
		}
		return listaQta;
	}

}