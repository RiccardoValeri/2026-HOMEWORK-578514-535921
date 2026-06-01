package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Orco extends AbstractPersonaggio {
    private static final String MESSAGGIO_BRUTTO = "Grazie per il regalo, ma non mi serve! Lo butto via.";
    
    public Orco(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
    public String agisci(Partita partita) {
        if (this.giaSalutato()) {
            return "Mi hai salutato, quindi non ti picchio. Ma non ho nulla da darti!";
        } else {
            return "Sei un maleducato! Ti meriti una randellata.";
        }
    }

    @Override
    public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
        // L'orco semplicemente distrugge l'attrezzo
        return MESSAGGIO_BRUTTO;
    }
}