package edu.CodePad.model.contracts;

import edu.CodePad.model.lexico.parts.wrappers.ErrorToken;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public abstract class AnalyzeException extends Exception {
    
    protected final ErrorToken tokenInvalido;
    protected final Token token;

    public AnalyzeException(ErrorToken tokenInvalido) {
        this.tokenInvalido = tokenInvalido;
        this.token = null;
    }

    public AnalyzeException(Token token) {
        this.tokenInvalido = null;
        this.token = token;
    }

    public ErrorToken getTokenInvalido() {
        return this.tokenInvalido;
    }

}
