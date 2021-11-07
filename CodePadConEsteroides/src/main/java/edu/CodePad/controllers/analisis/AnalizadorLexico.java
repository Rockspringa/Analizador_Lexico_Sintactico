package edu.CodePad.controllers.analisis;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import edu.CodePad.model.contracts.Analizer;
import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.lexico.automatas.AFDToken;
import edu.CodePad.model.lexico.excepciones.InvalidCharacterException;
import edu.CodePad.model.lexico.parts.wrappers.Coordenada;
import edu.CodePad.model.lexico.parts.wrappers.ErrorToken;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class AnalizadorLexico implements Analizer {

    private StringBuilder logErrores;
    private StringBuilder lexema;
    private List<Token> tablaSimbolos;
    private List<ErrorToken> errores;
    private Queue<Token> queueSimbolos;
    private String contenido;
    private String log;

    AnalizadorLexico(String contenido, Queue<Token> queue) {
        this.queueSimbolos = queue;
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
                    queueSimbolos.add(token);
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
            } else if (ch != '\r' && ch != '\f') {
                col++;
            }
        }

        this.log = afd.getLog();
        Token dollar = new Token(Type.FINAL, "", coorActual);
        queueSimbolos.add(dollar);
    }

    @Override
    public void detectErrors() {
        this.logErrores = new StringBuilder();
        this.queueSimbolos.add(new Token(Type.INICIO, "", null));
        
        Coordenada coorActual = new Coordenada(0, 1);
        AFDToken afd = new AFDToken();
        Type typeOld = Type.INICIO;
        int col = 0;
        int row = 1;

        this.errores = new ArrayList<>();

        for (char ch : contenido.toCharArray()) {
            Type typeNew = null;
            try {
                typeNew = afd.getNextState(ch);
                if (isGuardable(typeOld, typeNew)) {
                    lexema = new StringBuilder();
                    coorActual = new Coordenada(col, row);
                    typeOld = typeNew;
                }
                lexema.append(ch);
            } catch (InvalidCharacterException e) {
                lexema.append(ch);

                ErrorToken error = e.getTokenInvalido();
                error.setLexema(lexema.toString());
                error.setCoordenadas(coorActual);

                this.errores.add(error);

                lexema = new StringBuilder();
                coorActual = new Coordenada(col, row);
                typeOld = typeNew;

                this.logErrores.append(e.getMessage() + "\n");
            }

            if (ch == '\n') {
                row++;
                col = 0;
            } else if (ch != '\r' && ch != '\f') {
                col++;
            }
        }

        this.log = afd.getLog();
    }

    public Object[] getTablaSimbolos() {
        if (this.errores == null)
            return this.tablaSimbolos.toArray();
        return this.errores.toArray();
    }

    public String getLog() {
        return this.log;
    }

    public String getLogErrores() {
        return logErrores.toString();
    }

}
