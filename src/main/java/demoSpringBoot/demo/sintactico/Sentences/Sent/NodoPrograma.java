package demoSpringBoot.demo.sintactico.Sentences.Sent;


import java.util.List;

public class NodoPrograma extends NodoAST {
    private List<NodoAST> sentencias;


    public NodoPrograma( List<NodoAST>  sentencias) {
        this.sentencias = sentencias;
    }

    @Override
    public void print(int depth) {
        System.out.println("NodoPrograma");

        for (NodoAST sentencia : sentencias) {
            sentencia.print(depth + 1);
        }
    }
}
