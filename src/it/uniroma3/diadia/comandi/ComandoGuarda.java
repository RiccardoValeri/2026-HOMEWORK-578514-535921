package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoGuarda implements Comando {
    private final static String NOME = "guarda";

    @Override
    public void esegui(Partita partita) {
        IO io = partita.getIo();
        // Stampa descrizione stanza e stato partita (CFU e borsa)
        io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
        io.mostraMessaggio("CFU rimasti: " + partita.getGiocatore().getCfu());
        io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
    }

    @Override
    public void setParametro(String parametro) {
        // Non serve parametro per guarda
    }

    @Override
    public String getNome() {
        return NOME;
    }

    @Override
    public String getParametro() {
        return null;
    }
}