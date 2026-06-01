package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			this.getIo().mostraMessaggio(personaggio.saluta());
		} else {
			this.getIo().mostraMessaggio("Non c'è nessuno da salutare qui.");
		}
	}
}