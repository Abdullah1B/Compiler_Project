import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConvertToDfa {
    private static Set<Integer>[] followPos;
    private static Node root;
    private static Set<State> DStates;

    private static Set<String> input;

    private static HashMap<Integer, String> charToNum;
    private static HashMap<List,Boolean> accept;
    private static HashMap<List,String> STATES;
    private static ArrayList<Transition> tran;
    public static void main(String[] args) {
        initialize();
    }
    public static void initialize() {
        Scanner in = new Scanner(System.in);

        DStates = new HashSet<>();
        input = new HashSet<String>();
        accept = new HashMap<>();
        STATES = new HashMap<>();
        tran = new ArrayList<>();

        String regualr = getRe(in);
        getCharToNum(regualr);

        SyntaxTree syntaxTree = new SyntaxTree(regualr);
        root = syntaxTree.getRoot();
        
        followPos = syntaxTree.getFollowPos();

        System.out.println("FirstPos:");
        printFirstPos(root);
        System.out.println("=================================================");
        System.out.println("\nLastPos:");
        printLastPos(root);
        System.out.println("=================================================");

        System.out.println(charToNum);
        System.out.println("\nFollowPos:");
        for (int i=0; i < followPos.length; i++){
            System.out.print(charToNum.get(i + 1));
            System.out.print(" : ");
            System.out.println(followPos[i]);
        }


        State q0 = createDFA();

        System.out.println("=============================================");
        System.out.println("State(s)");
        for (List L : STATES.keySet()){
            System.out.println(STATES.get(L) +" = "+L);
        } 
        System.out.println("=================================================");

        List<Integer> list = new ArrayList<Integer>(root.getFirstPos());
        System.out.println("Start State: "+ STATES.get(list));
        System.out.println("Accept State(s):");
        for (List L : accept.keySet()){
            if (accept.get(L)){
                System.out.print(STATES.get(L)+" , ");
            }
        }
        System.out.println("\n=================================================");

        System.out.println("\nState transition:\n");
        for (Transition item : tran){
            System.out.println(item.toString());
        }
    }
    private static State createDFA() {
        int id = 0;
        Set<Integer> FirstPos = root.getFirstPos();
        State q0 = new State(id++);
        q0.addSetToFollowSet(FirstPos);
        if(q0.getFollowSet().contains(followPos.length)){
            q0.setIsAccepted(true);
        }
        DStates.clear();
        DStates.add(q0);
        int k = 0;

        while(true){
            boolean exit = true;
            State s = null;

            for(State state : DStates){
                if(!state.getIsMarked()){
                    exit = false;
                    s = state;
                }
            }
            if (exit){
                break;
            }
            if(s.getIsMarked()){
                continue;
            }
            s.setIsMarked(true);
            Set<Integer> position = s.getFollowSet();
            for (String a : input){
                Set<Integer> U = new HashSet<>();
                for (int p : position){
                    if (charToNum.get(p).equals(a)){
                        U.addAll(followPos[p - 1]);
                    }
                }
                boolean Unmarked = false;
                State temp = null;
                for (State state : DStates){
                    if(state.getFollowSet().equals(U)){
                        temp = state;
                        Unmarked = true;
                        break;
                    }
                }
                if (!Unmarked){
                    State q = new State(id++);
                    q.addSetToFollowSet(U);
                    if (U.contains(followPos.length)){
                        q.setIsAccepted(true);
                    }
                    DStates.add(q);
                    temp = q;
                }

                if (!s.IsEmpty()){
                    accept.put(s.getArrayOfFollowSet(),s.getIsAccepted());
                    
                    if (!a.equals("#")){
                        if(!STATES.containsKey(s.getArrayOfFollowSet())){

                            STATES.put(s.getArrayOfFollowSet(),"q"+k);
                            s.setName("q"+k);
                            k++;

                        }

                        tran.add(new Transition(s, a, temp));
                    }
                    
                }

        

                s.addMove(a, temp);

            }
        }
        return q0;
    }
    
    private static void getCharToNum(String regualr) {
        Set<Character> op = new HashSet<>();
        Character[] ch = {'(', ')', '*', '|', '&', '.', '\\', '[', ']', '+'};
        op.addAll(Arrays.asList(ch));
        input = new HashSet<>();
        charToNum = new HashMap<>();
        int num = 1;
        for(int i = 0; i < regualr.length(); i++){
            char At = regualr.charAt(i);
            if(!op.contains(At)){
                input.add("" + At);
                charToNum.put(num++, ""+ At);
            }
        }
    }
    public static void printFirstPos(Node node){
        if (node == null){
            return;
        }
        System.out.print(node.getChar());
        System.out.print(" : ");
        System.out.print(node.getFirstPos());
        System.out.println();
        printFirstPos(node.getLeftNode());
        printFirstPos(node.getRightNode());
    }
    public static void printLastPos(Node node){
        if (node == null){
            return;
        }
        System.out.print(node.getChar());
        System.out.print(" : ");
        System.out.print(node.getLastPos());
        System.out.println();
        printLastPos(node.getLeftNode());
        printLastPos(node.getRightNode());
    }

    private static String getRe(Scanner in) {
        System.out.print("Enter a regex: ");
        String regex = in.nextLine();
        return regex+"#";
    }
    
}
