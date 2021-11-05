package edu.CodePad.model.sintactico.automatas;

import java.util.ArrayDeque;
import java.util.Deque;

import edu.CodePad.model.contracts.AnalyzeException;
import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.lexico.parts.wrappers.Token;
import edu.CodePad.model.sintactico.analisis.arbol.Arbol;
import edu.CodePad.model.sintactico.analisis.reglas.NoTerminal;
import edu.CodePad.model.sintactico.analisis.reglas.Terminal;
import edu.CodePad.model.sintactico.analisis.reglas.comodines.NTN;
import edu.CodePad.model.sintactico.analisis.reglas.terminales.Agrupadores;
import edu.CodePad.model.sintactico.analisis.reglas.terminales.Generales;
import edu.CodePad.model.sintactico.analisis.reglas.terminales.Operadores;
import edu.CodePad.model.sintactico.analisis.reglas.terminales.PalabrasClave;
import edu.CodePad.model.sintactico.excepciones.InvalidTokenException;

public class AutomataPila {

    private static final NoTerminal[] rows = NTN.values(); // orden en el enum

    private static final Terminal[] cols = { PalabrasClave.ESCRIBIR, PalabrasClave.REPETIR, PalabrasClave.SI,
            Generales.NUM, Generales.LITERAL, Generales.ID, PalabrasClave.FIN, Operadores.MULTIPLICAR, Operadores.SUMA,
            PalabrasClave.VERDADERO, PalabrasClave.FALSO, Agrupadores.PARENTESIS_IZQ, Agrupadores.PARENTESIS_DER,
            Generales.FINAL }; // orden de la tabla de analisis

    private static final Sintagma[][][] tablaAnalisis = {
            { { NTN.E, NTN.L }, { NTN.R, NTN.L }, { NTN.I, NTN.L }, null, null, { NTN.A, NTN.L }, null, null, null,
                    null, null, null, null, { Generales.EPSILON } },
            { { PalabrasClave.ESCRIBIR, NTN.S, PalabrasClave.FIN } },
            { null, null, null, { NTN.N }, { Generales.LITERAL }, { NTN.N } },
            { null, null, null, { Generales.NUM }, null, { Generales.ID } },
            { null, { PalabrasClave.REPETIR, NTN.N, PalabrasClave.INICIAR, NTN.G, PalabrasClave.FIN } },
            { { NTN.E, NTN.G }, null, null, null, null, null, { Generales.EPSILON } },
            { { NTN.E }, null, null, null, null, null, { Generales.EPSILON } },
            { null, null, { PalabrasClave.SI, NTN.C, PalabrasClave.ENTONCES, NTN.F, PalabrasClave.FIN } },
            { null, null, null, null, null, null, null, null, null, { PalabrasClave.VERDADERO },
                    { PalabrasClave.FALSO } },
            { null, null, null, null, null, { Generales.ID, Generales.IGUAL, NTN.X, PalabrasClave.FIN } },
            { null, null, null, { NTN.V, NTN.M, NTN.D }, null, { NTN.V, NTN.M, NTN.D }, null, null, null, null, null,
                    { NTN.V, NTN.M, NTN.D } },
            { null, null, null, null, null, null, { Generales.EPSILON }, { Operadores.MULTIPLICAR, NTN.X },
                    { Generales.EPSILON }, null, null, null, { Generales.EPSILON } },
            { null, null, null, null, null, null, { Generales.EPSILON }, null, { Operadores.SUMA, NTN.X }, null, null,
                    null, { Generales.EPSILON } },
            { null, null, null, { NTN.N }, null, { NTN.N }, null, null, null, null, null,
                    { Agrupadores.PARENTESIS_IZQ, NTN.X, Agrupadores.PARENTESIS_DER } } };

    private Deque<Sintagma> pila;
    private Arbol arbol;

    public AutomataPila() {
        this.pila = new ArrayDeque<>();
        this.pila.push(Generales.FINAL);
        this.pila.push(NTN.L);

        this.arbol = new Arbol(NTN.L);
    }

    public void getNextState(Token token) throws AnalyzeException {
        Sintagma sintagma = this.pila.peek();
        if (sintagma instanceof Terminal)
            reduce(token);
        else
            shift(token);
    }

    public boolean isFinished() {
        return this.pila.isEmpty();
    }

    private void reduce(Token token) throws AnalyzeException {
        Terminal terminal = (Terminal) this.pila.pop();
        if (! terminal.isCorrectToken(token))
            throw new InvalidTokenException(token);
        
        if (terminal == Generales.EPSILON)
            this.arbol.set(new Token(null, "", null));
        else if (terminal != Generales.FINAL)
            this.arbol.set(token);

        if (terminal == Generales.EPSILON)
            if (this.pila.peek() instanceof Terminal)
                reduce(token);
            else
                shift(token);
        else
            System.out.println("Reducido " + token);
    }

    private void shift(Token token) throws AnalyzeException {
        NoTerminal noTerminal = (NoTerminal) this.pila.pop();
        int row = getRow(noTerminal);
        int col = getCol(token);

        Sintagma[] sintagmas = tablaAnalisis[row][col];

        this.arbol.set(sintagmas);

        for (int i = sintagmas.length - 1; i >= 0; i--)
            this.pila.push(sintagmas[i]);

        if (sintagmas[0] instanceof Terminal)
            reduce(token);
        else
            shift(token);
    }

    private int getRow(NoTerminal noTerminal) {
        for (int i = 0; i < rows.length; i++)
            if (rows[i].equals(noTerminal))
                return i;
        return -1;
    }

    private int getCol(Token token) {
        int col = -1;
        for (int i = 0; i < cols.length; i++)
            if (cols[i].isCorrectToken(token))
                if (col == -1)
                    if (cols[i] == Generales.ID)
                        col = i;
                    else
                        return i;
                else
                    return i;
        return col;
    }

}
