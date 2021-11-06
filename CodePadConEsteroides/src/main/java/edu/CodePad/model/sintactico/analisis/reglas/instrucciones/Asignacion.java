package edu.CodePad.model.sintactico.analisis.reglas.instrucciones;

import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.salida.compiles.Variables;
import edu.CodePad.model.sintactico.analisis.arbol.Nodo;
import edu.CodePad.model.sintactico.analisis.arbol.NodoHoja;
import edu.CodePad.model.sintactico.analisis.reglas.Instruccion;
import edu.CodePad.model.sintactico.excepciones.VariableNoInicializada;

public class Asignacion extends Instruccion {

    public Asignacion(Sintagma[][] sintagmas) {
        super(sintagmas);
    }

    @Override
    public void doAction(Nodo nodo) throws VariableNoInicializada {
        Nodo X = (Nodo) nodo.getHijos()[2];
        Expresion expr = (Expresion) X.getSintagma();

        String id = ((NodoHoja) nodo.getHijos()[0]).getToken().getLexema();

        try {
            expr.getBag();
        } catch (NullPointerException e) {
            expr.doAction(X);
        }

        int valor = expr.getBag();

        new Variables(id, valor);
    }
    
}
