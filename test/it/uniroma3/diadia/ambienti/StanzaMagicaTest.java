package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private StanzaMagica stanza;
	private Attrezzo attrezzo;

	@Before
	public void setUp() {
		// Soglia impostata a 1 per fare il test velocemente
		this.stanza = new StanzaMagica("Magica", 1);
		this.attrezzo = new Attrezzo("osso", 2);
	}

	@Test
	public void testAddAttrezzoSottoSoglia() {
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertEquals(attrezzo, stanza.getAttrezzo("osso"));
	}

	@Test
	public void testAddAttrezzoSopraSoglia() {
	    // Usiamo "chiave" così l'inversione è palese
	    Attrezzo chiave = new Attrezzo("chiave", 2);
	    
	    // 1. Posiamo il primo attrezzo (soglia è 1, quindi 1 > 1 è falso)
	    stanza.addAttrezzo(new Attrezzo("lanterna", 1)); 
	    
	    // 2. Posiamo il secondo attrezzo (2 > 1 è vero -> SCATTA LA MAGIA)
	    stanza.addAttrezzo(chiave); 
	    
	    // Verifichiamo: "chiave" non deve esserci, deve esserci "evaihc"
	    assertNull("L'attrezzo originale non dovrebbe esserci", stanza.getAttrezzo("chiave")); 
	    assertNotNull("Dovrebbe esserci l'attrezzo invertito", stanza.getAttrezzo("evaihc"));
	    
	    // Il peso deve essere raddoppiato (2 * 2 = 4)
	    assertEquals(4, stanza.getAttrezzo("evaihc").getPeso());
	}
}