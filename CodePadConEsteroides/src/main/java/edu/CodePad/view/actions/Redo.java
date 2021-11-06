package edu.CodePad.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class Redo extends AbstractAction {

    private final UndoManager undoManager;

    public Redo(UndoManager undoManager) {
        super("Redo");
        this.undoManager = undoManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        } catch (CannotUndoException ex) {
        }
    }

}
