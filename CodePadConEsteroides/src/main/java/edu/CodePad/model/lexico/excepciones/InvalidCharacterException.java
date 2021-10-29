package edu.CodePad.model.lexico.excepciones;

import edu.CodePad.model.wrappers.Token;

public class InvalidCharacterException extends RuntimeException {

    private final Token tokenInvalido;

    public InvalidCharacterException(Token tokenInvalido) {
        this.tokenInvalido = tokenInvalido;
    }

    public Token getTokenInvalido() {
        return this.tokenInvalido;
    }
}
