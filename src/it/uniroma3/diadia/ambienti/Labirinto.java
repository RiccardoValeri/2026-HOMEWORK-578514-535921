package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Orco;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;
	private Stanza stanzaCorrente;

	private Labirinto() {
	}

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	public Stanza getStanzaCorrente() { 
		return stanzaCorrente; 
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) { 
		this.stanzaCorrente = stanzaCorrente; 
	}
	
	public Stanza getStanzaVincente() { 
		return stanzaFinale; 
	}

	public void setStanzaVincente(Stanza vincente) {
		this.stanzaFinale = vincente;
	}

	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> nome2stanza;

		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.nome2stanza = new HashMap<>();
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			Stanza iniziale = new Stanza(nomeStanza);
			this.labirinto.setStanzaCorrente(iniziale);
			this.completamentoAggiuntaStanza(iniziale);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			Stanza vincente = new Stanza(nomeStanza);
			this.labirinto.setStanzaVincente(vincente);
			this.completamentoAggiuntaStanza(vincente);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaPartenza, String stanzaAdiacente, String direzione) {
			Stanza partenza = this.nome2stanza.get(stanzaPartenza);
			Stanza arrivo = this.nome2stanza.get(stanzaAdiacente);
			partenza.impostaStanzaAdiacente(Direzione.valueOf(direzione), arrivo);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			Attrezzo a = new Attrezzo(nomeAttrezzo, peso);
			this.ultimaStanzaAggiunta.addAttrezzo(a);
			return this;
		}

		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza stanza = new Stanza(nomeStanza);
			this.completamentoAggiuntaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nomeStanza, int soglia) {
			Stanza stanza = new StanzaMagica(nomeStanza, soglia);
			this.completamentoAggiuntaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoLucente) {
			Stanza stanza = new StanzaBuia(nomeStanza, attrezzoLucente);
			this.completamentoAggiuntaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzione, String attrezzoSbloccante) {
			Stanza stanza = new StanzaBloccata(nomeStanza, Direzione.valueOf(direzione), attrezzoSbloccante);
			this.completamentoAggiuntaStanza(stanza);
			return this;
		}

		private void completamentoAggiuntaStanza(Stanza stanza) {
			this.ultimaStanzaAggiunta = stanza;
			this.nome2stanza.put(stanza.getNome(), stanza);
		}
		
		public Map<String, Stanza> getListaStanze() {
			return this.nome2stanza;
		}
		
		public LabirintoBuilder addMago(String nome, String presentazione, String nomeAttrezzo, int pesoAttrezzo) {
			Mago m = new Mago(nome, presentazione, new Attrezzo(nomeAttrezzo, pesoAttrezzo));
			this.ultimaStanzaAggiunta.setPersonaggio(m);
			return this;
		}
		
		public LabirintoBuilder addOrco(String nome, String presentazione) {
			Orco o = new Orco(nome, presentazione);
			this.ultimaStanzaAggiunta.setPersonaggio(o);
			return this;
		}

		public LabirintoBuilder addStrega(String nome, String presentazione) {
			Strega s = new Strega(nome, presentazione);
			this.ultimaStanzaAggiunta.setPersonaggio(s);
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeStanza, String nomeAttrezzo, int peso) {
			Stanza stanza = this.nome2stanza.get(nomeStanza); 
			if (stanza != null) {
				stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			}
			return this;
		}
		
		public LabirintoBuilder addMago(String nomeStanza, String nome, String presentazione, String nomeAttrezzo, int pesoAttrezzo) {
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			if (stanza != null) {
				stanza.setPersonaggio(new Mago(nome, presentazione, new Attrezzo(nomeAttrezzo, pesoAttrezzo)));
			}
			return this;
		}

		public LabirintoBuilder addOrco(String nomeStanza, String nome, String presentazione) {
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			if (stanza != null) {
				stanza.setPersonaggio(new Orco(nome, presentazione));
			}
			return this;
		}

		public LabirintoBuilder addStrega(String nomeStanza, String nome, String presentazione) {
			Stanza stanza = this.nome2stanza.get(nomeStanza);
			if (stanza != null) {
				stanza.setPersonaggio(new Strega(nome, presentazione));
			}
			return this;
		}
	}
}