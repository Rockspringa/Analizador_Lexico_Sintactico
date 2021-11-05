package edu.CodePad.model.sintactico.excepciones;

import edu.CodePad.model.contracts.AnalyzeException;
import edu.CodePad.model.lexico.parts.wrappers.ErrorToken;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class AnalisisTerminadoException extends AnalyzeException {
    
    public AnalisisTerminadoException() {
        super((ErrorToken) null);
    }

    public AnalisisTerminadoException(Token token) {
        super(token);
    }

    @Override
    public String getMessage() {
        return "El analisis sintactico ya termino de analizar, ya no se esperaba mas texto.";
    }

}
