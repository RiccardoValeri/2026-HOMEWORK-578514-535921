package it.uniroma3.diadia.ambienti;

/**
 * Una stanza buia è una stanza che non permette di vedere la sua descrizione
 * se non contiene un attrezzo specifico che fa luce.
 */
public class StanzaBuia extends Stanza {

    private String attrezzoPerVedere;

    public StanzaBuia(String nome, String attrezzoPerVedere) {
        super(nome);
        this.attrezzoPerVedere = attrezzoPerVedere;
    }

    @Override
    public String getDescrizione() {
        if (!this.hasAttrezzo(this.attrezzoPerVedere)) {
            return "qui c'è buio pesto";
        }
        return super.getDescrizione();
    }
}