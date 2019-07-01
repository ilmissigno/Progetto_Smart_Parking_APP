/**
 * 
 */
package ui;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Raffaele
 *
 */
public class UiUtenteRegistratoTest {
	static UIUtenteRegistrato ui;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ui=new UIUtenteRegistrato();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ui=null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.setIn(System.in);
	}

	@Test
	public final void selezionePortoErrata() {
		assertEquals(ui.impostaPorto(6),null);
	}
	@Test(expected=NumberFormatException.class)
	public final void immettiStringaoptintero() {
		ByteArrayInputStream in = new ByteArrayInputStream("Proviamo con una stringa".getBytes());
		System.setIn(in);
		assertEquals(ui.getFormattedInteger(),null);
	}
	@Test
	public final void immettiintero() {
		ByteArrayInputStream in = new ByteArrayInputStream("42".getBytes());
		System.setIn(in);
		assertEquals(ui.getFormattedInteger(),new Integer(42));
	}
	@Test
	public final void immettiinteromax() {
		ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(Integer.MAX_VALUE).getBytes());
		System.setIn(in);
		assertEquals(ui.getFormattedInteger(),new Integer(Integer.MAX_VALUE));
	}
	@Test
	public final void immettiinteromin() {
		ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(Integer.MIN_VALUE).getBytes());
		System.setIn(in);
		assertEquals(ui.getFormattedInteger(),new Integer(Integer.MIN_VALUE));
	}
	@Test(expected=NumberFormatException.class)
	public final void immettioltreinteromax() {
		ByteArrayInputStream in = new ByteArrayInputStream("21474836470".getBytes());
		System.setIn(in);
		assertEquals(ui.getFormattedInteger(),null);
	}
	
	
	@Test
	public final void formatoDataOk() {
		assertEquals(ui.formattaData(2013, 1, 1).toString(),"2013-01-01");
	}
	@Test
	public final void dataOutofRange() {
		assertEquals(ui.formattaData(1900, 2, 1),null);
	}
	@Test
	public final void DataOutofRangeforMonth() {
		assertEquals(ui.formattaData(2013, 2, 31).toString(),"2013-02-31");
	}

}
