package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {
	
	private Scanner scannerDiLinee;
	
	public IOConsole(Scanner scanner) {
		this.scannerDiLinee = scanner;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	@Override
	public String leggiRiga() {
		return scannerDiLinee.nextLine();
	}
}