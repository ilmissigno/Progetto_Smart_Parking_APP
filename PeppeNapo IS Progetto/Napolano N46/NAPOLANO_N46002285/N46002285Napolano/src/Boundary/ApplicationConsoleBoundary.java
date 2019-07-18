package Boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

public class ApplicationConsoleBoundary {

	private BufferedReader consoleReader;
	private PrintWriter consoleWriter;

	public ApplicationConsoleBoundary() {

	}

	/**
	 * @throws IOException
	 * 
	 */
	private void doLogin() {

	}

	/**
	 * 
	 */
	private void doLogout() {
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public TerminationState runApplication() throws SQLException {
		UtenteStandardConsoleBoundary UtenteStandardConsoleBoundary = new UtenteStandardConsoleBoundary();
		try {
			UtenteStandardConsoleBoundary.showBoundary();
			return TerminationState.CORRECT_TERMINATION;
		} catch (IOException e) {
			handleException(e);
			return TerminationState.ABNORMAL_TERMINATION;
		}
	}

	/**
	 * @param e
	 */
	public void handleException(Exception e) {
		consoleWriter.format("Attenzione: Errore fatale nell'esecuzione: %s", e.getMessage());
		consoleWriter.flush();
	}

	public static enum TerminationState {
		CORRECT_TERMINATION, ABNORMAL_TERMINATION
	};

}
