package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {

	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;
	private IO io; // Aggiungi questo campo [cite: 37]

	// Il costruttore ora deve accettare IO 
	public Partita(IO io){
		this.labirinto = new Labirinto();
		this.giocatore = new Giocatore();
		this.finita = false;
		this.io = io; 
	}
	
	// Aggiungi questo metodo getter per i comandi
	public IO getIo() {
		return this.io;
	}

	public Labirinto getLabirinto() {
		return labirinto;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}
	
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}

	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	public void setFinita() {
		this.finita = true;
	}
}