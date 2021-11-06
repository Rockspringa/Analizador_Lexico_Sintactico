package edu.CodePad.view.listeners.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.CodePad.controllers.files.WriterManager;
import edu.CodePad.view.listeners.changes.Analizar;

public class ExportResult implements ActionListener {

    private JFrame window;
    private Analizar analisis;

    public ExportResult(JFrame window, Analizar analisis) {
        this.window = window;
        this.analisis = analisis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.analisis.isExportable()) {
            try {
                new WriterManager(window);
                JOptionPane.showMessageDialog(window, "El archivo se ha exportado con exito.", "Archivo guardado",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (NullPointerException | IOException e1) {
                JOptionPane.showMessageDialog(window,
                        "Ocurrio un error al momento de intentar guardar el archivo, vuelva a intentarlo.",
                        "Error al guardar", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(window,
                    "El analisis detecto errores en la entrada de texto, resuelvalos para poder exportar.",
                    "Error en el analisis", JOptionPane.ERROR_MESSAGE);
        }
    }

}
