package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI = "Con chi vuoi interagire?";

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			this.getIo().mostraMessaggio(personaggio.agisci(partita));
		} else {
			this.getIo().mostraMessaggio(MESSAGGIO_CON_CHI);
		}
	}
}