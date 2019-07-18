package coreBusiness.modelliDichiarazione;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import coreBusiness.exception.ModelloNotFoundException;

public class GestoreModelliDichiarazioneTest {
	static GestoreModelliDichiarazione gestore;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gestore=new GestoreModelliDichiarazione();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		gestore=null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
	//TC3
		 @Test
		 (expected=ModelloNotFoundException.class)
		 public final void ricercaDichiarazioneCFNull() throws ModelloNotFoundException {
			 @SuppressWarnings("unused")
			 ModelloDichiarazione md=gestore.getModello(null, 2015);
		 }
	//TC6
		 @Test
		 (expected=ModelloNotFoundException.class)
		 public final void ricercaModelloDichiarazioneNonPresente() throws ModelloNotFoundException {
			@SuppressWarnings("unused")
			ModelloDichiarazione md=gestore.getModello("12345", 2010);
		 }
	//TC1
		 @Test
		 public final void ricercaModelloDichiarazione() throws ModelloNotFoundException{	 
			 ModelloDichiarazione md=new ModelloDichiarazione();
			 md=gestore.getModello("12345", 2015);
			 assertTrue(gestore.calcolaImpostaIRPEF(md)==(double)(388.5));		 
		 }

}
