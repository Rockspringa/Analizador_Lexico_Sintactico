package edu.CodePad.model.contracts;

import edu.CodePad.model.lexico.parts.wrappers.Token;
import edu.CodePad.model.sintactico.excepciones.AlreadySetted;

public interface Node {
    
    void set(Token token) throws AlreadySetted;
}
