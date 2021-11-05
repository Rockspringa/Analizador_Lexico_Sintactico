package edu.CodePad.model.sintactico.analisis.reglas.terminales;

import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.sintactico.analisis.reglas.Terminal;

public interface Agrupadores {

    Terminal PARENTESIS_IZQ = new Terminal("(", Type.AGRUPACION);
    Terminal PARENTESIS_DER = new Terminal(")", Type.AGRUPACION);

}
