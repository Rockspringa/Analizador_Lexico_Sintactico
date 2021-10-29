package edu.CodePad.model.lexico.excepciones;

import edu.CodePad.model.contracts.AnalyzeException;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class InvalidCharacterException extends AnalyzeException {

    private final Token tokenInvalido;

    public InvalidCharacterException(Token tokenInvalido) {
        this.tokenInvalido = tokenInvalido;
    }

    public Token getTokenInvalido() {
        return this.tokenInvalido;
    }
}
