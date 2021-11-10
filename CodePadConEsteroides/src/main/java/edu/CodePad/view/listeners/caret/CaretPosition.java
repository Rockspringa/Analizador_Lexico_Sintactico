package edu.CodePad.view.listeners.caret;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

public class CaretPosition implements CaretListener {

    private JLabel caretLabel;

    public CaretPosition(JLabel caretLabel) {
        this.caretLabel = caretLabel;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        JTextPane textPane = (JTextPane) e.getSource();

        int line = 1;
        int col = 0;

        int caret = textPane.getCaretPosition();
        try {
            char[] chars = textPane.getDocument().getText(0, caret).toCharArray();
            for (int i = 0; i < caret; i++) {
                if (chars[i] == '\n') {
                    line++;
                    col = 0;
                } else if (chars[i] != '\f' && chars[i] != '\r') {
                    col++;
                }
            }
        } catch (BadLocationException e1) {
        }

        int diff = textPane.getSelectionEnd() - textPane.getSelectionStart();
        if (diff == 0)
            caretLabel.setText("Linea: " + line + ", Columna: " + col);
        else
            caretLabel.setText("Linea: " + line + ", Columna: " + col + " (" + diff + " seleccionadas)");
    }

}
