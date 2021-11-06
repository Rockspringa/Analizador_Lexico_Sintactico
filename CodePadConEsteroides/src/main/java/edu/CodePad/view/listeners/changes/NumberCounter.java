package edu.CodePad.view.listeners.changes;

import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.CodePad.view.Principal;

public class NumberCounter implements DocumentListener {

    private JFrame win;

    public NumberCounter(JFrame win) {
        this.win = win;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateNumbers();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateNumbers();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateNumbers();
    }

    private void updateNumbers() {
        ((Principal) win).updateNumbers();
    }

}
