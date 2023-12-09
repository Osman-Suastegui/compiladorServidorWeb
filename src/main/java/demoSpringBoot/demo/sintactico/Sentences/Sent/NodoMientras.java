package demoSpringBoot.demo.sintactico.Sentences.Sent;



import java.util.ArrayList;
import java.util.List;


public class NodoMientras extends NodoAST {


    private NodoAST condition;
    private List<NodoAST> bodyMientras;

    public NodoMientras(NodoAST condition, List<NodoAST> bodyMientras) {
        super("NodoMientras", combineLists(bodyMientras,condition));
        this.condition = condition;
        this.bodyMientras = bodyMientras;
    }
    private static List<NodoAST> combineLists(List<NodoAST> bodyMientras,NodoAST condition) {
        List<NodoAST> combinedList = new ArrayList<>();
        combinedList.addAll(bodyMientras);
        combinedList.add(condition);
        return combinedList;
    }


    @Override
    public void print(int depth) {

        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("-NodoMientras");

        condition.print(depth + 1);
        for (NodoAST nodoAST : bodyMientras) {
            nodoAST.print(depth + 1);
        }
    }
}
