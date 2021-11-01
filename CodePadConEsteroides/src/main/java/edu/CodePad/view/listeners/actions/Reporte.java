package edu.CodePad.view.listeners.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.CodePad.view.Report;
import edu.CodePad.view.listeners.focus.Analizar;

public class Reporte implements ActionListener {

    private Analizar analisis;
    private Report report;

    public Reporte(Analizar analisis) {
        this.analisis = analisis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (report == null)
            report = new Report();

        boolean setted = false;
        while (analisis.getLexicoData() == null) {
            if (analisis.getLexicoData() != null) {
                report.setContent(analisis.getLexicoData());
                setted = true;
            }
        }
        
        if (!setted)
            report.setContent(analisis.getLexicoData());

        report.setVisible(true);
    }

    public void disposeReport() {
        if (this.report != null)
            this.report.dispose();
    }

}