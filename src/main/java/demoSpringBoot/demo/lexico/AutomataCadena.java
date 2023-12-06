package demoSpringBoot.demo.lexico;


import demoSpringBoot.demo.lexico.tablaDeSimbolos.ManejadorErrores;

import java.util.List;

public class AutomataCadena {

    private List<Contenido> contenido ;
    private LinkList listaEnlazada;
    public AutomataCadena(List<Contenido> contenido, LinkList listaEnlazada) {
        this.contenido = contenido;
        this.listaEnlazada = listaEnlazada;
    }

    public int validar(int indx) throws Exception {
        ManejadorErrores manejadorErrores = ManejadorErrores.obtenerInstancia();
        int fila = this.contenido.get(indx).getRenglon();
        int currRenglon = this.contenido.get(indx).getRenglon();
        int currColumna = this.contenido.get(indx).getColumna();
        String lexema = "";
        int comillas = 34;

        int j = indx + 1;
        while (j < this.contenido.size()) {
            int currCaracter = this.contenido.get(j).getCaracter();

            if (this.contenido.get(j).getRenglon() != fila) {
                manejadorErrores.agregarError("Error en la fila " + fila + " no hay comillas de cierre");
                return j;
            }

            if (currCaracter == comillas) {
                Token token = new Token();
                token.setLexema(lexema);
                token.setTipo(TipoToken.CADENA);
                token.setColumna(currColumna);
                token.setRenglon(currRenglon);
                listaEnlazada.insertar(token);
                return j + 1;
            }


            lexema += (char) currCaracter;
            j++;
        }
        manejadorErrores.agregarError("Error en la fila " + fila + " no hay comillas de cierre");
        return j;

    }
}
