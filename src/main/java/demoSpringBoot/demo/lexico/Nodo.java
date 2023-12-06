package demoSpringBoot.demo.lexico;

public class Nodo {
    private Nodo anterior;
    private Token token;
    private Nodo siguiente;

    public Nodo(Token info) {
        this.anterior = null;
        this.token = info;
        this.siguiente = null;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public Token getToken() {
        return token;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
