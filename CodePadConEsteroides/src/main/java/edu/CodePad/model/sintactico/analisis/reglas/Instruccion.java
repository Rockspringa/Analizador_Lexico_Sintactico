package edu.CodePad.model.sintactico.analisis.reglas;

import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.sintactico.analisis.arbol.Nodo;
import edu.CodePad.model.sintactico.excepciones.VariableNoInicializada;

public abstract class Instruccion extends NoTerminal {

    protected Object bag;

    public Instruccion(Sintagma[][] sintagmas) {
        super(sintagmas);
    }

    public abstract void doAction(Nodo nodo) throws VariableNoInicializada;
    
}
