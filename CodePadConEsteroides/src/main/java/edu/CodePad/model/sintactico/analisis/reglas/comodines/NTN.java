package edu.CodePad.model.sintactico.analisis.reglas.comodines;

import edu.CodePad.model.sintactico.analisis.reglas.NoTerminal;
import edu.CodePad.model.sintactico.analisis.reglas.instrucciones.Asignacion;
import edu.CodePad.model.sintactico.analisis.reglas.instrucciones.Condicional;
import edu.CodePad.model.sintactico.analisis.reglas.instrucciones.Escritura;
import edu.CodePad.model.sintactico.analisis.reglas.instrucciones.Expresion;
import edu.CodePad.model.sintactico.analisis.reglas.instrucciones.Repetir;

public interface NTN { // No Terminales Nullos abrebiado
    NoTerminal L = new NoTerminal(null);
    NoTerminal E = new Escritura(null);
    NoTerminal S = new NoTerminal(null);
    NoTerminal N = new NoTerminal(null);
    NoTerminal R = new Repetir(null);
    NoTerminal G = new NoTerminal(null);
    NoTerminal F = new NoTerminal(null);
    NoTerminal I = new Condicional(null);
    NoTerminal C = new NoTerminal(null);
    NoTerminal A = new Asignacion(null);
    NoTerminal X = new Expresion(null);
    NoTerminal M = new NoTerminal(null);
    NoTerminal D = new NoTerminal(null);
    NoTerminal V = new NoTerminal(null);

    public static NoTerminal[] values() {
        NoTerminal[] nts = new NoTerminal[14];
        nts[0] = L;
        nts[1] = E;
        nts[2] = S;
        nts[3] = N;
        nts[4] = R;
        nts[5] = G;
        nts[6] = F;
        nts[7] = I;
        nts[8] = C;
        nts[9] = A;
        nts[10] = X;
        nts[11] = M;
        nts[12] = D;
        nts[13] = V;

        return nts;
    }
}
