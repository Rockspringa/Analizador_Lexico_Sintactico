package edu.CodePad.view.listeners.changes;

import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.CodePad.controllers.analisis.Analizador;
import edu.CodePad.model.viewSupps.TablaData;

public class Analizar implements DocumentListener {

    private Analizador analizador;
    private String previouContent;
    private JTextPane contPane;
    private JTextPane logPane;

    public Analizar(JTextPane contPane, JTextPane logPane) {
        this.contPane = contPane;
        this.logPane = logPane;
    }

    public TablaData getLexicoData() {
        if (this.analizador != null && !this.analizador.isAnalizandoLexico())
            return this.analizador.getLexicoData();
        else
            return null;
    }

    private void analizar() {
        if (!contPane.getText().equals(previouContent) && isForAnalisis()) {
            previouContent = contPane.getText();
            this.analizador = new Analizador(logPane, previouContent);
            this.analizador.start();
        }
    }

    private boolean isForAnalisis() {
        boolean out = this.analizador == null || !this.analizador.isAlive();
        out = out && Analizador.isFinalizado() == null;
        out = out || Analizador.isFinalizado() == true;
        return out;
    }

    public boolean isExportable() {
        try {
            return !this.analizador.getsErrores() && Analizador.isFinalizado();
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        analizar();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        analizar();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        analizar();
    }

}
