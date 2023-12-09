package demoSpringBoot.demo.sintactico.Sentences.Sent;


import java.util.ArrayList;
import java.util.List;

public class NodoSi extends NodoAST{
    private NodoAST condition;
    private List<NodoAST> ifBody;
    private List<NodoAST> elseBody;

    public NodoSi(NodoAST condition, List<NodoAST> ifBody, List<NodoAST> elseBody) {
        super("NodoSi", new ArrayList<>(combineLists(ifBody, elseBody,condition)));


        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;

    }
    private static List<NodoAST> combineLists(List<NodoAST> ifbody, List<NodoAST> elseBody,NodoAST condition) {
        NodoIFBody nodoIFBody = new NodoIFBody(ifbody);
        NodoElseBody nodoElseBody = new NodoElseBody(elseBody);
        List<NodoAST> combinedList = new ArrayList<>();
        combinedList.add(nodoIFBody);
        if(elseBody.size() > 0){
            combinedList.add(nodoElseBody);
        }
        combinedList.add(condition);
        return combinedList;
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