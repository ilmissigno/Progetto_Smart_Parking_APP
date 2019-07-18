package Test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Statement;
import java.util.ArrayList;

import Control.GestoreStreamingMusica;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

public class Test2 {

	// Test di verifica che il Titolo della PlayList scelta dall'utente non sia ad
	// egli già assegnata

	private static GestoreStreamingMusica instance;
	private String titoloPlayList;
	private String Utente;

	@Before
	public void SetUp() throws Exception {
		this.titoloPlayList = "LEOPLAY";
		this.Utente = "1";
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
		instance = GestoreStreamingMusica.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {

		boolean result = instance.VerificaTitolo(titoloPlayList, Utente);
		assertTrue("TitoloGiaPresente", result);

	}

}
