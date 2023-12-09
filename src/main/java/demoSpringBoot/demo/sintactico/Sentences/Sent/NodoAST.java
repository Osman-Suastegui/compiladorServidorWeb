package demoSpringBoot.demo.sintactico.Sentences.Sent;

import java.util.List;

public abstract class NodoAST {
    protected String name;
    protected List<NodoAST> sentencias;

    public NodoAST(String name, List<NodoAST> sentencias) {
        this.name = name;
        this.sentencias = sentencias;
    }

    abstract public void print(int depth);
    public String getName(){
        return name;
    }
    public List<NodoAST> getChildren(){
        return sentencias;
    }

}
