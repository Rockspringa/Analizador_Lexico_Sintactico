package edu.CodePad.view.listeners.changes;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.CodePad.view.listeners.actions.Reporte;

public class AutoClose implements DocumentListener {

    private Reporte event;

    public AutoClose(Reporte event) {
        this.event = event;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        event.disposeReport();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        event.disposeReport();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        event.disposeReport();
    }
    
}
