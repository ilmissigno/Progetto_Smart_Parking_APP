package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import prodotti.GestoreProdotti;

public class Main {
	public static GestoreProdotti gp = GestoreProdotti.getIstance();

	public void ricercaCircuitiStampati(int codice) throws SQLException {
		if (codice < 0) {
			System.out.println("Formato codice non valido");
			System.exit(0);
		}
		ArrayList<Integer> app = gp.ricercaComponenti(codice);
		ArrayList<Integer> app2 = gp.ricercaQta(codice);
		if ((app2.size() == 0) && (app.size() == 0)) {
			System.out.println("Prodotto non trovato");
		}
		for (int i = 0; i < app.size(); i++) {
			System.out.println("codice componente: " + app.get(i) + " quantita: " + app2.get(i) + "\n");
		}
	}

	public static void main(String[] args) throws SQLException {
		Scanner input = new Scanner(System.in);
		System.out.println("Inserisci il codice del circuito: ");
		int codice = input.nextInt();
		Main ui = new Main();
		ui.ricercaCircuitiStampati(codice);
		input.close();
	}

}
