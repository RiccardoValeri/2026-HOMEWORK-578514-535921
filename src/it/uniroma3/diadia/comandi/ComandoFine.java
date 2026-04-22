package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoFine implements Comando {
    private final static String NOME = "fine";

    @Override
    public void esegui(Partita partita) {
        IO io = partita.getIo();
        io.mostraMessaggio("Grazie di aver giocato!");  // Messaggio di addio
        partita.setFinita(); // Imposta lo stato della partita su finita
    }

    @Override
    public void setParametro(String parametro) {
        // Non serve parametro
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