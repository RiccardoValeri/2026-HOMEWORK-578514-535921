package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private Direzione direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (this.direzioneBloccata == direzione && !this.hasAttrezzo(attrezzoSbloccante))
			return this;
		return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		String descrizioneBlocco = "";
		if (!this.hasAttrezzo(attrezzoSbloccante))
			descrizioneBlocco = "\nDirezione bloccata: " + direzioneBloccata.name() + 
								". Ti serve un " + attrezzoSbloccante + " per sbloccarla.";
		return super.getDescrizione() + descrizioneBlocco;
	}
}