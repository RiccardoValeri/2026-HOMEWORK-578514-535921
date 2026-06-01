package it.uniroma3.diadia;

import java.io.*;
import java.util.*;
import it.uniroma3.diadia.ambienti.Labirinto;

public class CaricatoreLabirinto {

    private static final String STANZE_MARKER = "Stanze:";
    private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche:";
    private static final String STANZE_BUIE_MARKER = "StanzeBuie:";
    private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate:";
    private static final String STANZA_INIZIALE_MARKER = "Inizio:";
    private static final String STANZA_VINCENTE_MARKER = "Vincente:";
    private static final String ATTREZZI_MARKER = "Attrezzi:";
    private static final String USCITE_MARKER = "Uscite:";
    private static final String MAGHI_MARKER = "Maghi:";
    private static final String ORCHI_MARKER = "Orchi:";
    private static final String STREGHE_MARKER = "Streghe:";

    private LineNumberReader reader;
    private Labirinto.LabirintoBuilder builder;

    public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
        this.reader = new LineNumberReader(new FileReader(nomeFile));
        this.builder = Labirinto.newBuilder();
    }

    public CaricatoreLabirinto(Reader reader) {
        this.reader = new LineNumberReader(reader);
        this.builder = Labirinto.newBuilder();
    }

    public void carica() throws FormatoFileNonValidoException {
        try {
            String riga;
            while ((riga = this.reader.readLine()) != null) {
                if (riga.trim().isEmpty()) continue;
                
                if (riga.startsWith(STANZE_MARKER)) leggiECreaStanze(riga.substring(STANZE_MARKER.length()));
                else if (riga.startsWith(STANZE_MAGICHE_MARKER)) leggiECreaStanzeMagiche(riga.substring(STANZE_MAGICHE_MARKER.length()));
                else if (riga.startsWith(STANZE_BUIE_MARKER)) leggiECreaStanzeBuie(riga.substring(STANZE_BUIE_MARKER.length()));
                else if (riga.startsWith(STANZE_BLOCCATE_MARKER)) leggiECreaStanzeBloccate(riga.substring(STANZE_BLOCCATE_MARKER.length()));
                else if (riga.startsWith(STANZA_INIZIALE_MARKER)) this.builder.addStanzaIniziale(riga.substring(STANZA_INIZIALE_MARKER.length()).trim());
                else if (riga.startsWith(STANZA_VINCENTE_MARKER)) this.builder.addStanzaVincente(riga.substring(STANZA_VINCENTE_MARKER.length()).trim());
                else if (riga.startsWith(ATTREZZI_MARKER)) leggiECollocaAttrezzi(riga.substring(ATTREZZI_MARKER.length()));
                else if (riga.startsWith(USCITE_MARKER)) leggiEImpostaUscite(riga.substring(USCITE_MARKER.length()));
                else if (riga.startsWith(MAGHI_MARKER)) leggiECollocaMaghi(riga.substring(MAGHI_MARKER.length()));
                else if (riga.startsWith(STREGHE_MARKER)) leggiECollocaStreghe(riga.substring(STREGHE_MARKER.length()));
                else if (riga.startsWith(ORCHI_MARKER)) leggiECollocaOrchi(riga.substring(ORCHI_MARKER.length()));
            }
        } catch (IOException e) {
            throw new FormatoFileNonValidoException(e.getMessage());
        } finally {
            try {
                this.reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<String> separaStringheAlleVirgole(String string) {
        List<String> result = new LinkedList<>();
        Scanner scanner = new Scanner(string);
        scanner.useDelimiter(",");
        try (Scanner scannerDiParole = scanner) {
            while (scannerDiParole.hasNext()) {
                result.add(scannerDiParole.next().trim());
            }
        }
        return result;
    }

    private void leggiECreaStanze(String stringa) {
        for (String nomeStanza : separaStringheAlleVirgole(stringa)) {
            this.builder.addStanza(nomeStanza);
        }
    }

    private void leggiECreaStanzeMagiche(String stringa) throws FormatoFileNonValidoException {
        for (String spec : separaStringheAlleVirgole(stringa)) {
            try (Scanner scanner = new Scanner(spec)) {
                check(scanner.hasNext(), "nome stanza magica mancante");
                String nome = scanner.next();
                check(scanner.hasNext(), "soglia stanza magica mancante");
                try {
                    int soglia = Integer.parseInt(scanner.next());
                    this.builder.addStanzaMagica(nome, soglia);
                } catch (NumberFormatException e) {
                    check(false, "soglia non valida per " + nome);
                }
            }
        }
    }

    private void leggiECreaStanzeBuie(String stringa) throws FormatoFileNonValidoException {
        for (String spec : separaStringheAlleVirgole(stringa)) {
            try (Scanner scanner = new Scanner(spec)) {
                check(scanner.hasNext(), "nome stanza buia mancante");
                String nome = scanner.next();
                check(scanner.hasNext(), "attrezzo per stanza buia mancante");
                String attrezzo = scanner.next();
                this.builder.addStanzaBuia(nome, attrezzo);
            }
        }
    }

    private void leggiECreaStanzeBloccate(String stringa) throws FormatoFileNonValidoException {
        for (String spec : separaStringheAlleVirgole(stringa)) {
            try (Scanner scanner = new Scanner(spec)) {
                check(scanner.hasNext(), "nome stanza bloccata mancante");
                String nome = scanner.next();
                check(scanner.hasNext(), "direzione bloccata mancante");
                String dir = scanner.next();
                check(scanner.hasNext(), "attrezzo sbloccante mancante");
                String attrezzo = scanner.next();
                this.builder.addStanzaBloccata(nome, dir, attrezzo);
            }
        }
    }

    private void leggiECollocaAttrezzi(String stringa) throws FormatoFileNonValidoException {
        for (String spec : separaStringheAlleVirgole(stringa)) {
            try (Scanner scanner = new Scanner(spec)) {
                check(scanner.hasNext(), "nome attrezzo mancante");
                String nome = scanner.next();
                check(scanner.hasNext(), "peso attrezzo mancante");
                String pesoStr = scanner.next();
                check(scanner.hasNext(), "nome stanza per attrezzo mancante");
                String stanza = scanner.next();
                try {
                    int peso = Integer.parseInt(pesoStr);
                    this.builder.addAttrezzo(stanza, nome, peso);
                } catch (NumberFormatException e) {
                    check(false, "peso non valido per " + nome);
                }
            }
        }
    }

    private void leggiEImpostaUscite(String stringa) throws FormatoFileNonValidoException {
        for (String spec : separaStringheAlleVirgole(stringa)) {
            try (Scanner scanner = new Scanner(spec)) {
                check(scanner.hasNext(), "stanza partenza mancante");
                String partenza = scanner.next();
                check(scanner.hasNext(), "direzione uscita mancante");
                String dir = scanner.next();
                check(scanner.hasNext(), "stanza destinazione mancante");
                String destinazione = scanner.next();
                this.builder.addAdiacenza(partenza, destinazione, dir);
            }
        }
    }

    private void leggiECollocaMaghi(String stringa) throws FormatoFileNonValidoException {
        for (String spec : separaStringheAlleVirgole(stringa)) {
            try (Scanner scanner = new Scanner(spec)) {
                check(scanner.hasNext(), "stanza mago mancante");
                String stanza = scanner.next();
                check(scanner.hasNext(), "nome mago mancante");
                String nome = scanner.next();
                check(scanner.hasNext(), "presentazione mago mancante");
                String pres = scanner.next();
                check(scanner.hasNext(), "attrezzo mago mancante");
                String attr = scanner.next();
                check(scanner.hasNext(), "peso attrezzo mago mancante");
                try {
                    int peso = Integer.parseInt(scanner.next());
                    this.builder.addMago(stanza, nome, pres, attr, peso);
                } catch (NumberFormatException e) {
                    check(false, "peso attrezzo non valido per il mago " + nome);
                }
            }
        }
    }

    private void leggiECollocaStreghe(String stringa) throws FormatoFileNonValidoException {
        for (String spec : separaStringheAlleVirgole(stringa)) {
            try (Scanner scanner = new Scanner(spec)) {
                check(scanner.hasNext(), "stanza strega mancante");
                String stanza = scanner.next();
                check(scanner.hasNext(), "nome strega mancante");
                String nome = scanner.next();
                check(scanner.hasNext(), "presentazione strega mancante");
                String pres = scanner.next();
                this.builder.addStrega(stanza, nome, pres);
            }
        }
    }

    private void leggiECollocaOrchi(String stringa) throws FormatoFileNonValidoException {
        for (String spec : separaStringheAlleVirgole(stringa)) {
            try (Scanner scanner = new Scanner(spec)) {
                check(scanner.hasNext(), "stanza orco mancante");
                String stanza = scanner.next();
                check(scanner.hasNext(), "nome orco mancante");
                String nome = scanner.next();
                check(scanner.hasNext(), "presentazione orco mancante");
                String pres = scanner.next();
                this.builder.addOrco(stanza, nome, pres);
            }
        }
    }

    private void check(boolean condizione, String messaggioErrore) throws FormatoFileNonValidoException {
        if (!condizione) {
            throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
        }
    }

    public Labirinto getLabirinto() {
        return this.builder.getLabirinto();
    }
}