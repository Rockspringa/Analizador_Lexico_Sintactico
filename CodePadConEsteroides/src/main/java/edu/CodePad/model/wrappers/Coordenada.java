package edu.CodePad.model.wrappers;

public class Coordenada {
    
    private final int col;
    private final int row;
    
    public Coordenada(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

}
