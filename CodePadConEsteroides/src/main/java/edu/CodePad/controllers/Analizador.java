package edu.CodePad.controllers;

import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

import edu.CodePad.controllers.concurrent.AnalizerThread;
import edu.CodePad.model.contracts.Analizer;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class Analizador extends Thread {

    private Analizer lexico;
    private Queue<Token> tablaSimbolos;

    public Analizador(String content) {
        this.lexico = new AnalizadorLexico(content);
        this.tablaSimbolos = new SynchronousQueue<>();
    }

    private void analisisLexico() {
        try {
            new AnalizerThread(lexico, "Analizador lexico").start();
        } catch (IllegalThreadStateException e) {
            e.printStackTrace();
        }
    }

    private void analisisSintactico() {

    }

    @Override
    public void run() {
        analisisLexico();
        analisisSintactico();
    }
    
}
