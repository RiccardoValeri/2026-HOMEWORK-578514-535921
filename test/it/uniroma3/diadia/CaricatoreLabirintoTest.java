package it.uniroma3.diadia;

import org.junit.Test;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Direzione;
import static org.junit.Assert.*;
import java.io.StringReader;

public class CaricatoreLabirintoTest {

    private final String labirintoMonolocale = 
        "Stanze: salotto\n" +
        "Inizio: salotto\n" +
        "Vincente: salotto\n" +
        "Attrezzi: tv 10 salotto\n" +
        "Uscite: \n";

    private final String labirintoBilocale = 
        "Stanze: salotto, camera\n" +
        "Inizio: salotto\n" +
        "Vincente: camera\n" +
        "Attrezzi: letto 10 camera\n" +
        "Uscite: salotto nord camera, camera sud salotto\n";

    @Test
    public void testCaricaMonolocale() throws Exception {
        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirintoMonolocale));
        caricatore.carica();
        Labirinto labirinto = caricatore.getLabirinto();
        
        assertEquals("salotto", labirinto.getStanzaCorrente().getNome());
        assertEquals("salotto", labirinto.getStanzaVincente().getNome());
    }

    @Test
    public void testCaricaBilocale() throws Exception {
        CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(labirintoBilocale));
        caricatore.carica();
        Labirinto labirinto = caricatore.getLabirinto();
        
        assertEquals("salotto", labirinto.getStanzaCorrente().getNome());
        assertEquals("camera", labirinto.getStanzaVincente().getNome());
        assertEquals("camera", labirinto.getStanzaCorrente().getStanzaAdiacente(Direzione.nord).getNome());
    }
}