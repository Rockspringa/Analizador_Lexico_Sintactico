package edu.CodePad.model.sintactico.excepciones;

import edu.CodePad.model.contracts.AnalyzeException;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class AlreadySetted extends AnalyzeException {
    
    public AlreadySetted() {
        super((Token) null);
    }

    @Override
    public String getMessage() {
        return "El valor de este nodo ya se establecio con anterioridad.";
    }
    
}
