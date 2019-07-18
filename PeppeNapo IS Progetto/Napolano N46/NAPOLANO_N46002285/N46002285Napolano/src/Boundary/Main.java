package Boundary;

import java.sql.SQLException;

import Boundary.ApplicationConsoleBoundary.TerminationState;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		ApplicationConsoleBoundary applicationConsoleBoundary = new ApplicationConsoleBoundary();
		TerminationState exitStatus = applicationConsoleBoundary.runApplication();

		if (exitStatus == TerminationState.CORRECT_TERMINATION) {
			System.exit(0);
		} else
			System.exit(-1);
	}
}


