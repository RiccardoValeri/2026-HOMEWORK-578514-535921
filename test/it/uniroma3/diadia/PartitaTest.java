package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.ambienti.Labirinto;

public class PartitaTest {

    private Partita partita;

    @Before
    public void setUp() {
        Labirinto labirinto = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanzaVincente("Biblioteca")
                .getLabirinto();
        this.partita = new Partita(labirinto);
    }

    @Test
    public void testVinta() {
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