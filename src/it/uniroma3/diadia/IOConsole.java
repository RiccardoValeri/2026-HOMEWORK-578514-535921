package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole {
    // 1. Creiamo lo scanner come variabile di istanza
    private Scanner scannerDiLinee;

    public IOConsole() {
        // 2. Lo inizializziamo una sola volta nel costruttore
        this.scannerDiLinee = new Scanner(System.in);
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public String leggiRiga() {
        // 3. Usiamo lo scanner già esistente senza chiuderlo
        return this.scannerDiLinee.nextLine();
    }
}