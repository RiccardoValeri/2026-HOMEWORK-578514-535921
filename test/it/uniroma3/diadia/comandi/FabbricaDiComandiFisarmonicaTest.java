package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandiFisarmonica factory;

	@Before
	public void setUp() {
		this.factory = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testComandoVai() {
		Comando eseguito = factory.costruisciComando("vai nord");
		assertEquals("vai", eseguito.getNome());
		assertEquals("nord", eseguito.getParametro());
	}

	@Test
	public void testComandoNonValido() {
		Comando eseguito = factory.costruisciComando("pippo");
		assertEquals("non valido", eseguito.getNome());
	}
    
    @Test
	public void testComandoFine() {
		Comando eseguito = factory.costruisciComando("fine");
		assertEquals("fine", eseguito.getNome());
	}
}