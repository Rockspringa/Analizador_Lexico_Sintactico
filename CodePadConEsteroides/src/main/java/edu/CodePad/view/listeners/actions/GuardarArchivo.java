package edu.CodePad.view.listeners.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GuardarArchivo implements ActionListener {

    private JFrame window;

    public GuardarArchivo(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {/*
        try {
            if (buscarBtn.isSelected())
                buscarBtn.doClick();

            if (manager.saveChanges()) {
                JOptionPane.showMessageDialog(window, "El archivo se ha modificado con exito.", "Archivo guardado",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(window,
                        "Ocurrio un error al momento de intentar guardar el archivo, vuelva a intentarlo.",
                        "Error al guardar", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NullPointerException | HeadlessException | IOException e1) {
            JOptionPane.showMessageDialog(window,
                    "Ocurrio un error al momento de intentar guardar el archivo, vuelva a intentarlo.",
                    "Error al guardar", JOptionPane.ERROR_MESSAGE);
        }*/
    }

}
