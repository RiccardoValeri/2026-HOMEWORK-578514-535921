package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public abstract class AbstractComando implements Comando {
	private String parametro;
	private IO io;

	@Override
	public abstract void esegui(Partita partita);

	// IMPLEMENTA QUESTO: restituisce il nome basandosi sulla classe
	@Override
	public String getNome() {
		String nomeClasse = this.getClass().getSimpleName(); // es: "ComandoVai"
		if (nomeClasse.startsWith("Comando")) {
			return nomeClasse.substring(7).toLowerCase(); // restituisce "vai"
		}
		return nomeClasse.toLowerCase();
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}

	public void setIo(IO io) {
		this.io = io;
	}

	public IO getIo() {
		return this.io;
	}
}