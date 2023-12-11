package demoSpringBoot.demo.sintactico;


import demoSpringBoot.demo.lexico.LinkList;
import demoSpringBoot.demo.lexico.Nodo;
import demoSpringBoot.demo.lexico.TipoToken;
import demoSpringBoot.demo.lexico.tablaDeSimbolos.ManejadorErrores;
import demoSpringBoot.demo.sintactico.Sentences.Sent.*;

import java.util.ArrayList;
import java.util.List;

public class MainSintactico {

    boolean seEncontroErrorSintactico = false;

    LinkList tokens;
    ManejadorErrores manejadorErrores;
    private Nodo current;

    public MainSintactico(LinkList tokens){
        manejadorErrores = ManejadorErrores.obtenerInstancia();
        this.tokens = tokens;
        NodoAST nodo = this.GramaticaPrograma();
        if(manejadorErrores.getCantidadErrores() == 0){
            nodo.print(0);
        }
    }

    private void guardarError(String valorEsperado){
        if(this.seEncontroErrorSintactico){
            return;
        }

        this.seEncontroErrorSintactico = true;
        int linea = 0;
        if(this.current == null){
            if(this.tokens.getUltimo() != null){
                linea = this.tokens.getUltimo().getToken().getRenglon();
            }
            manejadorErrores.agregarError("Error en la linea " +linea+ " : esperaba " + valorEsperado);
            return;
        }
        linea = this.current.getToken().getRenglon();

        String valorEncontrado = this.current.getToken().getLexema();
        manejadorErrores.agregarError("Error en la linea " +linea+ " : se encontro " + valorEncontrado + " Se esperaba " + valorEsperado);
    }



    public NodoPrograma GramaticaPrograma() {
        this.current = tokens.getRaiz();
        // Validar que el programa comience con el token "inicio"
        if(!this.current.getToken().getLexema().equals("Inicio")){
            guardarError("Inicio");
            return NodoPrograma.obtenerInstancia(new ArrayList<>());
        }
        this.current = this.current.getSiguiente();

        List<NodoAST> nodosHijosProgramas = new ArrayList<>();

        while (this.current != null && !this.current.getToken().getLexema().equals("Fin") ){
            NodoAST nodo  = VerificarTipoDeNodo();
            if(nodo == null){
                break;
            }
            nodosHijosProgramas.add(nodo);
        }

        // Validar que el programa termine con el token "fin"
       if(this.current == null || !this.current.getToken().getLexema().equals("Fin")) {
           guardarError("Fin");
           return null;
       }

        return NodoPrograma.obtenerInstancia(nodosHijosProgramas);
    }
//    una funcion para verificar que tipo de AST ES
    public NodoAST VerificarTipoDeNodo(){
        switch (this.current.getToken().getLexema()){
            case "Si":
                return GramaticaSi();
            case "Mientras":
                return GramaticaMientras();
            case "Escribir":
                return GramaticaEscribir();
            case "Leer":
                return GramaticaLeer();
            case "Num" :
                return GramaticaDeclaracionVariable();
            case "Cadena" :
                return GramaticaDeclaracionVariable();
            case "Const":
                return GramaticaDeclaracionConstante();
        }
        if(this.current.getToken().getTipo().equals(TipoToken.IDENTIFICADOR)){
            return GramaticaAsignacion();
        }
        return null;

    }



    private NodoAST GramaticaSi() {

        if(!this.current.getToken().getLexema().equals("Si")){
            guardarError("Si");
            return null;
        }
        this.current = this.current.getSiguiente();
        NodoAST condition = GramaticaRelacional();
        if(condition == null){
            return null;
        }
        if(this.current == null || !this.current.getToken().getLexema().equals("Entonces")){
            guardarError("Entonces");
            return null;
        }
        this.current = this.current.getSiguiente();
        List<NodoAST> bodySi = new ArrayList<>();
        List<NodoAST> bodySino = new ArrayList<>();
        while (this.current != null && !this.current.getToken().getLexema().equals("FinSi")){

            if(!this.current.getToken().getLexema().equals("Sino")){
                NodoAST nodoSi  = VerificarTipoDeNodo();
                if(nodoSi == null){
                    String valorEsperado = "Finsi";
                    guardarError(valorEsperado);
                    return null;
                }
                bodySi.add(nodoSi);

            }

            if(this.current.getToken().getLexema().equals("Sino")){
                this.current = this.current.getSiguiente();

                while (this.current != null && !this.current.getToken().getLexema().equals("FinSi")) {
                    NodoAST nodoSino  = VerificarTipoDeNodo();

                    if(nodoSino == null){

                        String valorEsperado = "FinSi";
                        guardarError(valorEsperado);
                        return null;
                    }
                    bodySino.add(nodoSino);

                }
            }
        }

        if(this.current == null){
           guardarError("FinSi");
            return null;
        }
        this.current = this.current.getSiguiente();
        return new NodoSi(condition, bodySi,bodySino);


    }
    private  NodoAST GramaticaRelacional(){
        if(this.current == null || (!this.current.getToken().getTipo().equals(TipoToken.IDENTIFICADOR) && !this.current.getToken().getTipo().equals(TipoToken.NUMERO) && !this.current.getToken().getTipo().equals(TipoToken.CADENA))){
            guardarError("identificador, numero o cadena ");
            return null;
        }
        String var1 = this.current.getToken().getLexema();
        this.current = this.current.getSiguiente();
        if(this.current == null || !this.current.getToken().getTipo().equals( TipoToken.OP_RELACIONAL)){
            guardarError("operador relacional");
            return null;
        }

        String operador = this.current.getToken().getLexema();
        this.current = this.current.getSiguiente();

        if(this.current == null || (!this.current.getToken().getTipo().equals(TipoToken.IDENTIFICADOR) && !this.current.getToken().getTipo().equals(TipoToken.NUMERO) && !this.current.getToken().getTipo().equals(TipoToken.CADENA))){
            guardarError("identificador, numero o cadena ");
            return null;
        }
        String var2 = this.current.getToken().getLexema();
        this.current = this.current.getSiguiente();
        NodoRelacional nodoRelacional = new NodoRelacional(var1, var2, operador);
        return nodoRelacional;

    }
    private NodoAST GramaticaMientras() {
        if(!this.current.getToken().getLexema().equals("Mientras") ){
            guardarError("Mientras");
            return null;
        }
        this.current = this.current.getSiguiente();
        NodoAST condition = GramaticaRelacional();
        if(condition == null){
            return null;
        }
        if(this.current == null || !this.current.getToken().getLexema().equals("Entonces")){
            guardarError("Entonces");
            return null;
        }
        this.current = this.current.getSiguiente();
        List<NodoAST> body = new ArrayList<>();
        while (this.current != null && !this.current.getToken().getLexema().equals("FinMientras")){
            NodoAST nodo  = VerificarTipoDeNodo();
            if(nodo == null){
                guardarError("FinMientras");
                return null;
            }
            body.add(nodo);
//            this.current = this.current.getSiguiente();
        }
        if(this.current == null || !this.current.getToken().getLexema().equals("FinMientras")){
            guardarError("FinMientras");
            return null;
        }
        this.current = this.current.getSiguiente();
        return new NodoMientras(condition, body);
    }
    private NodoAST GramaticaEscribir() {
        if(!this.current.getToken().getLexema().equals("Escribir")){
            guardarError("Escribir");
            return null;
        }
        this.current = this.current.getSiguiente();
        if(this.current == null){
            guardarError("identificador, numero o cadena ");
            return null;
        }
        String contenidoNodoEscribir;
        if(this.current.getToken().getTipo().equals(TipoToken.CADENA)){
            contenidoNodoEscribir = this.current.getToken().getLexema();
        }else if(this.current.getToken().getTipo().equals(TipoToken.NUMERO)){
            contenidoNodoEscribir = this.current.getToken().getLexema();
        }else if(this.current.getToken().getTipo().equals(TipoToken.IDENTIFICADOR)){
            contenidoNodoEscribir = this.current.getToken().getLexema();
        }
        else {
            guardarError("identificador, numero o cadena ");
            return null;
        }
        this.current = this.current.getSiguiente();

        return new NodoEscribir(contenidoNodoEscribir);
    }

    public NodoAST GramaticaLeer() {
        if(!this.current.getToken().getLexema().equals("Leer")){
            guardarError("Leer");
            return null;
        }
        this.current = this.current.getSiguiente();
        if(this.current == null || !this.current.getToken().getTipo().equals(TipoToken.IDENTIFICADOR)){
            if(this.current == null){
                guardarError("identificador");
                return null;
            }
            guardarError("identificador");

            return null;
        }
        String identificadorLeer;
        identificadorLeer = this.current.getToken().getLexema();
        this.current = this.current.getSiguiente();
        return new NodoLeer(identificadorLeer);
    }
    private NodoAST GramaticaDeclaracionVariable() {
        if(!this.current.getToken().getLexema().equals("Num") && !this.current.getToken().getLexema().equals("Cadena")){
            guardarError("Num o Cadena");
            return null;
        }
        String tipo = this.current.getToken().getLexema();
        this.current = this.current.getSiguiente();
        if(this.current == null || !this.current.getToken().getTipo().equals(TipoToken.IDENTIFICADOR)){
            guardarError("identificador");
            return null;
        }


        String nombre = this.current.getToken().getLexema();
        this.current = this.current.getSiguiente();
        return new NodoDeclaracionVariable(tipo, nombre);
    }
    private NodoAST GramaticaAsignacion() {
        if(!this.current.getToken().getTipo().equals(TipoToken.IDENTIFICADOR)){
            guardarError("identificador");
            return null;
        }
        String nombre = this.current.getToken().getLexema();
        this.current = this.current.getSiguiente();
        if(this.current == null || !this.current.getToken().getTipo().equals(TipoToken.OP_ASIGNACION)){
            guardarError("=");
            return null;
        }
        this.current = this.current.getSiguiente();
        if(this.current == null || (!this.current.getToken().getTipo().equals(TipoToken.IDENTIFICADOR) && !this.current.getToken().getTipo().equals(TipoToken.NUMERO) && !this.current.getToken().getTipo().equals(TipoToken.CADENA))){
            guardarError("identificador, numero o cadena ");
            return null;
        }
        String valor1 = this.current.getToken().getLexema();
        this.current = this.current.getSiguiente();
        if(this.current == null){
            return null;
        }
        if(this.current.getToken().getTipo().equals(TipoToken.OP_ARITMETICO)){
            String operador = this.current.getToken().getLexema();
            this.current = this.current.getSiguiente();
            if(this.current == null || (!this.current.getToken().getTipo().equals(TipoToken.IDENTIFICADOR) && !this.current.getToken().getTipo().equals(TipoToken.NUMERO))){
                guardarError("identificador o numero ");
                return null;
            }
            String valor2 = this.current.getToken().getLexema();
            this.current = this.current.getSiguiente();
            return new NodoAsignacion(nombre, valor1, valor2, operador);
        }

        return new NodoAsignacion(nombre, valor1);
    }

    private NodoAST GramaticaDeclaracionConstante() {
        if(!this.current.getToken().getLexema().equals("Const")){
            guardarError("Const");
            return null;
        }
        this.current = this.current.getSiguiente();
        if(this.current == null){
            guardarError("Num o Cadena");
            return null;
        }
        if(!this.current.getToken().getLexema().equals("Num") && !this.current.getToken().getLexema().equals("Cadena")){
            guardarError("Num o Cadena");
            return null;
        }
        String tipo = this.current.getToken().getLexema();
        this.current = this.current.getSiguiente();
        if(this.current == null || !this.current.getToken().getTipo().equals(TipoToken.IDENTIFICADOR)){
            guardarError("identificador");
            return null;
        }
        String nombre = this.current.getToken().getLexema();
        this.current = this.current.getSiguiente();
        return new NodoDeclaracionConstante(tipo, nombre);

    }

}
