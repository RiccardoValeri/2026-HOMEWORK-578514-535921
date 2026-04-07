package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {

	private Partita partita;

	@BeforeEach
	void setUp() {
		this.partita = new Partita();
	}

	@Test
	void testIsFinita_AppenaIniziata() {
		assertFalse(this.partita.isFinita());
	}

	@Test
	void testIsFinita_CfuZero() {
		this.partita.getGiocatore().setCfu(0); // Testiamo lo stato limite [cite: 175]
		assertTrue(this.partita.isFinita());
	}

	@Test
	void testVinta_InAtrio() {
		// Sappiamo che l'atrio non è la biblioteca (uscita)
		assertFalse(this.partita.vinta());
	}
}