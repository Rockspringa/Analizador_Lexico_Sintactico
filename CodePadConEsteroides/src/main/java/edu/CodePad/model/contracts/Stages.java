package edu.CodePad.model.contracts;

import edu.CodePad.model.salida.exceptions.NotAllowedForStage;

public interface Stages {
    
    void commit() throws NotAllowedForStage;

    void rollback() throws NotAllowedForStage;

}
