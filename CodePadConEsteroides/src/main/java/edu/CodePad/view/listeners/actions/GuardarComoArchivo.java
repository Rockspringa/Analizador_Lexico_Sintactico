package edu.CodePad.view.listeners.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import edu.CodePad.controllers.files.WriterManager;
import edu.CodePad.model.contracts.ExternManager;

public class GuardarComoArchivo implements ActionListener {

    private JFrame window;
    private JTextPane textPane;

    public GuardarComoArchivo(JFrame window, JTextPane textPane) {
        this.window = window;
        this.textPane = textPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            WriterManager writer = new WriterManager(window, textPane.getText(), WriterManager.GUARDAR_COMO);
            window.setTitle("CodePad - " + writer.getTitle());
            JOptionPane.showMessageDialog(window, "El archivo se ha guardado con exito.", "Archivo guardado",
                    JOptionPane.INFORMATION_MESSAGE);
            ExternManager.touched = false;
            window.setTitle("CodePad - " + ExternManager.actualFile.getName());
        } catch (NullPointerException | IOException e1) {
            JOptionPane.showMessageDialog(window,
                    "Ocurrio un error al momento de intentar guardar el archivo, vuelva a intentarlo.",
                    "Error al guardar", JOptionPane.ERROR_MESSAGE);
        }
    }

}
