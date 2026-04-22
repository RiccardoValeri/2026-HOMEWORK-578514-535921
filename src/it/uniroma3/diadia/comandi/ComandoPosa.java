package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
    private String nomeAttrezzo;
    private final static String NOME = "posa";

    @Override
    public void esegui(Partita partita) {
        IO io = partita.getIo();
        if (nomeAttrezzo == null) {
            io.mostraMessaggio("Quale attrezzo vuoi posare?");
            return;
        }

        // Verifichiamo se l'attrezzo è in borsa
        if (partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
            Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
            // Proviamo a metterlo nella stanza
            if (partita.getLabirinto().getStanzaCorrente().addAttrezzo(a)) {
                partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
                io.mostraMessaggio("Hai posato l'attrezzo: " + nomeAttrezzo);
            } else {
                io.mostraMessaggio("Non c'è più spazio in questa stanza per posare l'attrezzo.");
            }
        } else {
            io.mostraMessaggio("Non hai l'attrezzo " + nomeAttrezzo + " nella borsa.");
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