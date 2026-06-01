package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

    @Override
    public void esegui(Partita partita) {
        AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
        if (personaggio == null) {
            this.getIo().mostraMessaggio("Non c'è nessuno a cui fare un regalo.");
            return;
        }
        
        Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
        if (attrezzo == null) {
            this.getIo().mostraMessaggio("Non hai questo attrezzo nella borsa.");
            return;
        }
        
        // Il personaggio riceve il regalo e restituisce un messaggio
        String risposta = personaggio.riceviRegalo(attrezzo, partita);
        this.getIo().mostraMessaggio(risposta);
        
        // Rimuoviamo l'attrezzo dalla borsa dopo il regalo
        partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
    }
}