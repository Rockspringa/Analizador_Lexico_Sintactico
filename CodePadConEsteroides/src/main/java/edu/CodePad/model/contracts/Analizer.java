package edu.CodePad.model.contracts;

public interface Analizer {
    
    public void analyze() throws AnalyzeException;

    public void detectErrors();

    public String getLogErrores();
    
}
