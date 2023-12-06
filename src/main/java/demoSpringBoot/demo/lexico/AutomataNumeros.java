package demoSpringBoot.demo.lexico;

import java.util.List;

public class AutomataNumeros {

    private List<Contenido> contenido;
    private LinkList listaEnlazada;

    public AutomataNumeros(List<Contenido> contenido, LinkList listaEnlazada) {
        this.contenido = contenido;
        this.listaEnlazada = listaEnlazada;
    }

    public int validar(int currIndx) {
        char currChar = contenido.get(currIndx).getCaracter();
        int currRenglon = contenido.get(currIndx).getRenglon();
        int currColumna = contenido.get(currIndx).getColumna();
        String lexema = "";
        while (currIndx < contenido.size() && (Character.isDigit(currChar) || (currChar == '.' && lexema.indexOf(".") == -1))) {

            lexema += currChar;
            currIndx++;
            if (currIndx < contenido.size()) {
                currChar = contenido.get(currIndx).getCaracter();
            }
        }


        Token token = new Token();
        token.setLexema(lexema);
        token.setTipo(TipoToken.NUMERO);
        token.setValor(Double.parseDouble(lexema));
        token.setRenglon(currRenglon);
        token.setColumna(currColumna);
        listaEnlazada.insertar(token);
        return currIndx;

    }
}
