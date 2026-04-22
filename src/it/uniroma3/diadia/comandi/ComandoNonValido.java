package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoNonValido implements Comando {
    private final static String NOME = "non valido";

    @Override
    public void esegui(Partita partita) {
        IO io = partita.getIo();
        io.mostraMessaggio("Comando sconosciuto.");
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