package edu.CodePad.model.lexico.automatas;

import edu.CodePad.model.analisis.Type;
import edu.CodePad.model.contracts.Automata;
import edu.CodePad.model.lexico.excepciones.InvalidCharacterException;
import edu.CodePad.model.lexico.parts.Alfabeto;
import edu.CodePad.model.wrappers.Token;

/**
 * El objeto de AFDToken se encarga de ser el analizador de todos los tokens.
 * 
 * @param transiciones son en escencia la matriz de transisciones o las
 *                     funciones de transisicon, donde las columnas de esta
 *                     seran los grupos de caracteres que representan el
 *                     alfabeto, y las filas representan los distintos estados
 *                     entre los cuales se movera el automata, los enteros que
 *                     se declararan seran el estado al cual se trasladaran, o
 *                     sea que seria el numero de fila al cual se trasladara.
 * @param tokens       sera la representacion del token que se obtiene del
 *                     estado, si es distinto a <code>VACIO</code> o <code>
 *                     INCOMPLETO</code> sera de aceptacion.
 * @param alfabeto     sera el alfabeto que acepta y usara el automata, el
 *                     alfabeto puede ser conformado caracteres o por cadenas de
 *                     caracteres, estos representando un conjunto dentro del
 *                     conjunto <code>alfabeto</code>.
 */
public class AFDToken implements Automata {
    private final StringBuilder LOG = new StringBuilder();
    private int state = 0;

    private final Alfabeto alfabeto = new Alfabeto(
            new char[][] {  { '_', '_', 'a', 'z', 'A', 'Z' },
                            { '_', '_', '-', '-', 'a', 'z', 'A', 'Z' },
                            { '-', '-', '1', '9' },
                            { '0', '9' },
                            { '"', '"' },
                            { '<', '>', ':', ';', ',', ',', '\'', '\'' },
                            { '*', '+', '-', '-' },
                            { '(', ')' },
                            { '/', '/' },
                            { ' ', ' ', '\t', '\t' },
                            { '\r', '\r', '\n', '\n', '\f', '\f' }
                        });

    private final int[][] transiciones = new int[][] {
                            {  1, -1,  2, -1,  4, -1,  7,  6,  9,  0,  0 }, // 0. INICIO
                            {  1,  1,  1,  1, -1, -1, -1,  6, -1,  0,  0 }, // 1. ID
                            { -1, -1, -1,  2, -1, -1, -1,  6, -1,  0,  0 }, // 2. NUMBER
                            { -1, -1, -1, -1, -1, -1, -1, -1, -1,  0,  0 }, // 3. LITERAL
                            {  4,  4,  4,  4,  3,  4,  4,  4,  4,  4, -1 }, // 4. LITERAL_INCOMPLETO
                            {  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  0 }, // 5. COMENTARIO
                            {  1, -1,  2, -1, -1, -1, -1,  6, -1,  0,  0 }, // 6. AGRUPACION
                            { -1, -1, -1, -1, -1, -1, -1, -1, -1,  0,  0 }, // 7. OPERADOR
                            { -1, -1, -1, -1, -1, -1, -1, -1, -1,  0,  0 }, // 8. IGUAL
                            { -1, -1, -1, -1, -1, -1, -1, -1,  5, -1, -1 }, // 9. COMENTARIO_INCOMPLETO
                        };

    private final Type[] types = new Type[] {
                            Type.INICIO,
                            Type.ID,
                            Type.NUMBER,
                            Type.LITERAL,
                            Type.LITERAL_INCOMPLETO,
                            Type.COMENTARIO,
                            Type.AGRUPACION,
                            Type.OPERADOR,
                            Type.IGUAL,
                            Type.COMENTARIO_INCOMPLETO
                        };

    /**
     * Verifica que un caracter especifico exista dentro del alfabeto, de lo contrario
     * lanzara un error. Si ocurre un error lo guarda en el log con su causa.
     * 
     * @param ch el caracter a buscar en el alfabeto.
     * @return la columna a la que pertenece dentro de la matriz de transiciones.
     * @throws InvalidCharacterException cuando el caracter no existe en el alfabeto.
     */
    private int verifyCharacter(char ch) {
        int col = alfabeto.getIndex(ch);

        if (col < 0 || col >= alfabeto.getCantConjuntos()) {
            String msg = "El caracter '" + ch + "' no pertenece al alfabeto aceptado.";

            LOG.append(msg + "\n");
            state = 0;

            throw new InvalidCharacterException(new Token(types[state], "", null));
        }

        return col;
    }

    /**
     * Verifica que el nuevo estado exista, o que sea diferente a <code>-1</code>.
     * De existir un error se guarda en el log la causa del mismo.
     * 
     * @param col es la columna del caracter que se ingreso.
     * @param ch es el caracter que se ingreso.
     * @return un entero que es el nuevo estado del automata.
     * @throws InvalidCharacterException cuando el nuevo estado es igual a -1.
     */
    private int verifyNextState(int col, char ch) {
        int tempState = transiciones[state][col];

        if (tempState < 0) {
            String msg = "Se ingreso un caracter invalido cuando se esperaba ";

            msg += switch (types[state]) {
                case ID                     -> "una letra o un digito o un espacio";
                case NUMBER                 -> "un digito o un espacio";
                case LITERAL                -> "algun tipo de espacio";
                case LITERAL_INCOMPLETO     -> "cerrar el literal antes del salto de linea";
                case AGRUPACION             -> "una letra o un digito o un espacio";
                case OPERADOR               -> "algun tipo de espacio";
                case COMENTARIO_INCOMPLETO  -> "una diagonal";
                case IGUAL                  -> "algun tipo de espacio";
                default                     -> "ah... no lo se..";
            };

            if (ch == '\n') msg += ", no un salto de linea.";
            else if (ch == '\r') msg += ", no un retorno de carro.";
            else if (ch == '\f') msg += ", no un salto de pagina.";
            else if (ch == '\t') msg += ", no una tabulacion.";
            else if (ch == ' ') msg += ", no un espacio.";
            else msg += ", no el caracter '" + ch + "'.";
            LOG.append(msg + "\n");
            state = 0;

            throw new InvalidCharacterException(new Token(types[state], "", null));
        }

        return tempState;
    }

    @Override
    public Type getNextState(char ch) {
        int col = verifyCharacter(ch);

        String estado = "Transicion del estado '" + state + "' al estado '";
        
        state = verifyNextState(col, ch);

        LOG.append(estado + state + "' con el caracter '");

        if (ch == '\n' || ch == '\r') LOG.append("\\n");
        else LOG.append(ch);

        LOG.append("'. Token actual: " + types[state] + "\n");

        return types[state];
    }

    @Override
    public String getLog() {
        return LOG.toString();
    }
    
}
