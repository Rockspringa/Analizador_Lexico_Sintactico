package edu.CodePad.view.listeners.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class CargarArchivo implements ActionListener {

    private JFrame window;

    public CargarArchivo(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {/*
        try {
            textoPrincipal.setText(manager.updateTextArea());
            textoContador.setText(manager.getLineNums());
        } catch (IOException | NullPointerException ex) {
            JOptionPane.showMessageDialog(window,
                    "Hubo un error al abrir o leer el archivo. Msg: " + ex.getMessage(), "Error al cargar archivo",
                    JOptionPane.ERROR_MESSAGE);
        }*/
    }

}
