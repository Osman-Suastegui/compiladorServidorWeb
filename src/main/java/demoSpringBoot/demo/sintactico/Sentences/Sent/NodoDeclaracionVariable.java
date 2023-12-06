package demoSpringBoot.demo.sintactico.Sentences.Sent;

public class NodoDeclaracionVariable extends NodoAST{


    private String tipo;
    private String nombre;

    public NodoDeclaracionVariable(String tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }
    @Override
    public void print(int depth) {

        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("-NodoDeclaracionVariable");

    }
}
