package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private Stanza stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() {
		this.stanza = new Stanza("Ripostiglio");
		this.attrezzo = new Attrezzo("Martello", 2);
	}
	@Test
	void testAddAttrezzo_StanzaVuota() {
		assertTrue(this.stanza.addAttrezzo(attrezzo));
	}

	@Test
	void testAddAttrezzo_StanzaPiena() {
	
		for(int i=0; i<10; i++) {
			this.stanza.addAttrezzo(new Attrezzo("Attrezzo"+i, 1));
		}
		assertFalse(this.stanza.addAttrezzo(attrezzo)); 
	}

	@Test
	void testGetAttrezzo_Presente() {
		this.stanza.addAttrezzo(attrezzo);
		assertEquals(attrezzo, this.stanza.getAttrezzo("Martello"));
	}
}