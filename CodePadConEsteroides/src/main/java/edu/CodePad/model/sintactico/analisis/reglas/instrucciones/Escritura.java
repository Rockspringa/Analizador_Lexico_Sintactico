package edu.CodePad.model.sintactico.analisis.reglas.instrucciones;

import edu.CodePad.model.contracts.Sintagma;
import edu.CodePad.model.lexico.analisis.Type;
import edu.CodePad.model.salida.compiles.Documento;
import edu.CodePad.model.salida.compiles.Variables;
import edu.CodePad.model.sintactico.analisis.arbol.Nodo;
import edu.CodePad.model.sintactico.analisis.arbol.NodoHoja;
import edu.CodePad.model.sintactico.analisis.reglas.Instruccion;
import edu.CodePad.model.sintactico.excepciones.VariableNoInicializada;

public class Escritura extends Instruccion {

    public Escritura(Sintagma[][] sintagmas) {
        super(sintagmas);
    }

    @Override
    public void doAction(Nodo nodo) throws VariableNoInicializada {
        Nodo S = (Nodo) nodo.getHijos()[1];
        StringBuilder line = new StringBuilder();

        if (S.getHijos()[0] instanceof NodoHoja) {
            char[] lineTmp = ((NodoHoja) S.getHijos()[0]).getToken().getLexema().toCharArray();

            for (int i = 1; i < lineTmp.length - 1; i++)
                line.append(lineTmp[i]);
        } else {
            NodoHoja nodoValor = ((NodoHoja) ((Nodo) S.getHijos()[0]).getHijos()[0]);

            if (nodoValor.getToken().getTipo() == Type.ID)
                try {
                    line.append(new Variables().getValor(nodoValor.getToken().getLexema()));
                } catch (NullPointerException e) {
                    throw new VariableNoInicializada(nodoValor.getToken().getLexema());
                }
            else
                line.append(Integer.parseInt(nodoValor.getToken().getLexema()));
        }

        new Documento(line.toString());
    }

}
