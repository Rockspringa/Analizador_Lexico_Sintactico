package edu.CodePad.controllers.files;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import edu.CodePad.model.contracts.ExternManager;
import edu.CodePad.model.salida.files.Reader;

public class ReaderManager extends ExternManager {

    public ReaderManager(JFrame parent, JTextPane textPane) throws IOException {
        Reader reader = new Reader();
        actualFile = reader.read(parent);
        textPane.setText(reader.getContentFile(actualFile));
    }

    public String getTitle() {
        if (actualFile != null)
            return actualFile.getName();
        return null;
    }
    
}
