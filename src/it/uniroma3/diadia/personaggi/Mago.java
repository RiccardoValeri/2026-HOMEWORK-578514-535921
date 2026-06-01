package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo != null) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = "Sei un vero signore! Ti ho lasciato un regalo nella stanza.";
		} else {
			msg = "Mi spiace, ho già esaurito i miei poteri magici!";
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		// Il mago dimezza il peso dell'attrezzo ricevuto e lo posa nella stanza
		Attrezzo attrezzoModificato = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2);
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoModificato);
		return "Grazie per il regalo! L'ho reso più leggero con la mia magia!";
	}
}