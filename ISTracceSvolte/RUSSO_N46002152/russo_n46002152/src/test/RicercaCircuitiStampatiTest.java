package test;

import static org.junit.Assert.*;
import java.sql.SQLException;
import prodotti.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RicercaCircuitiStampatiTest {

	private GestoreProdotti gp;

	@Before
	public void setUp() throws Exception {
		// prima dei test inserisco un prodotto di prova
		gp = GestoreProdotti.getIstance();
	}

	@After
	public void tearDown() throws Exception {
		// rimuovo la tupla di test dal database
		gp = null;
	}

	@Test
	public void ricercaProdOk() throws SQLException {
		assertTrue((gp.ricercaComponenti(1).size() != 0) && (gp.ricercaQta(1).size() != 0));
	}

	@Test
	public void ricercaProdNonPresente() throws SQLException {
		assertTrue((gp.ricercaComponenti(3).size() == 0) && (gp.ricercaQta(3).size() == 0));
	}

	@Test
	public void ricercaProdInputNegativo() throws SQLException {
		assertTrue((gp.ricercaComponenti(-2).size() == 0) && (gp.ricercaQta(-2).size() == 0));
	}

}
