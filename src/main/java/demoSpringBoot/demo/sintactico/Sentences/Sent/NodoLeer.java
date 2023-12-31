package demoSpringBoot.demo.sintactico.Sentences.Sent;

import java.util.ArrayList;

public class NodoLeer extends NodoAST {
    String identificador;

public NodoLeer(String identificador) {
    super("NodoLeer", new ArrayList<>());
    this.identificador = identificador;
    }

    @Override
    public void print(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("-NodoLeer");
    }
}
