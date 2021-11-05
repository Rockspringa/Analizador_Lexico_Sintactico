package edu.CodePad.model.sintactico.analisis.reglas.instrucciones;

import edu.CodePad.model.contracts.Node;
import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.salida.Variables;
import edu.CodePad.model.sintactico.analisis.arbol.Nodo;
import edu.CodePad.model.sintactico.analisis.arbol.NodoHoja;
import edu.CodePad.model.sintactico.analisis.reglas.Instruccion;

public class Repetir extends Instruccion {

    public Repetir(Sintagma[][] sintagmas) {
        super(sintagmas);
    }

    private void callEscrituraAnidada(Nodo G) {
        Node[] hijos = G.getHijos();
        if (hijos.length == 2) {
            Nodo E = (Nodo) hijos[0];
            ((Escritura) E.getSintagma()).doAction(E);

            callEscrituraAnidada((Nodo) hijos[1]);
        }
    }

    @Override
    public void doAction(Nodo nodo) {
        Node[] hijos = nodo.getHijos();
        NodoHoja nodoValor = (NodoHoja) ((Nodo) hijos[1]).getHijos()[0];
        int valor = -1;

        if (nodoValor.getToken().getTipo() == Type.ID)
            valor = new Variables().getValor(nodoValor.getToken().getLexema());
        else
            valor = Integer.parseInt(nodoValor.getToken().getLexema());

        for (int i = 0; i < valor; i++) {
            callEscrituraAnidada((Nodo) hijos[3]);
        }
    }
    
}
