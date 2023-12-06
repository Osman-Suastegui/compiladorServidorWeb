package demoSpringBoot.demo.lexico;

public class Token {

    private TipoToken tipo;
    private String lexema;
    private Double valor = 0.0;

    private int renglon;
    private int columna;

    public Token(TipoToken tipo, String lexema, Double valor, int renglon, int columna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.valor = valor;
        this.renglon = renglon;
        this.columna = columna;
    }
    public Token() {

    }

    public String getLexema() {
        return lexema;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public void setRenglon(int renglon) {
        this.renglon = renglon;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getRenglon() {
        return this.renglon;
    }
    public int getColumna() {
        return this.columna;
    }
}
