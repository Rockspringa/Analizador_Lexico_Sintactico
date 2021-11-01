package edu.CodePad.model.lexico.parts.wrappers;

import java.util.Iterator;

import edu.CodePad.model.iterator.ObjectIterator;

public class ErrorToken implements Iterable<Object> {
    
    private final String causa;
    private final String lexema;
    private final Coordenada coor;

    public ErrorToken(String causa, String lexema, Coordenada coor) {
        this.causa = causa;
        this.lexema = lexema;
        this.coor = coor;
    }

    public String getCausa() {
        return this.causa;
    }

    public String getLexema() {
        return this.lexema;
    }

    public Coordenada getCoordanas() {
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
