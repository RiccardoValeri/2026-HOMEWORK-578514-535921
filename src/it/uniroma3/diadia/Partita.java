package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {

	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;

	// Modifica il costruttore per accettare il Labirinto
	public Partita(Labirinto labirinto){
		this.labirinto = labirinto;
		this.giocatore = new Giocatore();
		this.finita = false;
	}

	public Labirinto getLabirinto() {
		return labirinto;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}
	
	public boolean vinta() {
		return this.getLabirinto().getStanzaCorrente() == this.getLabirinto().getStanzaVincente();
	}

	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	public void setFinita() {
		this.finita = true;
	}
}