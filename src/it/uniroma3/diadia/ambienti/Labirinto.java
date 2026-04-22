package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
    private Stanza stanzaIniziale;
    private Stanza stanzaFinale;
    private Stanza stanzaCorrente;

    public Labirinto() {
        creaLabirinto();
        this.stanzaCorrente = this.stanzaIniziale;
    }

    private void creaLabirinto() {
        /* crea gli attrezzi */
        Attrezzo lanterna = new Attrezzo("lanterna", 3);
        Attrezzo osso = new Attrezzo("osso", 1);
        Attrezzo chiave = new Attrezzo("chiave", 1);

        /* 1. CREA LE STANZE (Sostituendo alcune con quelle speciali) */
        Stanza atrio = new Stanza("Atrio");
        StanzaMagica aulaN11 = new StanzaMagica("Aula N11"); // Stanza Magica
        StanzaBuia aulaN10 = new StanzaBuia("Aula N10", "lanterna"); // Stanza Buia (serve lanterna)
        StanzaBloccata laboratorio = new StanzaBloccata("Laboratorio Campus", "ovest", "chiave"); // Bloccata a ovest
        Stanza biblioteca = new Stanza("Biblioteca");

        /* 2. COLLEGA LE STANZE (Rimane uguale) */
        atrio.impostaStanzaAdiacente("nord", biblioteca);
        atrio.impostaStanzaAdiacente("est", aulaN11);
        atrio.impostaStanzaAdiacente("sud", aulaN10);
        atrio.impostaStanzaAdiacente("ovest", laboratorio);
        
        aulaN11.impostaStanzaAdiacente("ovest", atrio);
        
        aulaN10.impostaStanzaAdiacente("nord", atrio);
        
        laboratorio.impostaStanzaAdiacente("est", atrio);
        // laboratorio ha un'uscita a ovest che abbiamo bloccato nel costruttore
        laboratorio.impostaStanzaAdiacente("ovest", aulaN11); 
        
        biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* 3. PONE GLI ATTREZZI NELLE STANZE */
        atrio.addAttrezzo(osso);
        atrio.addAttrezzo(chiave); // Mettiamo la chiave nell'atrio per sbloccare il laboratorio
        // Nota: non mettiamo la lanterna nell'aulaN10, così sarà buia finché non ce la porti!
        atrio.addAttrezzo(lanterna); 

        this.stanzaIniziale = atrio;
        this.stanzaFinale = biblioteca;
    }

    // ... Getter e Setter rimangono uguali a prima ...
    public Stanza getStanzaCorrente() { return stanzaCorrente; }
    public void setStanzaCorrente(Stanza stanzaCorrente) { this.stanzaCorrente = stanzaCorrente; }
    public Stanza getStanzaVincente() { return stanzaFinale; }
}