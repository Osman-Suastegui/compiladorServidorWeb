package demoSpringBoot.demo.sintactico.Sentences.Sent;


import java.util.List;

public class NodoSi extends NodoAST{
    private NodoAST condition;
    private List<NodoAST> ifBody;
    private List<NodoAST> elseBody;

    public NodoSi(NodoAST condition, List<NodoAST> ifBody, List<NodoAST> elseBody) {
        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    @Override
    public void print(int depth) {

        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("-NodoIF");

        condition.print(depth + 1);

        if(ifBody.size() > 0){
            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }

            System.out.println("-IFBody");
        }



        for (NodoAST nodoAST : ifBody) {
            nodoAST.print(depth + 1);
        }

        if(elseBody.size() > 0){
            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }


            System.out.println("-ElseBody");
        }

        for (NodoAST nodoAST : elseBody) {
            nodoAST.print(depth + 1);
        }
    }
}