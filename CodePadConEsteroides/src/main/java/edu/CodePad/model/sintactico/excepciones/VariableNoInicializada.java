package edu.CodePad.model.sintactico.excepciones;

import edu.CodePad.model.contracts.AnalyzeException;
import edu.CodePad.model.lexico.parts.wrappers.ErrorToken;

public class VariableNoInicializada extends AnalyzeException {

    private String var;
    
    public VariableNoInicializada(String var) {
        super((ErrorToken) null);
        this.var = var;
    }

    @Override
    public String getMessage() {
        return "La variable '" + var + "' no se ha inicializado.";
    }

}
