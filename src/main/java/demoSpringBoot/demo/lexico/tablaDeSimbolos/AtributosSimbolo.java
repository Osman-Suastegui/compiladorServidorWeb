package demoSpringBoot.demo.lexico.tablaDeSimbolos;


import demoSpringBoot.demo.lexico.TipoToken;

public class AtributosSimbolo {
    private String lexema;
    private TipoToken tipo;
    private Double valor;
    private int renglon;
    private int columna;

    public AtributosSimbolo() {
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getRenglon() {
        return renglon;
    }

    public void setRenglon(int renglon) {
        this.renglon = renglon;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {

        return String.format("%-20s",lexema) + String.format("%-20s",tipo) + String.format("%-20s",valor)  + String.format("%-20s",renglon) + String.format("%-20s",columna);
    }
}
