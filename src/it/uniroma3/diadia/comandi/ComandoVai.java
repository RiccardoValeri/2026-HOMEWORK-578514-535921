package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if (this.getParametro() == null) {
			this.getIo().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		
		Direzione direzione;
		try {
			direzione = Direzione.valueOf(this.getParametro());
		} catch (IllegalArgumentException e) {
			this.getIo().mostraMessaggio("Direzione inesistente");
			return;
		}

		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.getIo().mostraMessaggio("Direzione inesistente");
			return;
		}
		
		partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		this.getIo().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}
}