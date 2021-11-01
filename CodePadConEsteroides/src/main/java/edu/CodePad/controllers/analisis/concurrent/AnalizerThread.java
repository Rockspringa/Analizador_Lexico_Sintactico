package edu.CodePad.controllers.analisis.concurrent;

import edu.CodePad.model.contracts.Analizer;
import edu.CodePad.model.contracts.AnalyzeException;

public class AnalizerThread extends Thread {

    private Analizer analizador;

    public AnalizerThread(Analizer analizer, String name) {
        super(name);
        this.analizador = analizer;
    }

    @Override
    public void run() {
        try {
            analizador.analyze();
        } catch (AnalyzeException e) {
            e.printStackTrace();
        }
    }
    
}
