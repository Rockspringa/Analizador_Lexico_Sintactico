package edu.CodePad.model.iterator;

import java.util.Iterator;

import edu.CodePad.model.lexico.parts.wrappers.ErrorToken;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class ObjectIterator implements Iterator<Object> {

    private int position = 0;
    private Object obj;

    public ObjectIterator(Object obj) {
        this.obj = obj;
    }

    @Override
    public boolean hasNext() {
        if (this.position < 3)
            return true;
        else
            return false;
    }

    @Override
    public Object next() {
        Object dato = null;

        if (this.obj instanceof Token) {
            Token token = (Token) this.obj;
            dato = switch (position) {
                case 0 -> token.getTipo();
                case 1 -> token.getLexema();
                case 2 -> token.getCoordenas();
                default -> new IndexOutOfBoundsException();
            };

            position++;
        }

        if (this.obj instanceof ErrorToken) {
            ErrorToken token = (ErrorToken) this.obj;
            dato = switch (position) {
                case 0 -> token.getCausa();
                case 1 -> token.getLexema();
                case 2 -> token.getCoordenas();
                default -> new IndexOutOfBoundsException();
            };

            position++;
        }

        return dato;
    }
    
}
