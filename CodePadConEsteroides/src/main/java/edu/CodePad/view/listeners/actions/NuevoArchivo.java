package edu.CodePad.view.listeners.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import edu.CodePad.model.contracts.ExternManager;

public class NuevoArchivo implements ActionListener {

    private final JFrame window;
    private final JTextPane textPane;

    public NuevoArchivo(JFrame window, JTextPane textPane) {
        this.window = window;
        this.textPane = textPane;
    }

    private void nuevo() {
        this.textPane.setText("");
        ExternManager.actualFile = null;
        ExternManager.touched = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ExternManager.touched) {
            int opt = JOptionPane.showConfirmDialog(window,
                    "Los cambios actuales no han sido guardados." + "¿Desea Guardar su progreso?",
                    "Cambios sin Guardar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opt == 0) {
                new GuardarArchivo(window, textPane).actionPerformed(e);
                if (ExternManager.touched == false)
                    nuevo();
            } else if (opt == 1) {
                nuevo();
            }
        } else
            nuevo();
    }

}
