package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/** 
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole console) {
		this.io = console;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		do {
			istruzione = this.io.leggiRiga();
		} while (!processaIstruzione(istruzione)); 
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	/**
	 * Processa una istruzione impartita dall'utente
	 *
	 * @param istruzione riga di comando inserita dall'utente
	 * @return true se l'istruzione e' eseguita e il gioco deve terminare, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
	    Comando comandoDaEseguire = new Comando(istruzione);
	    String nomeComando = comandoDaEseguire.getNome();
	    String parametro = comandoDaEseguire.getParametro();

	    if (nomeComando == null) {
	        this.io.mostraMessaggio("Inserisci un comando!");
	        return false;
	    }

	    if (nomeComando.equals("fine")) {
	        this.fine(); 
	        return true;
	    } else if (nomeComando.equals("vai")) {
	        this.vai(parametro);
	    } else if (nomeComando.equals("aiuto")) {
	        this.aiuto();
	    } else if (nomeComando.equals("prendi")) {
	        this.prendi(parametro);
	    } else if (nomeComando.equals("posa")) {
	        this.posa(parametro);
	    } else {
	        this.io.mostraMessaggio("Comando sconosciuto");
	    }

	    // Controlliamo se la partita è finita dopo l'ultimo comando
	    if (this.partita.vinta()) {
	        this.io.mostraMessaggio("Hai vinto!");
	        return true;
	    } 
	    
	    if (this.partita.isFinita()) {
	        this.io.mostraMessaggio("Hai esaurito i CFU! Game Over.");
	        return true;
	    }

	    return false;
	}
	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		StringBuilder s = new StringBuilder();
		s.append("Comandi disponibili: ");
		for(int i=0; i< elencoComandi.length; i++) {
			s.append(elencoComandi[i] + " ");
		}
		this.io.mostraMessaggio(s.toString());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	private void prendi(String nomeAttrezzo) {
	    Stanza stanzaCorrente = this.partita.getStanzaCorrente();
	    Attrezzo a = stanzaCorrente.getAttrezzo(nomeAttrezzo); // Prende il riferimento
	    
	    if (a != null) {
	        // Tenta di aggiungere l'attrezzo nella borsa del giocatore
	        if (this.partita.getGiocatore().getBorsa().addAttrezzo(a)) {
	            stanzaCorrente.removeAttrezzo(nomeAttrezzo); // Rimuove dalla stanza solo se entra in borsa
	            this.io.mostraMessaggio("Hai preso: " + nomeAttrezzo);
	        } else {
	            this.io.mostraMessaggio("Borsa troppo piena!");
	        }
	    } else {
	        this.io.mostraMessaggio("Attrezzo non presente in questa stanza.");
	    }
	}

	private void posa(String nomeAttrezzo) {
	    Borsa borsa = this.partita.getGiocatore().getBorsa();
	    Attrezzo a = borsa.getAttrezzo(nomeAttrezzo); // Cerca l'attrezzo in borsa
	    
	    if (a != null) {
	        // Lo mette nella stanza corrente
	        if (this.partita.getStanzaCorrente().addAttrezzo(a)) {
	            borsa.removeAttrezzo(nomeAttrezzo); // Lo toglie dalla borsa
	            this.io.mostraMessaggio("Hai posato: " + nomeAttrezzo);
	        } else {
	            this.io.mostraMessaggio("Non c'è spazio nella stanza per posare l'attrezzo!");
	        }
	    } else {
	        this.io.mostraMessaggio("Non hai questo attrezzo in borsa.");
	    }
	}
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.partita.setFinita();
		this.io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}