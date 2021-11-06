package edu.CodePad.controllers.analisis;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JTextPane;

import edu.CodePad.controllers.analisis.concurrent.AnalizerThread;
import edu.CodePad.model.contracts.Analizer;
import edu.CodePad.model.lexico.parts.wrappers.Token;
import edu.CodePad.model.salida.compiles.Documento;
import edu.CodePad.model.salida.compiles.Variables;
import edu.CodePad.model.salida.exceptions.NotAllowedForStage;
import edu.CodePad.model.viewSupps.TablaData;

public class Analizador extends Thread {

    private static Boolean finalizado;
    private JTextPane logErrores;
    private Analizer lexico;
    private Analizer sintaxis;
    private Thread hiloLexico;
    private Thread hiloSintactico;
    private Queue<Token> tablaSimbolos;

    public Analizador(JTextPane log, String content) {
        this.tablaSimbolos = new ConcurrentLinkedQueue<>();
        this.lexico = new AnalizadorLexico(content, this.tablaSimbolos);
        this.sintaxis = new AnalizadorSintactico(this.tablaSimbolos);
        this.logErrores = log;
        this.logErrores.setText("Analizando...");
    }

    private void analisisLexico() {
        try {
            hiloLexico = new AnalizerThread(lexico, "Analizador lexico");
            hiloLexico.start();
        } catch (IllegalThreadStateException e) {
            e.printStackTrace();
        }
    }

    private void analisisSintactico() {
        try {
            hiloSintactico = new AnalizerThread(sintaxis, "Analizador sintactico");
            hiloSintactico.start();
        } catch (IllegalThreadStateException e) {
            e.printStackTrace();
        }
    }

    public boolean isAnalizandoLexico() {
        return hiloLexico != null && hiloLexico.isAlive();
    }

    public TablaData getLexicoData() {
        return new TablaData((AnalizadorLexico) this.lexico);
    }

    public boolean getsErrores() {
        return ((AnalizerThread) this.hiloLexico).getsErrores() || ((AnalizerThread) this.hiloSintactico).getsErrores();
    }

    public String getLogErrores() {
        String log = "";
        try {
            log += this.lexico.getLogErrores();
        } catch (NullPointerException e) {
        }
        try {
            log += this.sintaxis.getLogErrores();
        } catch (NullPointerException e) {
        }
        return log;
    }

    @Override
    public void run() {
        if (finalizado == null || finalizado == true) {
            finalizado = false;
            try {
                new Variables().rollback();
                new Documento().rollback();
            } catch (NotAllowedForStage e) {
            }

            analisisLexico();
            analisisSintactico();

            while (hiloLexico.isAlive()) {
            }
            ((AnalizadorSintactico) sintaxis).noContinuar();
            while (hiloSintactico.isAlive()) {
            }

            if (getsErrores())
                this.logErrores.setText(getLogErrores());
            else
                this.logErrores.setText("No se encontraron errores.");

            finalizado = true;
        }
    }

    public static Boolean isFinalizado() {
        return finalizado;
    }

}
