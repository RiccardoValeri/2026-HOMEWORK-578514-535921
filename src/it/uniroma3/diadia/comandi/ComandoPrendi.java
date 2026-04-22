package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe implementa il comando "prendi".
 * Un comando prendi tenta di prelevare un attrezzo dalla stanza corrente
 * e di inserirlo nella borsa del giocatore.
 */
public class ComandoPrendi implements Comando {
    private String nomeAttrezzo;
    private final static String NOME = "prendi";

    @Override
    public void esegui(Partita partita) {
        IO io = partita.getIo();
        
        if (nomeAttrezzo == null) {
            io.mostraMessaggio("Quale attrezzo vuoi prendere?");
            return;
        }

        // Recuperiamo la stanza corrente dal labirinto
        if (partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
            Attrezzo a = partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
            
            // Proviamo ad aggiungere l'attrezzo alla borsa del giocatore
            if (partita.getGiocatore().getBorsa().addAttrezzo(a)) {
                // Rimuoviamo l'attrezzo dalla stanza usando il NOME (Stringa) [Correzione errore]
                partita.getLabirinto().getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
                io.mostraMessaggio("Hai preso l'attrezzo: " + nomeAttrezzo);
            } else {
                io.mostraMessaggio("Borsa troppo piena! Non puoi prendere l'attrezzo: " + nomeAttrezzo);
            }
        } else {
            io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " non è presente in questa stanza.");
        }
    }

    @Override
    public void setParametro(String parametro) {
        this.nomeAttrezzo = parametro;
    }

    @Override
    public String getNome() {
        return NOME;
    }

    @Override
    public String getParametro() {
        return this.nomeAttrezzo;
    }
}