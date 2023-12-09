package demoSpringBoot.demo.sintactico.Sentences.Sent;

import java.util.ArrayList;

public class NodoRelacional extends NodoAST{
    private String variable1;
    private String variable2;
    private String operator;

    public NodoRelacional(String variable1, String variable2, String operator) {
        super("NodoRelacional", new ArrayList<>());
        this.variable1 = variable1;
        this.variable2 = variable2;
        this.operator = operator;
    }

    @Override
    public void print(int depth) {


        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("-NodoRelacional" );
    }
}
