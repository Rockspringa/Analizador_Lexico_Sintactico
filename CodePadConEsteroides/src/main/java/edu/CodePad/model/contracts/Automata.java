package edu.CodePad.model.contracts;

import edu.CodePad.model.analisis.Type;

public interface Automata {
    
    /**
     * El metodo se encargara de mover al automata entre estado ingresando un nuevo caracter.
     * @param ch es el caracter con el que se decidira a que estado cambiara el automata.
     * @return el estado o tipo actual del token.
     */
    Type getNextState(char ch);

    /**
     * El metodo devolvera lo que exista en el log interno de transiciones, donde se encuentra cada
     * transicion generada por el automata incluyendo los errores.
     * @return todo lo que hay en el log.
     */
    String getLog();
    
}
