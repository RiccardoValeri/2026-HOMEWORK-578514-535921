package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LabirintoBuilderTest {

    private Labirinto.LabirintoBuilder builder;

    @Before
    public void setUp() {
        builder = Labirinto.newBuilder();
    }

    @Test
    public void testAddStanzaIniziale() {
        Labirinto labirinto = builder.addStanzaIniziale("Atrio").getLabirinto();
        assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
    }

    @Test
    public void testAddStanzaVincente() {
        Labirinto labirinto = builder.addStanzaVincente("Salotto").getLabirinto();
        assertEquals("Salotto", labirinto.getStanzaVincente().getNome());
    }

    @Test
    public void testAddAdiacenza() {
        builder.addStanzaIniziale("Atrio")
               .addStanza("Cucina")
               .addAdiacenza("Atrio", "Cucina", "nord");
        
        Stanza atrio = builder.getListaStanze().get("Atrio");
        assertEquals("Cucina", atrio.getStanzaAdiacente(Direzione.nord).getNome());
    }

    @Test
    public void testAddAttrezzo() {
        builder.addStanzaIniziale("Atrio")
               .addAttrezzo("chiave", 1);
        
        assertTrue(builder.getListaStanze().get("Atrio").hasAttrezzo("chiave"));
    }

    @Test
    public void testBuildLabirintoCompleto() {
        Labirinto labirinto = builder
                .addStanzaIniziale("Atrio")
                .addStanzaVincente("Salotto")
                .addAdiacenza("Atrio", "Salotto", "nord")
                .addAttrezzo("martello", 3)
                .getLabirinto();
        
        assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
        assertEquals("Salotto", labirinto.getStanzaVincente().getNome());
        assertEquals("Salotto", labirinto.getStanzaCorrente().getStanzaAdiacente(Direzione.nord).getNome());
        assertTrue(labirinto.getStanzaVincente().hasAttrezzo("martello"));
    }
    
    @Test
    public void testAddStanzaMagica() {
        builder.addStanzaMagica("Laboratorio", 1);
        Stanza s = builder.getListaStanze().get("Laboratorio");
        assertTrue(s instanceof StanzaMagica);
    }
    
    @Test
    public void testLabirintoDueStanze() {
        Labirinto labirinto = builder
                .addStanzaIniziale("Ingresso")
                .addStanzaVincente("Uscita")
                .addAdiacenza("Ingresso", "Uscita", "nord")
                .getLabirinto();
        
        assertEquals("Ingresso", labirinto.getStanzaCorrente().getNome());
        
        Stanza prossimaStanza = labirinto.getStanzaCorrente().getStanzaAdiacente(Direzione.nord);
        assertEquals("Uscita", prossimaStanza.getNome());
        assertEquals(prossimaStanza, labirinto.getStanzaVincente());
    }
}