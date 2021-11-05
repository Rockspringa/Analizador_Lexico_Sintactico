package edu.CodePad.model.sintactico.excepciones;

public class AlreadySetted extends Exception {
    
    @Override
    public String getMessage() {
        return "El valor de este nodo ya se establecio con anterioridad.";
    }
    
}
