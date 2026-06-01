package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.*;

public class BorsaTest {

	private Borsa borsa;
	private Attrezzo piombo;
	private Attrezzo piuma;
	private Attrezzo libro;

	@Before
	public void setUp() {
		borsa = new Borsa(20);
		piombo = new Attrezzo("piombo", 10);
		piuma = new Attrezzo("piuma", 1);
		libro = new Attrezzo("libro", 5);
	}

	@Test
	public void testGetContenutoOrdinatoPerNome() {
		borsa.addAttrezzo(piombo);
		borsa.addAttrezzo(piuma);
		borsa.addAttrezzo(libro);
		
		// Otteniamo il set ordinato (libro, piombo, piuma)
		SortedSet<Attrezzo> setPerNome = borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = setPerNome.iterator();
		
		assertEquals(libro, it.next());
		assertEquals(piombo, it.next());
		assertEquals(piuma, it.next());
	}

	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		borsa.addAttrezzo(piombo); // 10
		borsa.addAttrezzo(piuma);  // 1
		borsa.addAttrezzo(libro);  // 5
		
		List<Attrezzo> listaPerPeso = borsa.getContenutoOrdinatoPerPeso();
		
		assertEquals(piuma, listaPerPeso.get(0));
		assertEquals(libro, listaPerPeso.get(1));
		assertEquals(piombo, listaPerPeso.get(2));
	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Attrezzo incudine = new Attrezzo("incudine", 10); // stesso peso del piombo
		borsa.addAttrezzo(piombo);
		borsa.addAttrezzo(incudine);
		borsa.addAttrezzo(piuma);
		
		Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();
		
		// Verifichiamo che per il peso 10 ci siano due attrezzi
		assertEquals(2, mappa.get(10).size());
		assertTrue(mappa.get(10).contains(piombo));
		assertTrue(mappa.get(10).contains(incudine));
		// Verifichiamo che per il peso 1 ci sia solo la piuma
		assertEquals(1, mappa.get(1).size());
	}
}