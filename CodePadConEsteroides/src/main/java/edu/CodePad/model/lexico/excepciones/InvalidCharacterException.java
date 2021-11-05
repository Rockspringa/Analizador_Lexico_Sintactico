package edu.CodePad.model.lexico.excepciones;

import edu.CodePad.model.contracts.AnalyzeException;
import edu.CodePad.model.lexico.parts.wrappers.ErrorToken;

public class InvalidCharacterException extends AnalyzeException {

    public InvalidCharacterException(ErrorToken tokenInvalido) {
        super(tokenInvalido);
    }

    @Override
    public String getMessage() {
        String causa = this.tokenInvalido.getCausa();
        int col = this.tokenInvalido.getCoordenas().getCol();
        int row = this.tokenInvalido.getCoordenas().getRow();
        return causa + " en la columna " + col + ", fila " + row + ".";
    }
}
