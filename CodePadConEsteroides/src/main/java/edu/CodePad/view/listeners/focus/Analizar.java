package edu.CodePad.view.listeners.focus;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextPane;

import edu.CodePad.controllers.analisis.Analizador;
import edu.CodePad.model.viewSupps.TablaData;

public class Analizar implements FocusListener {

    private Analizador analizador;
    private String previouContent;

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JTextPane) {
            JTextPane content = (JTextPane) e.getSource();
            if (! content.getText().equals(previouContent)) {
                previouContent = content.getText();
                this.analizador = new Analizador(previouContent);
                this.analizador.start();
            }
        }
    }

    public TablaData getLexicoData() {
        if (this.analizador != null && ! this.analizador.isAnalizandoLexico())
            return this.analizador.getLexicoData();
        else
            return null;
    }

}
