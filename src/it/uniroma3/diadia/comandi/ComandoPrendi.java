package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzo = partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.getParametro());
		if (attrezzo == null) {
			this.getIo().mostraMessaggio("Attrezzo non presente nella stanza.");
			return;
		}
		if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
			partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo.getNome());
			this.getIo().mostraMessaggio("Hai preso: " + attrezzo.getNome());
		} else {
			this.getIo().mostraMessaggio("Borsa troppo piena!");
		}
	}
}