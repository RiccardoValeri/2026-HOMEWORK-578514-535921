package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio("Stanza corrente: " + partita.getLabirinto().getStanzaCorrente().getDescrizione());
		this.getIo().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		this.getIo().mostraMessaggio("CFU residui: " + partita.getGiocatore().getCfu());
	}
}