package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurazione {

    private static final String NOME_FILE = "diadia.properties";
    private static int cfu;
    private static int pesoMax;

    static {
        Properties prop = new Properties();
        try (InputStream input = Configurazione.class.getClassLoader().getResourceAsStream(NOME_FILE)) {
            if (input != null) {
                prop.load(input);
                cfu = Integer.parseInt(prop.getProperty("cfu_iniziali", "20"));
                pesoMax = Integer.parseInt(prop.getProperty("peso_max_borsa", "10"));
            } else {
                cfu = 20;
                pesoMax = 10;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static int getCfuIniziali() {
        return cfu;
    }

    public static int getPesoMaxBorsa() {
        return pesoMax;
    }
}