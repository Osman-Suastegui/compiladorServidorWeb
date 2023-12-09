package demoSpringBoot.demo.sintactico.Sentences.Sent;

import java.util.List;

public class NodoIFBody  extends NodoAST{

    public NodoIFBody(List<NodoAST> sentencias) {
        super("IfBody", sentencias);
    }

    @Override
    public void print(int depth) {

    }
}
