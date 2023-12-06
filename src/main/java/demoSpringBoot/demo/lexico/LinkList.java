package demoSpringBoot.demo.lexico;

public class LinkList {

    private Nodo raiz;
    private Nodo ultimo;

    public LinkList() {
        this.raiz = null;
        this.ultimo = null;
    }

    public void insertar(Token token){
        Nodo nuevo = new Nodo(token);
        if(raiz == null){
            raiz = nuevo;
            ultimo = nuevo;
        }else{
            nuevo.setAnterior(ultimo);
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
    }
    public void imprimir(){
        Nodo aux = raiz;
        while(aux != null){
            aux = aux.getSiguiente();
        }
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public Nodo getUltimo(){return ultimo;}
}
