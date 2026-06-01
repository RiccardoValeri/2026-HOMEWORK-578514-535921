package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean salutato;

	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.salutato = false;
	}

	public String getNome() {
		return nome;
	}

	public boolean giaSalutato() {
		return salutato;
	}

	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono " + this.nome + ". ");
		if (!salutato) {
			risposta.append(this.presentazione);
			this.salutato = true;
		} else {
			risposta.append("Ci siamo già salutati!");
		}
		return risposta.toString();
	}

	public abstract String agisci(Partita partita);
	
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);

	@Override
	public String toString() {
		return this.getNome();
	}
}