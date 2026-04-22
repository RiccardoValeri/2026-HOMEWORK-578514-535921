package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {
	
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	private static final int SOGLIA_DEFAULT = 3;

	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_DEFAULT);
	}

	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica) {
			
			// Logica di modifica attrezzo (stessa di prima)
			StringBuilder nomeInvertito = new StringBuilder(attrezzo.getNome());
			nomeInvertito.reverse();
			int pesoRaddoppiato = attrezzo.getPeso() * 2;
			attrezzo = new Attrezzo(nomeInvertito.toString(), pesoRaddoppiato);
		}
		
		// ACCESSO DIRETTO: Posso farlo perché i campi sono PROTECTED
		if (this.numeroAttrezzi < this.attrezzi.length) {
			this.attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		return false;
	}
}