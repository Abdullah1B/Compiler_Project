// import java.util.*;
// public class Tree {
//     private int leafId = 0;

//     private Stack<Node> nodeStack = new Stack<>();
//     private Stack<Character> operator = new Stack<>();

//     private Set<Character> input = new HashSet<Character>();
//     private ArrayList<Character> op = new ArrayList<>();

//     public Node createTree(String regular){
//         Character [] ops = {'*','|','&'};
//         op.addAll(Arrays.asList(ops));
        
//         // Set all the availabe character and add it to input set
//         Character ch[] = new Character[26 + 26];
//         for (int i = 65; i <= 90; i++) {
//             ch[i - 65] = (char) i;
//             ch[i - 65 + 26] = (char) (i + 32);
//         }
//         Character integer[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
//         Character others[] = {'#', '=', '_', '.', '*', '/', '+', '-', ' ', '(', ')'};
//         input.addAll(Arrays.asList(ch));
//         input.addAll(Arrays.asList(integer));
//         input.addAll(Arrays.asList(others));

//         regular = addConcat(regular);
        
//         nodeStack.clear();
//         operator.clear();

//         for (int i = 0; i < regular.length(); i++){
//             if(isCharacter(regular.charAt(i))){
//                 pushToStack(Character.toString(regular.charAt(i)));
//             }else if(operator.isEmpty()){
//                 operator.push(regular.charAt(i));
//             }else if (regular.charAt(i) == '('){
//                 operator.push(regular.charAt(i));
//             }else if(regular.charAt(i) == ')'){
//                 while(operator.get(operator.size() - 1) != '('){
//                     doOperation();
//                 }
//                 operator.pop();
//             }else{
//                 while(!operator.isEmpty() && Priority(regular.charAt(i),operator.get(operator.size() - 1))){
//                     doOperation();
//                 }
//                 operator.push(regular.charAt(i));
//             }
//         }
//         while (!operator.isEmpty()){
//             doOperation();
//         }

//         Node treeCompleted = nodeStack.pop();
//         return treeCompleted;
//     }
//     private boolean Priority(char first, Character second) {
//         if (first == second) {
//             return true;
//         }
//         if (first == '*') {
//             return false;
//         }
//         if (second == '*') {
//             return true;
//         }
//         if (first == '&') {
//             return false;
//         }
//         if (second == '&') {
//             return true;
//         }
//         if (first == '|') {
//             return false;
//         }
//         return true;
//     }
//     public void doOperation(){
//         if(this.operator.size() > 0){
//             char Ch = operator.pop();
//             if (Ch == '|'){
//                 union();

//             }else if (Ch == '&'){
//                 concatenation();
//             }else if (Ch == '*'){
//                 star();
//             }else{
//                 System.out.println(">>" + Ch);
//                 System.out.println("Unkown Symbol !");
//                 System.exit(1);
//             }
//         }
//     }
//     private void star() {
//         Node node = nodeStack.pop();
//         Node root = new Node("*");
//         root.setLeftNode(node);
//         root.setRightNode(null);
//         node.setParentNode(root);

//         nodeStack.push(root);
//     }
//     private void concatenation() {
//         Node rightNode = nodeStack.pop();
//         Node leftNode = nodeStack.pop();
//         Node root = new Node("&");
//         root.setLeftNode(leftNode);
//         root.setRightNode(rightNode);
//         leftNode.setParentNode(root);
//         rightNode.setParentNode(root);

//         nodeStack.push(root);
//     }
//     private void union() {
//         Node rightNode = nodeStack.pop();
//         Node leftNode = nodeStack.pop();

//         Node root = new Node("|");

//         root.setLeftNode(leftNode);
//         root.setRightNode(rightNode);
//         leftNode.setParentNode(root);
//         rightNode.setParentNode(root);

//         nodeStack.push(root);
//     }
//     public void pushToStack(String str){
//         Node node = new LeafNode(str, ++leafId);
//         node.setLeftNode(null);
//         node.setRightNode(null);
        
//         nodeStack.push(node);
//     }
//     public String addConcat(String reg){
//         String newReg = new String("");
//         for (int i = 0; i<reg.length() - 1; i++){
//             if (isCharacter(reg.charAt(i))){
//                 newReg += reg.charAt(i);
//             }else if(reg.charAt(i) == '('){
//                 newReg += reg.charAt(i);
//             }else if ((isCharacter(reg.charAt(i))) || (reg.charAt(i) == '(' && i > 0) && isCharacter(reg.charAt(i + 1))){
//                 newReg += reg.charAt(i)+ "&";
//             }else if ((isCharacter(reg.charAt(i))) || (reg.charAt(i) == '(' && i > 0) && reg.charAt(i) == '('){
//                 newReg += reg.charAt(i)+ "&";
//             }else if (reg.charAt(i) == ')' && isCharacter(reg.charAt(i + 1))){
//                 newReg += reg.charAt(i) + "&";
//             }else if (reg.charAt(i) == '*' && isCharacter(reg.charAt(i + 1))){
//                 newReg += reg.charAt(i) + "&";
//             }else if (reg.charAt(i) == '*' && reg.charAt(i) == '('){
//                 newReg += reg.charAt(i) + "&";
//             }
//             else if (reg.charAt(i) == ')' && reg.charAt(i) == '('){
//                 newReg += reg.charAt(i) + "&";
//             }else{
//                 newReg += reg.charAt(i);
//             }
//         }

//         newReg += reg.charAt(reg.length() - 1);
//         return newReg;
//     }
//     private boolean isCharacter(char charAt) {

//         if (op.contains(charAt)) {
//             return false;
//         }
//         for (Character c : input) {
//             if ((char) c == charAt && charAt != '(' && charAt != ')') {
//                 return true;
//             }
//         }
//         return false;
//     }
//     public void printInorder(Node node) {
//         if (node == null) {
//             return;
//         }

//         /* first recur on left child */
//         printInorder(node.getLeftNode());

//         /* then print the data of node */
//         System.out.print(node.getChar() + " ");

//         /* now recur on right child */
//         printInorder(node.getRightNode());
//     }
    
//     public int getNumberOfLeafs(){
//         return leafId;
//     }
// }



import java.util.*;


class Tree {
    


    private int leafNodeID = 0;
    
    // Stacks for symbol nodes and operators
    private Stack<Node> stackNode = new Stack<>();
    private Stack<Character> operator = new Stack<Character>();

    // Set of inputs
    private Set<Character> input = new HashSet<Character>();
    private ArrayList<Character> op = new ArrayList<>();

    // Generates tree using the regular expression and returns it's root
    public Node generateTree(String regular) {

        Character[] ops = {'*', '|', '&'};
        op.addAll(Arrays.asList(ops));

        // Only inputs available
        Character ch[] = new Character[26 + 26];
        for (int i = 65; i <= 90; i++) {
            ch[i - 65] = (char) i;
            ch[i - 65 + 26] = (char) (i + 32);
        }
        Character integer[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Character others[] = {'#','\\', '=', '_', '.', '*', '/', '+', '-', ' ', '(', ')'};
        input.addAll(Arrays.asList(ch));
        input.addAll(Arrays.asList(integer));
        input.addAll(Arrays.asList(others));

        regular = AddConcat(regular);
        stackNode.clear();
        operator.clear();

        boolean isSymbol = false;

        for (int i = 0; i < regular.length(); i++) {

            if (regular.charAt(i) == '\\') {
                isSymbol = true;
                continue;
            }
            if (isSymbol || isInputCharacter(regular.charAt(i))) {
                if (isSymbol) {
                    pushStack("\\"+Character.toString(regular.charAt(i)));
                }
                else{
                    pushStack(Character.toString(regular.charAt(i)));
                }
                isSymbol = false;
            } else if (operator.isEmpty()) {
                operator.push(regular.charAt(i));

            } else if (regular.charAt(i) == '(') {
                operator.push(regular.charAt(i));

            } else if (regular.charAt(i) == ')') {
                while (operator.get(operator.size() - 1) != '(') {
                    doOperation();
                }

                // Pop the '(' left parenthesis
                operator.pop();

            } else {
                while (!operator.isEmpty()
                        && Priority(regular.charAt(i), operator.get(operator.size() - 1))) {
                    doOperation();
                }
                operator.push(regular.charAt(i));
            }
        }

        while (!operator.isEmpty()) {
            doOperation();
        }

        Node completeTree = stackNode.pop();
        return completeTree;
    }

    private boolean Priority(char first, Character second) {
        if (first == second) {
            return true;
        }
        if (first == '*') {
            return false;
        }
        if (second == '*') {
            return true;
        }
        if (first == '&') {
            return false;
        }
        if (second == '&') {
            return true;
        }
        if (first == '|') {
            return false;
        }
        return true;
    }

    private void doOperation() {
        if (this.operator.size() > 0) {
            char charAt = operator.pop();

            switch (charAt) {
                case ('|'):
                    union();
                    break;

                case ('&'):
                    concatenation();
                    break;

                case ('*'):
                    star();
                    break;

                default:
                    System.out.println(">>" + charAt);
                    System.out.println("Unkown Symbol !");
                    System.exit(1);
                    break;
            }
        }
    }

    private void star() {
        Node node = stackNode.pop();
        Node root = new Node("*");
        root.setLeftNode(node);
        root.setRightNode(null);
        node.setParentNode(root);

        stackNode.push(root);
    }

    private void concatenation() {
        Node node2 = stackNode.pop();
        Node node1 = stackNode.pop();

        Node root = new Node("&");
        root.setLeftNode(node1);
        root.setRightNode(node2);
        node1.setParentNode(root);
        node2.setParentNode(root);

        // Put node back to stackNode
        stackNode.push(root);
    }

    private void union() {
        Node node2 = stackNode.pop();
        Node node1 = stackNode.pop();

        Node root = new Node("|");
        root.setLeftNode(node1);
        root.setRightNode(node2);
        node1.setParentNode(root);
        node2.setParentNode(root);

        // Put Node back to stack
        stackNode.push(root);
    }

    private void pushStack(String symbol) {
        Node node = new LeafNode(symbol, ++leafNodeID);
        node.setLeftNode(null);
        node.setRightNode(null);

        stackNode.push(node);
    }


    private String AddConcat(String regular) {
        String newRegular = new String("");

        for (int i = 0; i < regular.length() - 1; i++) {

            if (regular.charAt(i) == '\\' && isInputCharacter(regular.charAt(i + 1))) {
                newRegular += regular.charAt(i);
            } else if (regular.charAt(i) == '\\' && regular.charAt(i + 1) == '(') {
                newRegular += regular.charAt(i);
            } else if ((isInputCharacter(regular.charAt(i)) || (regular.charAt(i) == '(' && i > 0 && regular.charAt(i - 1) == '\\')) && isInputCharacter(regular.charAt(i + 1))) {
                newRegular += regular.charAt(i) + "&";

            } else if ((isInputCharacter(regular.charAt(i)) || (regular.charAt(i) == '(' && i > 0 && regular.charAt(i - 1) == '\\')) && regular.charAt(i + 1) == '(') {
                newRegular += regular.charAt(i) + "&";

            } else if (regular.charAt(i) == ')' && isInputCharacter(regular.charAt(i + 1))) {
                newRegular += regular.charAt(i) + "&";

            } else if (regular.charAt(i) == '*' && isInputCharacter(regular.charAt(i + 1))) {
                newRegular += regular.charAt(i) + "&";

            } else if (regular.charAt(i) == '*' && regular.charAt(i + 1) == '(') {
                newRegular += regular.charAt(i) + "&";

            } else if (regular.charAt(i) == ')' && regular.charAt(i + 1) == '(') {
                newRegular += regular.charAt(i) + "&";

            } else {
                newRegular += regular.charAt(i);
            }

        }
        newRegular += regular.charAt(regular.length() - 1);
        return newRegular;
    }

    // Return true if is part of the automata Language else is false
    private boolean isInputCharacter(char charAt) {

        if (op.contains(charAt)) {
            return false;
        }
        for (Character c : input) {
            if ((char) c == charAt && charAt != '(' && charAt != ')') {
                return true;
            }
        }
        return false;
    }
    
    /* This method is here just to test buildTree() */
    public void printInorder(Node node) {
        if (node == null) {
            return;
        }

        /* first recur on left child */
        printInorder(node.getLeftNode());

        /* then print the data of node */
        System.out.print(node.getChar() + " ");

        /* now recur on right child */
        printInorder(node.getRightNode());
    }
    
    public int getNumberOfLeafs(){
        return leafNodeID;
    }

}




