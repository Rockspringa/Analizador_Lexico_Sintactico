package edu.CodePad.controllers.analisis.concurrent;

import edu.CodePad.model.contracts.Analizer;
import edu.CodePad.model.contracts.AnalyzeException;

public class AnalizerThread extends Thread {

    private Analizer analizador;
    private boolean errores;

    public AnalizerThread(Analizer analizer, String name) {
        super(name);
        this.analizador = analizer;
    }

    @Override
    public void run() {
        try {
            analizador.analyze();
        } catch (AnalyzeException e) {
            errores = true;
            analizador.detectErrors();
        }
    }

    public boolean getsErrores() {
        return errores;
    }
    
}
