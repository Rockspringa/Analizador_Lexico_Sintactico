package edu.CodePad.model.salida;

import edu.CodePad.model.contracts.Stages;
import edu.CodePad.model.salida.exceptions.NotAllowedForStage;

public class Documento implements Stages {
    
    private static StringBuilder docBuilder;
    private static StringBuilder docUncommited = new StringBuilder();

    private boolean forCommit = false;

    public Documento(String line) {
        docUncommited.append(line + '\n');
    }

    public Documento() {
        this.forCommit = true;
    }

    private boolean isForTakeAndCommit() {
        return this.forCommit;
    }

    public String getDocument() throws NotAllowedForStage {
        if (! isForTakeAndCommit())
            throw new NotAllowedForStage();
        
        return docBuilder.toString();
    }

    @Override
    public void commit() throws NotAllowedForStage {
        if (! isForTakeAndCommit())
            throw new NotAllowedForStage();
            
        docBuilder = docUncommited;
        docUncommited = new StringBuilder();
    }

    @Override
    public void rollback() throws NotAllowedForStage {
        if (! isForTakeAndCommit())
            throw new NotAllowedForStage();
            
        docBuilder = new StringBuilder();
        docUncommited = new StringBuilder();
    }

}
