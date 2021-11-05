package edu.CodePad.model.sintactico.analisis.reglas;

import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class Terminal implements Sintagma {

    private final String keyWord;
    private final Type tipo;

    public Terminal(String keyWord, Type tipo) {
        this.keyWord = keyWord;
        this.tipo = tipo;
    }

    public Terminal(Type tipo) {
        this.keyWord = null;
        this.tipo = tipo;
    }

    @Override
    public boolean isCorrectToken(Token token) {
        if (tipo == null)
            return true;
        return tipo.equals(token.getTipo()) && (keyWord == null || keyWord.equals(token.getLexema()));
    }

}
