package edu.CodePad.controllers;

import java.util.ArrayList;
import java.util.List;

import edu.CodePad.model.contracts.Analizer;
import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.lexico.automatas.AFDToken;
import edu.CodePad.model.lexico.excepciones.InvalidCharacterException;
import edu.CodePad.model.lexico.parts.wrappers.Coordenada;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class AnalizadorLexico implements Analizer {

    private StringBuilder lexema;
    private List<Token> tablaSimbolos;
    private String contenido;
    private Token actualToken;

    AnalizadorLexico(String contenido) {
        this.tablaSimbolos = new ArrayList<>();
        this.contenido = contenido + '\n';
        this.lexema = new StringBuilder();
    }

    private boolean isGuardable(Type type) {
        return switch (type) {
        case INICIO -> false;
        case COMENTARIO -> false;
        case LITERAL_INCOMPLETO -> false;
        case COMENTARIO_INCOMPLETO -> false;
        default -> true;
        };
    }

    private boolean isGuardable(Type typeOld, Type typeNew) {
        if (typeOld != typeNew)
            return true;

        return switch (typeOld) {
        case AGRUPACION -> true;
        default -> false;
        };
    }

    public Token getToken() throws InterruptedException {
        while (actualToken == null)
            Thread.sleep(200);

        Token token = actualToken;
        actualToken = null;

        return token;
    }

    @Override
    public void analyze() throws InvalidCharacterException {
        Coordenada coorActual = new Coordenada(0, 1);
        AFDToken afd = new AFDToken();
        Type typeOld = Type.INICIO;
        int col = 0;
        int row = 1;

        for (char ch : contenido.toCharArray()) {
            Type typeNew = afd.getNextState(ch);

            if (isGuardable(typeOld, typeNew)) {
                if (isGuardable(typeOld)) {
                    Token token = new Token(typeOld, lexema.toString(), coorActual);
                    tablaSimbolos.add(token);
                    actualToken = token;
                }
                if (typeOld != Type.LITERAL_INCOMPLETO)
                    lexema = new StringBuilder();

                coorActual = new Coordenada(col, row);
                typeOld = typeNew;
            }

            lexema.append(ch);
            if (ch == '\n') {
                row++;
                col = 0;
            } else {
                col++;
            }
        }
    }

    @Override
    public void detectErrors() {
        // TODO Auto-generated method stub

    }

}
