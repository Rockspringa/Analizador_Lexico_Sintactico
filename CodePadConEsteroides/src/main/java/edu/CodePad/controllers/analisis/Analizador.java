package edu.CodePad.controllers.analisis;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.CodePad.controllers.analisis.concurrent.AnalizerThread;
import edu.CodePad.model.contracts.Analizer;
import edu.CodePad.model.lexico.parts.wrappers.Token;
import edu.CodePad.model.viewSupps.TablaData;

public class Analizador extends Thread {

    private Analizer lexico;
    private Thread hiloLexico;
    private Queue<Token> tablaSimbolos;

    public Analizador(String content) {
        this.tablaSimbolos = new ConcurrentLinkedQueue<>();
        this.lexico = new AnalizadorLexico(content, this.tablaSimbolos);
    }

    private void analisisLexico() {
        try {
            hiloLexico = new AnalizerThread(lexico, "Analizador lexico");
            hiloLexico.start();
        } catch (IllegalThreadStateException e) {
            e.printStackTrace();
        }
    }

    private void analisisSintactico() {/*
        while (hiloLexico.isAlive() || !tablaSimbolos.isEmpty()) {
            Token token = tablaSimbolos.poll();
            if (token != null)
                System.out.print(token);
        }*/
    }

    public boolean isAnalizandoLexico() {
        return hiloLexico != null && hiloLexico.isAlive();
    }

    public TablaData getLexicoData() {
        return new TablaData((AnalizadorLexico) this.lexico);
    }

    @Override
    public void run() {
        analisisLexico();
        analisisSintactico();
    }
    
}
