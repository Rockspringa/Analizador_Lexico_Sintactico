package edu.CodePad.model.lexico.parts.wrappers;

import java.util.Iterator;

import edu.CodePad.model.iterator.ObjectIterator;

public class ErrorToken implements Iterable<Object> {
    
    private final String causa;
    private String lexema;
    private Coordenada coor;

    public ErrorToken(String causa) {
        this.causa = causa;
    }

    public String getCausa() {
        return this.causa;
    }

    public String getLexema() {
        return this.lexema;
    }

    public Coordenada getCoordenas() {
        return this.coor;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setCoordenadas(Coordenada coor) {
        this.coor = coor;
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
