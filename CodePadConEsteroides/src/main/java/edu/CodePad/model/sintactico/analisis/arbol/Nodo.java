package edu.CodePad.model.sintactico.analisis.arbol;

import javax.naming.OperationNotSupportedException;

import edu.CodePad.model.contracts.Node;
import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.lexico.parts.wrappers.Token;
import edu.CodePad.model.sintactico.analisis.reglas.Instruccion;
import edu.CodePad.model.sintactico.analisis.reglas.NoTerminal;
import edu.CodePad.model.sintactico.analisis.reglas.Terminal;
import edu.CodePad.model.sintactico.excepciones.AlreadySetted;

public class Nodo implements Node {

    private final NoTerminal sintagma;
    private Node[] hijos;
    private int posSet = 0;

    public Nodo(NoTerminal sintagma, Sintagma[] sintagmas) {
        this.sintagma = sintagma;
        this.setHijos(sintagmas);
    }

    public Nodo(NoTerminal sintagma) {
        this.sintagma = sintagma;
    }

    private void setHijos(Sintagma[] sintagmas) {
        this.hijos = new Node[sintagmas.length];

        for (int i = 0; i < sintagmas.length; i++) {
            if (sintagmas[i] instanceof NoTerminal)
                hijos[i] = new Nodo((NoTerminal) sintagmas[i]);
            else if (sintagmas[i] instanceof Terminal)
                hijos[i] = new NodoHoja(null);
        }
    }

    private void callAction() {
        Nodo nodo = (Nodo) hijos[posSet];
        if (nodo.sintagma instanceof Instruccion) {
            Instruccion ins = (Instruccion) nodo.sintagma;
            if (! (this.sintagma instanceof Instruccion)) {
                ins.doAction(nodo);
            }
        }
    }

    public NoTerminal getSintagma() {
        return this.sintagma;
    }

    public Node[] getHijos() {
        return this.hijos;
    }

    public void set(Token token) throws AlreadySetted {
        if (hijos[posSet] instanceof NodoHoja) {
            NodoHoja hoja = (NodoHoja) hijos[posSet];

            if (hoja.getToken() == null) {
                hoja.set(token);
                posSet++;
            } else
                throw new AlreadySetted();
        } else {
            try {
                Nodo nodo = (Nodo) hijos[posSet];
                nodo.set(token);
            } catch (IndexOutOfBoundsException e) {
                callAction();
                posSet++;
                set(token);
            }
        }
    }

    public void set(Sintagma[] sintagmas) throws AlreadySetted, OperationNotSupportedException {
        if (hijos == null) {
            this.setHijos(sintagmas);
        } else if (hijos[posSet] instanceof Nodo) {
            try {
                Nodo nodo = (Nodo) hijos[posSet];
                nodo.set(sintagmas);
            } catch (IndexOutOfBoundsException e) {
                callAction();
                posSet++;
                set(sintagmas);
            }
        } else {
            posSet++;
            throw new OperationNotSupportedException("Los nodos hojas no contienen sintagmas.");
        }
    }

}
