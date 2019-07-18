package coreBusiness.GestioneFumetti;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import coreBusiness.exception.FumettiNotFoundException;

public class GestoreBusinessTest {
	
	static GestoreBusiness gb;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gb = new GestoreBusiness();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		gb = null;
	}

	@Test
	(expected = FumettiNotFoundException.class)
	public void fumettiNonTrovati() throws FumettiNotFoundException {
		@SuppressWarnings("unused")
		Prenotazione p = gb.inviaEmailRiepilogo();
	}
}
