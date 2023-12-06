package demoSpringBoot.demo.sintactico.Sentences.Sent;

public class NodoEscribir extends NodoAST {

    private String contenidoEscribir;

    public NodoEscribir(String contenidoEscribir) {
        this.contenidoEscribir = contenidoEscribir;
    }
    @Override
    public void print(int depth) {

        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("-NodoEscribir");

    }
}
