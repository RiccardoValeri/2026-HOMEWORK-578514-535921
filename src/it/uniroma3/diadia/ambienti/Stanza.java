package it.uniroma3.diadia.ambienti;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class Stanza {

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashMap<>();
		this.stanzeAdiacenti = new HashMap<>();
	}

	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		this.stanzeAdiacenti.put(direzione, stanza);
	}

	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		return this.toString();
	}

	public Collection<Attrezzo> getAttrezzi() {
		return this.attrezzi.values();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.nome);
		s.append("\nUscite: ");
		for (Direzione direzione : this.stanzeAdiacenti.keySet()) {
			s.append(" " + direzione.name());
		}
		s.append("\nAttrezzi nella stanza: ");
		for (Attrezzo a : this.attrezzi.values()) {
			s.append(a.toString() + " ");
		}
		return s.toString();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public boolean removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo) != null;
	}

	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public List<Stanza> getStanzeAdiacenti() {
		return new ArrayList<>(this.stanzeAdiacenti.values());
	}
}