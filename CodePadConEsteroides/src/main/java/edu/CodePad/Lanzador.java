package edu.CodePad;

import javax.swing.SwingUtilities;

import edu.CodePad.view.Principal;

public class Lanzador {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Principal();
            }
        });
    }
}
