package UI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import coreBusiness.exception.AnnoNotValidException;
import coreBusiness.exception.ContribuenteNotValidException;

public class UIContribuenteTest {

	private static UIContribuente UIContr;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		UIContr=new UIContribuente();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		UIContr=null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//TC2
	 @Test
	 (expected=ContribuenteNotValidException.class)
	 public final void ricercaDichiarazioneCFNonValido() throws ContribuenteNotValidException {
		 System.out.println("Inserisci codice fiscale: ");
		 UIContr.getCodFisInput();
	 }
	 
	 //TC4
	 @Test
	 (expected=AnnoNotValidException.class)
	 public final void ricercaDichiarazioneAnnoNonValido() throws AnnoNotValidException {
		 System.out.println("Inserisci anno fiscale: ");
		 UIContr.getAnnoInput();
	 }
	 
	 //TC5
	 @Test
	 (expected=NumberFormatException.class)
	 public final void ricercaDichiarazioneAnnoCaratteri() throws NumberFormatException, AnnoNotValidException {
		 System.out.println("Inserisci anno fiscale: ");
		 UIContr.getAnnoInput();
	 }
	 
}
