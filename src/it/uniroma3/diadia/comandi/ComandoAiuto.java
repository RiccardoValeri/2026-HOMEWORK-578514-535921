package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoAiuto implements Comando {
    private final static String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
    private final static String NOME = "aiuto";

    @Override
    public void esegui(Partita partita) {
        IO io = partita.getIo();
        for(int i=0; i< elencoComandi.length; i++) 
            io.mostraMessaggio(elencoComandi[i] + " ");
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