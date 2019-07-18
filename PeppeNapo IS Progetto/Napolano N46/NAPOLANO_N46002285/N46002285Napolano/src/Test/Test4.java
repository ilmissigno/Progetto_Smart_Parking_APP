package Test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import Control.GestoreStreamingMusica;
import Entity.Brano;

public class Test4 {

	// Test che verifica memorizzazione con PlayListInBaseTarget

	private static GestoreStreamingMusica instance;
	private String titoloPlayList;
	private String Target;
	private String Utente;
	private String Tipo;

	@Before
	public void SetUp() throws Exception {
		this.titoloPlayList = "MaradonaPlaylist";
		this.Target = "122";// target che non sarà presente!
		this.Utente = "1";
		this.Tipo = "BasePunteggio";
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
		ArrayList<Brano> ListaBrani = instance.CreaPlayList(this.Tipo, this.Target);// non verifico l'assert
		boolean memorizzazioneResult = instance.MemorizzaPlaylist(this.titoloPlayList, this.Utente, ListaBrani);
		assertFalse("ValoreMemorizzato!", memorizzazioneResult);

	}

}