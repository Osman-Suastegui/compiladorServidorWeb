package demoSpringBoot.demo.lexico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutomataIdentificadores {


    private List<Contenido> contenido;
    private LinkList listaEnlazada;


    public AutomataIdentificadores(List<Contenido> contenido, LinkList listaEnlazada) {
        this.contenido = contenido;
        this.listaEnlazada = listaEnlazada;
    }
    private Boolean esPalabraReservada(String palabra){
        ArrayList<String> misPalabrasReservadas = new ArrayList<>();
        List<String> tmp  = Arrays.asList("Inicio","Fin","Si","Entonces","Sino","FinSi","Mientras","FinMientras","Escribir","Leer","Num","Cadena","Const");
        misPalabrasReservadas.addAll(tmp);
        return misPalabrasReservadas.contains(palabra);
    }

    public int validar(int currindx,String lexema,int startRenglon,int startColumna) throws Exception {


        if(currindx >= contenido.size()){
            Token token = new Token();
            token.setLexema(lexema);

            if(esPalabraReservada(lexema)){
                token.setTipo(TipoToken.PALABRA_RESERVADA);
            }else{
                token.setTipo(TipoToken.IDENTIFICADOR);
            }
            token.setColumna(startColumna);
            token.setRenglon(startRenglon);

            listaEnlazada.insertar(token);
            return  currindx;
        }

        int c = contenido.get(currindx).getCaracter();
        if(Character.isDigit(c) || Character.isLetter(c) ) {
            lexema += (char) c;
            return this.validar(currindx + 1, lexema,startRenglon,startColumna);
        }

        Token token = new Token();
        token.setLexema(lexema);
        if(esPalabraReservada(lexema)){
            token.setTipo(TipoToken.PALABRA_RESERVADA);
        }else{
            token.setTipo(TipoToken.IDENTIFICADOR);
        }
        token.setColumna(startColumna);
        token.setRenglon(startRenglon);
        listaEnlazada.insertar(token);
        return currindx;

    }
}