package edu.CodePad.model.salida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.CodePad.model.contracts.Stages;
import edu.CodePad.model.salida.exceptions.NotAllowedForStage;

public class Variables implements Stages {

    private static final Map<String, Integer> VARIABLES = new HashMap<>();
    private static final List<Variables> VARIABLES_UNCOMMITEDS = new ArrayList<>();

    private final String nombre;
    private final Integer valor;

    public Variables(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;

        VARIABLES_UNCOMMITEDS.add(this);
    }

    public Variables() {
        this.nombre = null;
        this.valor = null;
    }

    private boolean isForRollback() {
        return this.nombre == null && this.valor == null;
    }

    public int getValor(String key) {
        commit();
        return VARIABLES.get(key);
    }

    @Override
    public void commit() {
        for (Variables var : VARIABLES_UNCOMMITEDS)
            VARIABLES.put(var.nombre, var.valor);

        VARIABLES_UNCOMMITEDS.clear();
    }

    @Override
    public void rollback() throws NotAllowedForStage {
        if (! isForRollback())
            throw new NotAllowedForStage();
            
        VARIABLES.clear();
        VARIABLES_UNCOMMITEDS.clear();
    }

}
