package edu.CodePad.model.sintactico.excepciones;

import edu.CodePad.model.contracts.AnalyzeException;
import edu.CodePad.model.lexico.parts.wrappers.ErrorToken;

public class AnalisisNoTerminadoException extends AnalyzeException {
    
    public AnalisisNoTerminadoException() {
        super((ErrorToken) null);
    }

    @Override
    public String getMessage() {
        return "El analisis sintactico no ha termino de analizar, se esperaban más tokens.";
    }

}
