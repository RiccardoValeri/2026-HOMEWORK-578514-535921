package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {
    @Override
    public void mostraMessaggio(String messaggio) {
        System.out.println(messaggio); // [cite: 116]
    }

    @Override
    public String leggiRiga() {
        Scanner scannerDiLinee = new Scanner(System.in); // [cite: 116]
        return scannerDiLinee.nextLine();
    }
}