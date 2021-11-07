package edu.CodePad.view.listeners.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import edu.CodePad.controllers.files.ReaderManager;
import edu.CodePad.model.contracts.ExternManager;

public class CargarArchivo implements ActionListener {

    private final JFrame window;
    private final JTextPane textPane;

    public CargarArchivo(JFrame window, JTextPane textPane) {
        this.window = window;
        this.textPane = textPane;
    }

    private void abrir() {
        try {
            ReaderManager reader = new ReaderManager(window, textPane);
            window.setTitle("CodePad - " + reader.getTitle());
            
        } catch (IOException | NullPointerException ex) {
            JOptionPane.showMessageDialog(window, "Hubo un error al abrir o leer el archivo.",
                    "Error al cargar archivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ExternManager.touched) {
            int opt = JOptionPane.showConfirmDialog(window,
                    "Los cambios actuales no han sido guardados." + "¿Desea Guardar su progreso?",
                    "Cambios sin Guardar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opt == 0) {
                new GuardarArchivo(window, textPane).actionPerformed(e);
            }
            if (opt < 2) {
                abrir();
                ExternManager.touched = false;
            }
        } else
            abrir();
    }

}
