package demoSpringBoot.demo.sintactico.Sentences.Sent;

public class NodoAsignacion extends NodoAST{

    String nombreVariable;
    String valor1;
    String valor2;
    String operador;

    public NodoAsignacion(String nombreVariable, String valor1, String valor2, String operador) {
        this.nombreVariable = nombreVariable;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.operador = operador;
    }

    public NodoAsignacion(String nombreVariable, String valor1) {
        this.nombreVariable = nombreVariable;
        this.valor1 = valor1;
    }

    @Override
    public void print(int depth) {

            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }
            System.out.println("-NodoAsignacion " + nombreVariable);

    }
}
