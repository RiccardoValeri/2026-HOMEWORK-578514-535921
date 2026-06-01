package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

    @Override
    public Comando costruisciComando(String istruzione) {
        Scanner scannerDiParole = new Scanner(istruzione);
        String nomeComando = null;
        String parametro = null;
        Comando comando = null;

        if (scannerDiParole.hasNext())
            nomeComando = scannerDiParole.next(); // es. "vai"
        if (scannerDiParole.hasNext())
            parametro = scannerDiParole.next(); // es. "nord"

        if (nomeComando == null) {
            return new ComandoNonValido();
        }

        try {
            // Componiamo il nome della classe: it.uniroma3.diadia.comandi.Comando + Vai
            String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
            nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
            nomeClasse += nomeComando.substring(1).toLowerCase();
            
            // Carichiamo la classe e creiamo l'oggetto istantaneamente
            comando = (Comando) Class.forName(nomeClasse).newInstance();
            comando.setParametro(parametro);
        } catch (Exception e) {
            // Se la classe non esiste (es. l'utente scrive "palla"), creiamo ComandoNonValido
            comando = new ComandoNonValido();
        }
        
        return comando;
    }
}