package Terapia;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("unused")
public class GestoreTerapiaTest {
	static GestoreTerapia gestore;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gestore= new GestoreTerapia();
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

	@SuppressWarnings("deprecation")
	
	@Test
	public void testCase1() {
		assertFalse(gestore.VisualizzaTerapieInIntervalloDiTempo(new java.util.Date(2014-1900,1-1,01),new java.util.Date(2014-1900,1-1,20)).size()!=0);
	}	
	@SuppressWarnings("deprecation")
	
	@Test
	public void testCase2(){
		assertFalse(gestore.VisualizzaTerapieInIntervalloDiTempo(new java.util.Date(2014-1900,1-1,21),new java.util.Date(2014-1900,1-1,20)).size()!=0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCase3(){
		assertTrue(gestore.VisualizzaTerapieInIntervalloDiTempo(new java.util.Date(2016-1900,1-1,01),new java.util.Date(2016-1900,1-1,15)).size()!=0);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testCase4(){
		assertFalse(gestore.VisualizzaTerapieInIntervalloDiTempo(new java.util.Date(2014-1900,1-1,01),new java.util.Date(2016-1900,1-1,20)).size()!=0);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testCase5(){
		assertFalse(gestore.VisualizzaTerapieInIntervalloDiTempo(new java.util.Date(2016-1900,1-1,15),new java.util.Date(2020-1900,1-1,20)).size()!=0);
	}
}
