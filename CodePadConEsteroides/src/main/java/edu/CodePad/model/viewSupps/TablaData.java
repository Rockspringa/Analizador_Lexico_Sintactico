package edu.CodePad.model.viewSupps;

import edu.CodePad.controllers.analisis.AnalizadorLexico;
import edu.CodePad.model.lexico.parts.wrappers.ErrorToken;
import edu.CodePad.model.lexico.parts.wrappers.Token;

public class TablaData {
    
    private final Object[][] data;
    private final String[] headers;
    private final String title;
    private final String log;

    public TablaData(AnalizadorLexico lexico) {
        Object[] iterables = lexico.getTablaSimbolos();
        this.data = new Object[iterables.length][3];

        for (int i = 0; i < iterables.length; i++) {
            int j = 0;

            if (iterables[i] instanceof Token)
                for (Object obj : (Token) iterables[i])
                    this.data[i][j++] = obj;

            else if (iterables[i] instanceof ErrorToken)
                for (Object obj : (ErrorToken) iterables[i])
                    this.data[i][j++] = obj;
        }

        if (iterables.length > 0) {
            if (iterables[0] instanceof Token) {
                this.title = "Reporte de Tokens";
                this.headers = new String[] { "Tipo de Token", "Lexema", "Coordenadas" };
            }
            
            else if (iterables[0] instanceof ErrorToken) {
                this.title = "Reporte de Errores";
                this.headers = new String[] { "Causa del error", "Texto", "Coordenadas" };
            } else {
                this.title = "";
                this.headers = new String[] { };
            }
        } else {
            this.title = "Reporte de Tokens";
            this.headers = new String[] { "Tipo de Token", "Lexema", "Coordenadas" };
        }

        this.log = lexico.getLog();
    }

    public Object[][] getData() {
        return this.data;
    }

    public String[] getHeaders() {
        return this.headers;
    }

    public String getLog() {
        return this.log;
    }

    public String getTitle() {
        return title;
    }

}
