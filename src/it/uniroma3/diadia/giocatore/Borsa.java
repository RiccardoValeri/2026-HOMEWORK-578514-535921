package it.uniroma3.diadia.giocatore;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Configurazione;

public class Borsa {
	private int pesoMassimo;
	private Map<String, Attrezzo> attrezzi;

	public Borsa() {
		this(Configurazione.getPesoMaxBorsa());
	}

	public Borsa(int pesoMassimo) {
		this.pesoMassimo = pesoMassimo;
		this.attrezzi = new HashMap<>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null) return false;
		if (this.getPeso() + attrezzo.getPeso() > this.pesoMassimo)
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	public int getPesoMassimo() {
		return pesoMassimo;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int pesoTotale = 0;
		for (Attrezzo a : this.attrezzi.values()) {
			pesoTotale += a.getPeso();
		}
		return pesoTotale;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMassimo() + "kg): ");
			for (Attrezzo a : this.attrezzi.values()) {
				s.append(a.toString() + " ");
			}
		} else {
			s.append("Borsa vuota");
		}
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> ordinata = new ArrayList<>(this.attrezzi.values());
		Collections.sort(ordinata, new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if (a1.getPeso() != a2.getPeso())
					return a1.getPeso() - a2.getPeso();
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		return ordinata;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		return new TreeSet<>(this.attrezzi.values());
	}

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> peso2attrezzi = new HashMap<>();

		for (Attrezzo a : this.attrezzi.values()) {
			int peso = a.getPeso();
			if (!peso2attrezzi.containsKey(peso)) {
				peso2attrezzi.put(peso, new HashSet<Attrezzo>());
			}
			peso2attrezzi.get(peso).add(a);
		}
		return peso2attrezzi;
	}

	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> s = new TreeSet<>(new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if (a1.getPeso() != a2.getPeso())
					return a1.getPeso() - a2.getPeso();
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		s.addAll(this.attrezzi.values());
		return s;
	}
}