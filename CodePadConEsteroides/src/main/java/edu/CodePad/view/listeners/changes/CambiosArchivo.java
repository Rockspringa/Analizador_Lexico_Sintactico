package edu.CodePad.view.listeners.changes;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.CodePad.model.contracts.ExternManager;

public class CambiosArchivo implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        ExternManager.touched = true;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        ExternManager.touched = true;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        ExternManager.touched = true;
    }
    
}
