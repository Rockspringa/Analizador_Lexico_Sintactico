package edu.CodePad.model.sintactico.analisis.reglas.terminales;

import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.sintactico.analisis.reglas.Terminal;

public interface Operadores {
    
    Terminal SUMA = new Terminal("+", Type.OPERADOR);
    Terminal MULTIPLICAR = new Terminal("*", Type.OPERADOR);

}
