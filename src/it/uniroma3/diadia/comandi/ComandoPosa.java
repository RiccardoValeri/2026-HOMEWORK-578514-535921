package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
		if (attrezzo == null) {
			this.getIo().mostraMessaggio("Attrezzo non presente nella borsa.");
			return;
		}
		if (partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo)) {
			partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
			this.getIo().mostraMessaggio("Hai posato: " + attrezzo.getNome());
		} else {
			this.getIo().mostraMessaggio("Non c'è spazio in questa stanza!");
		}
	}
}