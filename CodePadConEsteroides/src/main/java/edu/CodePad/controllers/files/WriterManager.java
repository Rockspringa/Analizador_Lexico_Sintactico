package edu.CodePad.controllers.files;

import java.io.IOException;

import javax.swing.JFrame;

import edu.CodePad.controllers.analisis.Analizador;
import edu.CodePad.model.contracts.ExternManager;
import edu.CodePad.model.salida.compiles.Documento;
import edu.CodePad.model.salida.exceptions.NotAllowedForStage;
import edu.CodePad.model.salida.files.Writer;

public class WriterManager extends ExternManager {

    private static final Documento DOCUMENTO = new Documento();
    public static final int GUARDAR_COMO = 1;
    public static final int GUARDAR = 0;

    private String content;
    private JFrame parent;

    public WriterManager(JFrame parent, String content, int modoGuardar) throws IOException {
        this.content = content;
        this.parent = parent;

        if (modoGuardar == GUARDAR_COMO)
            exportContent();
        else
            save();
    }

    public WriterManager(JFrame parent) throws IOException {
        try {
            while (Analizador.isFinalizado() == null || Analizador.isFinalizado() == false);
            
            DOCUMENTO.commit();
            this.content = DOCUMENTO.getDocument();
            this.parent = parent;

            exportContent();
        } catch (NotAllowedForStage e) {
        }
    }

    private void exportContent() throws IOException {
        Writer writer = new Writer();
        writer.export(parent, actualFile, content);
    }

    private void save() throws IOException {
        Writer writer = new Writer();
        writer.save(parent, actualFile, content);
    }

    public String getTitle() {
        if (actualFile != null)
            return actualFile.getName();
        return null;
    }
    
}
