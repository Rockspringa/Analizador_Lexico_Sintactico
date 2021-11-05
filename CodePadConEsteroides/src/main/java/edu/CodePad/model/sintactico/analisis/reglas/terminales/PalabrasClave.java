package edu.CodePad.model.sintactico.analisis.reglas.terminales;

import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.sintactico.analisis.reglas.Terminal;

public interface PalabrasClave {

    Terminal SI = new Terminal("SI", Type.ID);
    Terminal FIN = new Terminal("FIN", Type.ID);
    Terminal FALSO = new Terminal("FALSO", Type.ID);
    Terminal INICIAR = new Terminal("INICIAR", Type.ID);
    Terminal REPETIR = new Terminal("REPETIR", Type.ID);
    Terminal ENTONCES = new Terminal("ENTONCES", Type.ID);
    Terminal ESCRIBIR = new Terminal("ESCRIBIR", Type.ID);
    Terminal VERDADERO = new Terminal("VERDADERO", Type.ID);

}
