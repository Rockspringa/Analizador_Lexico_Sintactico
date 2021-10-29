package edu.CodePad.model.wrappers;

import edu.CodePad.model.analisis.Type;

public class Token {

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

    public Coordenada getCoordanas() {
        return this.coor;
    }

    @Override
    public String toString() {
        return this.lexema;
    }

}
