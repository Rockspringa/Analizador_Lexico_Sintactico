package edu.CodePad.view.listeners.keys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.CodePad.view.listeners.actions.Reporte;

public class AutoClose implements KeyListener {

    private Reporte event;

    public AutoClose(Reporte event) {
        this.event = event;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        event.disposeReport();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
