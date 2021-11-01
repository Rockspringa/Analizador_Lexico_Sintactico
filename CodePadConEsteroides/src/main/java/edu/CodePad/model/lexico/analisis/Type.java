package edu.CodePad.model.lexico.analisis;

public enum Type {

    NUMBER("Entero"), ID("Identificador"), LITERAL("Literal"), INICIO("Vacio"), COMENTARIO("Comentario"),
    COMENTARIO_INCOMPLETO("Comentario sin completar"), OPERADOR("Operador"), AGRUPACION("Agrupacion"),
    IGUAL("Igual"), LITERAL_INCOMPLETO("Literal sin completar"), FINAL("$");

    private final String s;

    private Type(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return this.s;
    }

}
