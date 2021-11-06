package edu.CodePad.model.contracts;

import edu.CodePad.model.lexico.parts.wrappers.Token;

public interface Node {
    
    void set(Token token) throws AnalyzeException;
}
