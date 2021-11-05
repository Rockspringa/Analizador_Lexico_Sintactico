package edu.CodePad.model.sintactico.analisis.arbol;

import edu.CodePad.model.contracts.Node;
import edu.CodePad.model.lexico.parts.wrappers.Token;
import edu.CodePad.model.sintactico.excepciones.AlreadySetted;

public class NodoHoja implements Node {

    private Token token;
    
    public NodoHoja(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return this.token;
    }

    public void set(Token token) throws AlreadySetted {
        if (this.token == null)
            this.token = token;
        else
            throw new AlreadySetted();
    }

}
