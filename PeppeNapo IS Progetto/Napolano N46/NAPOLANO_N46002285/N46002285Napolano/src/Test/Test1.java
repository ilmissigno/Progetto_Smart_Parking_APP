package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.Statement;
import java.util.ArrayList;

import Control.GestoreStreamingMusica;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;

//Test di verifica del LOGIN grazie al quale capiamo chi è l'utente a cui dobbiamo assegnare 
//la nuova PlayList
public class Test1 {

	private static GestoreStreamingMusica instance;
	private String username;
	private String password;

	@Before
	public void SetUp() throws Exception {
		username = "MarioRossi";
		password = "mario";
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
		String valore = new String("valorenullo");
		String IdentificativoUtente = instance.Login(username, password);
		assertEquals("errore", valore, IdentificativoUtente);

	}

}
