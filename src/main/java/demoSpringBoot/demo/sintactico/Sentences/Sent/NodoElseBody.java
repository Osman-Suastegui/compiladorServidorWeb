package demoSpringBoot.demo.sintactico.Sentences.Sent;

import java.util.List;

public class NodoElseBody extends NodoAST{
    public NodoElseBody(List<NodoAST> sentencias) {
        super("ElseBody", sentencias);
    }

    @Override
    public void print(int depth) {

    }
}
