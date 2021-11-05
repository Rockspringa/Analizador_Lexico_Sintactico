package edu.CodePad.model.sintactico.analisis.reglas.instrucciones;

import edu.CodePad.model.contracts.Node;
import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.sintactico.analisis.arbol.Nodo;
import edu.CodePad.model.sintactico.analisis.arbol.NodoHoja;
import edu.CodePad.model.sintactico.analisis.reglas.Instruccion;

public class Condicional extends Instruccion {

    public Condicional(Sintagma[][] sintagmas) {
        super(sintagmas);
    }

    @Override
    public void doAction(Nodo nodo) {
        Node[] hijos = nodo.getHijos();
        Node[] hijosDeC = ((Nodo) hijos[1]).getHijos();
        String booleano = ((NodoHoja) hijosDeC[0]).getToken().getLexema();

        if (booleano.equals("VERDADERO")) {
            Nodo E = (Nodo) ((Nodo) hijos[3]).getHijos()[0];
            ((Escritura) E.getSintagma()).doAction(E);
        }
    }
    
}
