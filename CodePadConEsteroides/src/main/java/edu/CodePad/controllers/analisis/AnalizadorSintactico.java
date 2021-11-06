package edu.CodePad.controllers.analisis;

import java.util.Queue;

import edu.CodePad.model.contracts.Analizer;
import edu.CodePad.model.contracts.AnalyzeException;
import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.lexico.parts.wrappers.Token;
import edu.CodePad.model.sintactico.automatas.AutomataPila;
import edu.CodePad.model.sintactico.excepciones.AnalisisNoTerminadoException;
import edu.CodePad.model.sintactico.excepciones.InvalidTokenException;

public class AnalizadorSintactico implements Analizer {

    private Queue<Token> tablaSimbolos;
    private StringBuilder log = new StringBuilder();
    private Token token = null;
    private boolean continuar = true;

    public AnalizadorSintactico(Queue<Token> tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }

    @Override
    public void analyze() throws AnalyzeException {
        AutomataPila autPila = new AutomataPila();

        try {
            while (true) {
                token = this.tablaSimbolos.poll();
                if (token == null)
                    if (continuar)
                        continue;
                    else
                        throw new AnalisisNoTerminadoException();
                else if (token.getTipo() == Type.INICIO)
                    break;

                autPila.getNextState(token);
                if (token.getTipo() == Type.FINAL)
                    if (!autPila.isFinished())
                        this.tablaSimbolos.add(token);
                    else
                        break;
            }
        } catch (AnalyzeException | IndexOutOfBoundsException e) {
            log.append(e.getMessage() + "\n");
            throw new InvalidTokenException(token);
        }
    }

    @Override
    public void detectErrors() {
        AutomataPila autPila = new AutomataPila();

        while (true) {
            try {
                if (token != null && token.getTipo() == Type.FINAL)
                    break;
                
                token = this.tablaSimbolos.poll();
                if (token == null)
                    if (continuar)
                        continue;
                    else
                        break;
                else if (token.getTipo() == Type.INICIO)
                    break;

                autPila.getNextState(token);
            } catch (AnalyzeException | IndexOutOfBoundsException e) {
                log.append(e.getMessage() + "\n");
            }
        }
    }

    public String getLogErrores() {
        return log.toString();
    }

    public void noContinuar() {
        this.continuar = false;
    }

}
