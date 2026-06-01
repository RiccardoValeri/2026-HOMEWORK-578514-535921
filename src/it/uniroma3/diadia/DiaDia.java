package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import java.util.Scanner;

public class DiaDia {
    private Partita partita;
    private IO io;

    public DiaDia(IO io, Labirinto labirinto) {
        this.io = io;
        this.partita = new Partita(labirinto); 
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
        FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
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
        try (Scanner scanner = new Scanner(System.in)) {
            IO io = new IOConsole(scanner);
            
            Labirinto labirinto = Labirinto.newBuilder()
                    .addStanzaIniziale("Atrio")
                    .addAttrezzo("Atrio", "osso", 1)
                    .addStanzaVincente("Biblioteca")
                    .addAdiacenza("Atrio", "Biblioteca", Direzione.nord.name())
                    .getLabirinto();
            
            DiaDia gioco = new DiaDia(io, labirinto);
            gioco.gioca();
        }
    }
}