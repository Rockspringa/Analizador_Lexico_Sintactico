package edu.CodePad.model.sintactico.excepciones;

import edu.CodePad.model.contracts.AnalyzeException;
import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class InvalidTokenException extends AnalyzeException {

    public InvalidTokenException(Token tokenInvalido) {
        super(tokenInvalido);
    }

    @Override
    public String getMessage() {
        Type tipo = token.getTipo();
        int col = token.getCoordenas().getCol();
        int row = token.getCoordenas().getRow();
        return "El token de tipo " + tipo + " es invalido en la columna " + col + ", fila " + row + ".";
    }
}
