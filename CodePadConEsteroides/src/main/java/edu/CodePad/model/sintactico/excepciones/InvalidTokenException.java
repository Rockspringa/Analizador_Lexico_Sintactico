package edu.CodePad.model.sintactico.excepciones;

import edu.CodePad.model.contracts.AnalyzeException;
import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.lexico.parts.wrappers.Token;
import edu.CodePad.model.sintactico.analisis.reglas.Terminal;

public class InvalidTokenException extends AnalyzeException {

    private Terminal[] terminales;

    public InvalidTokenException(Token tokenInvalido, Terminal... terminales) {
        super(tokenInvalido);
        this.terminales = terminales;
    }

    @Override
    public String getMessage() {
        Type tipo = token.getTipo();
        int col = token.getCoordenas().getCol();
        int row = token.getCoordenas().getRow();

        String msgTer = "";
        if (terminales != null && terminales.length > 0) {
            msgTer = " Se esperaba";
            for (Terminal terminal : terminales)
                msgTer += " '" + terminal.toString() + "'";
        }
        return "El token de tipo " + tipo + " es invalido en la columna " + col + ", fila " + row + "." + msgTer;
    }
}
