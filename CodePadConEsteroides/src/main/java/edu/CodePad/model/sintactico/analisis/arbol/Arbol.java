package edu.CodePad.model.sintactico.analisis.arbol;

import javax.naming.OperationNotSupportedException;

import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.lexico.parts.wrappers.Token;
import edu.CodePad.model.sintactico.analisis.reglas.NoTerminal;
import edu.CodePad.model.sintactico.excepciones.AlreadySetted;
import edu.CodePad.model.sintactico.excepciones.AnalisisTerminadoException;

public class Arbol {

    private final Nodo raiz;

    public Arbol(NoTerminal sintagma) {
        this.raiz = new Nodo(sintagma);
    }

    public void set(Token token) throws AnalisisTerminadoException {
        try {
            this.raiz.set(token);
        } catch (AlreadySetted | NullPointerException | IndexOutOfBoundsException e) {
            throw new AnalisisTerminadoException(token);
        }
    }

    public void set(Sintagma[] sintagmas) throws AnalisisTerminadoException {
        try {
            this.raiz.set(sintagmas);
        } catch (OperationNotSupportedException | AlreadySetted | NullPointerException | IndexOutOfBoundsException e) {
            throw new AnalisisTerminadoException();
        }
    }

}
