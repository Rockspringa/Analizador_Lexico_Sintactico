package edu.CodePad.view.listeners.keys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

public class NumberCounter implements KeyListener {

    private JTextPane numbers;

    public NumberCounter(JTextPane numbers) {
        this.numbers = numbers;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() instanceof JTextPane) {
            JTextPane contPane = (JTextPane) e.getSource();
            String content = contPane.getText().replace("\r", "");

            StringBuilder sb = new StringBuilder();
            int lines = 1;
            int offset = content.length();

            try {
                while (offset > 0) {
                    offset = Utilities.getRowStart(contPane, offset) - 1;
                    sb.append(lines++ + "\n");
                }
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }

            numbers.setText(sb.toString());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
