package Test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Statement;
import java.util.ArrayList;

import Control.GestoreStreamingMusica;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import Entity.Brano;

public class Test3 {

	// Test che verifica come si comporta il sistema in assenza del target
	// specificato

	private static GestoreStreamingMusica instance;
	private String tipo;
	private String Target;

	@Before
	public void SetUp() throws Exception {
		this.tipo = "BasePunteggio";
		this.Target = "122";// target che non sarà presente
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
		ArrayList<Brano> ListaBrani = instance.CreaPlayList(this.tipo, this.Target);
		assertEquals("errore", 0, ListaBrani.size());

	}

}