package demoSpringBoot.demo.lexico;

public class Contenido {

    private char caracter;
    private int renglon;
    private int columna;


    public Contenido(char caracter, int renglon, int columna) {
        this.caracter = caracter;
        this.renglon = renglon;
        this.columna = columna;
    }

    public char getCaracter() {
        return caracter;
    }

    public int getRenglon() {
        return renglon;
    }

    public int getColumna() {
        return columna;
    }
}
