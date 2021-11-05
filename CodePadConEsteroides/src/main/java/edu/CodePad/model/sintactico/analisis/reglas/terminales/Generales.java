package edu.CodePad.model.sintactico.analisis.reglas.terminales;

import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.lexico.parts.wrappers.Token;
import edu.CodePad.model.sintactico.analisis.reglas.Terminal;

public interface Generales {

    Terminal ID = new Terminal(Type.ID);
    Terminal NUM = new Terminal(Type.NUMBER);
    Terminal IGUAL = new Terminal(Type.IGUAL);
    Terminal FINAL = new Terminal(Type.FINAL);
    Terminal EPSILON = new Terminal("", null);
    Terminal LITERAL = new Terminal(Type.LITERAL);

    Terminal NUM_POS = new Terminal(Type.NUMBER) {

        @Override
        public boolean isCorrectToken(Token token) {
            if (token != null) {
                char inicial = token.getLexema().toCharArray()[0];

                if (inicial != '-' && inicial != '0')
                    return super.isCorrectToken(token);
            }
            return false;
        }

    };

}
