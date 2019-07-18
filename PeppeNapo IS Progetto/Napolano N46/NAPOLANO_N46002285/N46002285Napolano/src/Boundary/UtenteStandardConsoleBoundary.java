package Boundary;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

//import Boundary.UtenteStandardConsoleBoundary.SelectionOptions;
import Control.GestoreStreamingMusica;
import Entity.PlayList;
import Entity.Brano;

public class UtenteStandardConsoleBoundary {

	private Scanner input;

	public UtenteStandardConsoleBoundary() {
		input = new Scanner(System.in);
	}

	private static enum SelectionOptions {
		CREA_PLAYLIST, ESCI
	}

	private static enum SelectionOptions2 {
		CREAZIONE_PUNTEGGIO, CREAZIONE_BASE_ASCOLTO, ESCI
	}

	private SelectionOptions chooseOption(Scanner input) throws IOException {
		while (true) {
			System.out.println("\nMenu\n");
			System.out.println("\n0. Esci dal programma.\n");
			System.out.println("1. CreaPlaylist\n");
			System.out.println("Scelta : ");

			String value = input.nextLine();
			SelectionOptions select = null;
			try {
				int optionValue = Integer.parseInt(value);
				switch (optionValue) {
				case 0:
					select = SelectionOptions.ESCI;
					break;
				case 1:
					select = SelectionOptions.CREA_PLAYLIST;
					break;

				default:
					System.out.print("Nessuna funzione associata alla selezione! Riprova.\n\n");
				}
			} catch (NumberFormatException e) {
				System.err.println("Valore non valido. Riprova.\n");
			}
			return select;
		}
	}

	public String Login() throws SQLException {
		String Utente = new String();
		do {
			System.out.println("Inserisci Username");
			String Username = input.nextLine();
			System.out.println("Inserisci Password");
			String password = input.nextLine();
			Utente = GestoreStreamingMusica.getInstance().Login(Username, password);

			if (Utente.equals("valorenullo")) {
				System.out.println("Password o Username Non Corretta RIPROVA\n");

			}
		} while (Utente.equals("valorenullo"));
		return Utente;
	}

	public void showBoundary() throws IOException, SQLException {

		String Utente = new String();
		Utente = Login();
		SelectionOptions selected = null;
		SelectionOptions2 selected2 = null;
		String tipo = null;
		String Target = null;
		while (true) {
			selected = chooseOption(input);// SelectionOptions.CREA_PLAYLIST;chooseOption(input);
			selected2 = chooseOption2(input);// SelectionOptions2.CREAZIONE_PUNTEGGIO;
			if (selected == SelectionOptions.ESCI) {
				return;
			}

			if (selected == SelectionOptions.CREA_PLAYLIST) {

				if (selected2 == SelectionOptions2.CREAZIONE_BASE_ASCOLTO) {
					tipo = new String("BaseAscolto");
				} else if (selected2 == SelectionOptions2.CREAZIONE_PUNTEGGIO) {
					tipo = new String("BasePunteggio");
				} else {
					return;
				}
				System.out.println("Inserisci Target");

				Target = input.nextLine();
				CreaPlaylist(tipo, Target, Utente);

			}

			else {
				input.close();
				return;
			}

		}
	}

	private SelectionOptions2 chooseOption2(Scanner input) throws IOException {
		while (true) {
			System.out.println("\nMenuCreaPlayList\n");
			System.out.println("\n0. Esci dal programma.\n");
			System.out.println("Scegli Creazione PlayList\n");
			System.out.println("1. :Creazione su Punteggio\n");
			System.out.println("2.:Creazione su NumeroAscolti \n");

			String value = input.nextLine();
			SelectionOptions2 select = null;
			try {
				int optionValue = Integer.parseInt(value);
				switch (optionValue) {
				case 0:
					select = SelectionOptions2.ESCI;
					break;
				case 1:
					select = SelectionOptions2.CREAZIONE_PUNTEGGIO;
					break;
				case 2:
					select = SelectionOptions2.CREAZIONE_BASE_ASCOLTO;
					break;

				default:
					System.out.print("Nessuna funzione associata alla selezione! Riprova.\n\n");
					System.out.println("Scrivi Target\n");

				}
			} catch (NumberFormatException e) {
				System.err.println("Valore non valido. Riprova.\n");
			}
			return select;
		}
	}

	private String InserisciTitoloPlaylist() {

		System.out.println("Inserisci Titolo PlayList");
		String value = this.input.nextLine();
		return value;
	}

	private boolean VerificaTitolo(String titoloUtente, String utente) throws SQLException {

		return GestoreStreamingMusica.getInstance().VerificaTitolo(titoloUtente, utente);

	}

	private void CreaPlaylist(String tipo, String Target, String Utente) throws SQLException {
		String titoloPlayListUtente;
		ArrayList<Brano> listaBrani = GestoreStreamingMusica.getInstance().CreaPlayList(tipo, Target);
		System.out.println("ListaBrani pronta per la PlayList:\n");
		for (int i = 0; i < listaBrani.size(); i++) {
			System.out.println("Brano:" + listaBrani.get(i));
		}

		boolean EsitoMemorizzazione;
		boolean verificaTitolo;

		do {
			titoloPlayListUtente = InserisciTitoloPlaylist();
			verificaTitolo = VerificaTitolo(titoloPlayListUtente, Utente);
			if (verificaTitolo == true) {
				System.out.println("L'utente ha già una PlayList con quel Titolo\n");
				System.out.println("Inserire un nuovo Titolo\n");
			}
		} while (verificaTitolo == true);
		EsitoMemorizzazione = MemorizzaPlaylist(titoloPlayListUtente, listaBrani, Utente);
		if (EsitoMemorizzazione == true) {
			System.out.println("PlayList Memorizzata Correttamente!");
		}
	}

	private boolean MemorizzaPlaylist(String titoloPlayListUtente, ArrayList<Brano> listaBrani, String Utente)
			throws SQLException {

		return GestoreStreamingMusica.getInstance().MemorizzaPlaylist(titoloPlayListUtente, Utente, listaBrani);

	}

}
