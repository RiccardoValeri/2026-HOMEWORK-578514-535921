package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class DiaDia {
    private Partita partita;
    private IO io; // Riferimento tipato IO 

    public DiaDia(IO io) {
        this.io = io;
        this.partita = new Partita(io); // Passiamo IO anche alla partita se serve ai comandi
    }

    public void gioca() {
        String istruzione;
        io.mostraMessaggio("Benvenuto!");
        do {
            istruzione = io.leggiRiga();
        } while (!processaIstruzione(istruzione));
    }

    private boolean processaIstruzione(String istruzione) {
        Comando comandoDaEseguire;
        FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
        comandoDaEseguire = factory.costruisciComando(istruzione);
        comandoDaEseguire.esegui(this.partita);
        
        if (this.partita.vinta()) {
            io.mostraMessaggio("Hai vinto!");
            return true;
        } else if (this.partita.getGiocatore().getCfu() == 0) {
            io.mostraMessaggio("Hai esaurito i CFU!");
            return true;
        }
        return this.partita.isFinita();
    }

    public static void main(String[] argc) {
        IO io = new IOConsole(); // L'unica istanza creata qui [cite: 44, 47]
        DiaDia gioco = new DiaDia(io); // Iniettata nel costruttore [cite: 48, 119]
        gioco.gioca();
    }
}