package it.uniroma3.diadia.ambienti;

/**
 * Una stanza bloccata ha una direzione che non è percorribile
 * se nella stanza non è presente un attrezzo specifico che funge da chiave.
 */
public class StanzaBloccata extends Stanza {

    private String direzioneBloccata;
    private String attrezzoChiave;

    public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoChiave) {
        super(nome);
        this.direzioneBloccata = direzioneBloccata;
        this.attrezzoChiave = attrezzoChiave;
    }

    @Override
    public Stanza getStanzaAdiacente(String direzione) {
        // Se la direzione è quella bloccata e manca l'attrezzo chiave...
        if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(this.attrezzoChiave)) {
            // Restituisce la stanza stessa (rimani dove sei)
            return this;
        }
        return super.getStanzaAdiacente(direzione);
    }

    @Override
    public String getDescrizione() {
        String base = super.getDescrizione();
        if (!this.hasAttrezzo(this.attrezzoChiave)) {
            return base + "\nDirezione bloccata: " + this.direzioneBloccata + 
                   ". Ti serve un/una " + this.attrezzoChiave + " per passare.";
        }
        return base;
    }
}