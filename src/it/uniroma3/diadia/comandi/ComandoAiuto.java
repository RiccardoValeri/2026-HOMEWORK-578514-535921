package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

// 1. Cambia implements con extends
public class ComandoAiuto extends AbstractComando {

	private static final String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};

	@Override
	public void esegui(Partita partita) {
		// 2. Usa this.getIo() che viene ereditato da AbstractComando
		for (int i=0; i < elencoComandi.length; i++) {
			this.getIo().mostraMessaggio(elencoComandi[i] + " ");
		}
	}
    
}