package edu.CodePad.model.sintactico.analisis.reglas;

import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class NoTerminal implements Sintagma {

    private Sintagma[][] sintagmas;

    public NoTerminal(Sintagma[][] sintagmas) {
        this.sintagmas = sintagmas;
    }

    public Sintagma[][] getSintagmas() {
        return this.sintagmas;
    }

    @Override
    public boolean isCorrectToken(Token token) {
        for (Sintagma[] sins : sintagmas)
            if (sins[0].isCorrectToken(token))
                return sintagmas[0][0].isCorrectToken(token);
        return false;
    }

}
