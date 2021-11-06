package edu.CodePad.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class Undo extends AbstractAction {

    private final UndoManager undoManager;

    public Undo(UndoManager undoManager) {
        super("Undo");
        this.undoManager = undoManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        } catch (CannotUndoException ex) {
        }
    }
    
}
