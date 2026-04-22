package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PartitaTest {

	private Partita partita;
	private IO io;

	@Before
	public void setUp() {
		// Dobbiamo creare un IO per il costruttore di Partita
		// Possiamo usare una classe anonima o IOConsole
		this.io = new IOConsole(); 
		this.partita = new Partita(io);
	}

	@Test
	public void testVinta() {
		// All'inizio non è vinta perché siamo nell'atrio
		assertFalse(partita.vinta());
	}

	@Test
	public void testIsFinita_InizioPartita() {
		assertFalse(partita.isFinita());
	}

	@Test
	public void testSetFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}
    
    @Test
    public void testGetGiocatore() {
        assertNotNull(partita.getGiocatore());
    }

    @Test
    public void testGetLabirinto() {
        assertNotNull(partita.getLabirinto());
    }
}