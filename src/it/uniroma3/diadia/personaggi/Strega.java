package it.uniroma3.diadia.personaggi;

import java.util.List;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

    public Strega(String nome, String presentazione) {
        super(nome, presentazione);
    }

    @Override
    public String agisci(Partita partita) {
        List<Stanza> adiacenti = partita.getLabirinto().getStanzaCorrente().getStanzeAdiacenti();
        Stanza destinazione = adiacenti.get(0);
        
        if (this.giaSalutato()) {
            // Cerca la stanza con più attrezzi
            for (Stanza s : adiacenti) {
                if (s.getAttrezzi().size() > destinazione.getAttrezzi().size())
                    destinazione = s;
            }
        } else {
            // Cerca la stanza con meno attrezzi
            for (Stanza s : adiacenti) {
                if (s.getAttrezzi().size() < destinazione.getAttrezzi().size())
                    destinazione = s;
            }
        }
        
        partita.getLabirinto().setStanzaCorrente(destinazione);
        return "Ti ho spostato dove meritavi!";
    }

    @Override
    public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
        return "Ihihihi! Grazie per il regalo, ma preferisco farti sparire!";
    }
}