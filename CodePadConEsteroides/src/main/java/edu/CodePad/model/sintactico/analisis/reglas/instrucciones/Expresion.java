package edu.CodePad.model.sintactico.analisis.reglas.instrucciones;

import edu.CodePad.model.contracts.Node;
import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.salida.compiles.Variables;
import edu.CodePad.model.sintactico.analisis.arbol.Nodo;
import edu.CodePad.model.sintactico.analisis.arbol.NodoHoja;
import edu.CodePad.model.sintactico.analisis.reglas.Instruccion;
import edu.CodePad.model.sintactico.excepciones.VariableNoInicializada;

public class Expresion extends Instruccion {

    public Expresion(Sintagma[][] sintagmas) {
        super(sintagmas);
    }

    protected int getBag() {
        return (Integer) this.bag;
    }

    protected static int getValorNodoVMD(Nodo nodo) throws VariableNoInicializada {
        Expresion X = (Expresion) ((Nodo) nodo.getHijos()[1]).getSintagma();
        X.doAction((Nodo) nodo.getHijos()[1]);
        return (Integer) X.bag;
    }

    @Override
    public void doAction(Nodo nodo) throws VariableNoInicializada {
        Node[] hijos = nodo.getHijos();
        Nodo V = (Nodo) hijos[0];
        Nodo M = (Nodo) hijos[1];
        Nodo D = (Nodo) hijos[2];

        int valor = -1;

        if (V.getHijos()[0] instanceof Nodo) {
            NodoHoja nodoValor = (NodoHoja) ((Nodo) V.getHijos()[0]).getHijos()[0];
            if (nodoValor.getToken().getTipo() == Type.ID)
                try {
                    valor = new Variables().getValor(nodoValor.getToken().getLexema());
                } catch (NullPointerException e) {
                    throw new VariableNoInicializada(nodoValor.getToken().getLexema());
                }
            else
                valor = Integer.parseInt(nodoValor.getToken().getLexema());
        } else {
            valor = getValorNodoVMD(V);
        }

        if (M.getHijos().length == 2) {
            valor *= getValorNodoVMD(M);
        }

        if (D.getHijos().length == 2) {
            valor += getValorNodoVMD(D);
        }

        this.bag = valor;
    }
    
}
