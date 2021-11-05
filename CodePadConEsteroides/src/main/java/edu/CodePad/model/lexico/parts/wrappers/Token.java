package edu.CodePad.model.lexico.parts.wrappers;

import java.util.Iterator;

import edu.CodePad.model.iterator.ObjectIterator;
import edu.CodePad.model.lexico.analisis.Type;

public class Token implements Iterable<Object> {

    private final Type tipo;
    private final String lexema;
    private final Coordenada coor;

    public Token(Type tipo, String lexema, Coordenada coor) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.coor = coor;
    }

    public Type getTipo() {
        return this.tipo;
    }

    public String getLexema() {
        return this.lexema;
    }

    public Coordenada getCoordenas() {
        return this.coor;
    }

    @Override
    public String toString() {
        return this.lexema;
    }

    @Override
    public Iterator<Object> iterator() {
        return new ObjectIterator(this);
    }

}
