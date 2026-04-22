package it.uniroma3.diadia.comandi;

public interface FabbricaDiComandi {
    /**
     * Costruisce il comando a partire da una stringa.
     */
    public Comando costruisciComando(String istruzione);
}