import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
public class SyntaxTree {
    private String regular;
    private Tree tree;
    private Node root;
    private int numOfLeaf;
    private Set<Integer> followPos[];


    public SyntaxTree(String regular){
        this.regular = regular;
        tree = new Tree();
        // root = tree.createTree(regular);
        root = tree.generateTree(regular);

        numOfLeaf = tree.getNumberOfLeafs();
        followPos = new Set[numOfLeaf];
        for (int i = 0; i < numOfLeaf; i++){
            followPos[i] = new HashSet<>();
        }
        
        //bt.printInorder(root);
        generateNullable(root);
        generateFirstposLastPos(root);
        generateFollowPos(root);
    }

    // private void generateFollowPos(Node node) {
    //     if (node == null){
    //         return;
    //     }

    //     Node left = node.getLeftNode();
    //     Node right = node.getRightNode();

    //     if (node.getChar() == "&"){
    //         Object lastPosLeft[] = left.getLastPos().toArray();
    //         Set<Integer> firstPosRight = right.getFirstPos();
    //         for(int i = 0; i <lastPosLeft.length; i++){
    //             followPos[(Integer) lastPosLeft[i] - 1].addAll(firstPosRight);
               
    //         }
    //     }else if(node.getChar() == "*"){
    //         Object LastPos [] = node.getLastPos().toArray();
    //         Set<Integer> FirstPos = node.getFirstPos();
    //         for(int i = 0; i < LastPos.length; i++){
    //             followPos[(Integer) LastPos[i] - 1].addAll(FirstPos);
    //             // MapFollowPos.put((Integer) LastPos[i],FirstPos);


    //         }
    //     }
    //     generateFollowPos(node.getLeftNode());
    //     generateFollowPos(node.getRightNode());

    // }
    private void generateFollowPos(Node node) {
        if (node == null) {
            return;
        }
        Node left = node.getLeftNode();
        Node right = node.getRightNode();
        switch (node.getChar()) {
            case "&":
                Object lastpos_c1[] = left.getLastPos().toArray();
                Set<Integer> firstpos_c2 = right.getFirstPos();
                for (int i = 0; i < lastpos_c1.length; i++) {
                    followPos[(Integer) lastpos_c1[i] - 1].addAll(firstpos_c2);
                }
                break;
            case "*":
                Object lastpos_n[] = node.getLastPos().toArray();
                Set<Integer> firstpos_n = node.getFirstPos();
                for (int i = 0; i < lastpos_n.length; i++) {
                    followPos[(Integer) lastpos_n[i] - 1].addAll(firstpos_n);
                }
                break;
        }
        generateFollowPos(node.getLeftNode());
        generateFollowPos(node.getRightNode());

    }
    // private void generateFirstposLastPos(Node node) {
    //     if (node == null){
    //         return;
    //     }
    //     if(node instanceof LeafNode){
    //         LeafNode node2 = (LeafNode) node;
    //         node.addToFirstPos(node2.getNum());
    //         node.addToLastPos(node2.getNum());

    //     }else{
    //         Node left = node.getLeftNode();
    //         Node right = node.getRightNode();
    //         generateFirstposLastPos(left);
    //         generateFirstposLastPos(right);
    //         if (node.getChar() == "|"){
    //             node.addSetToFirstPos(left.getFirstPos());
    //             node.addSetToFirstPos(right.getFirstPos());
    //             node.addSetToLastPos(left.getLastPos());        
    //             node.addSetToLastPos(right.getLastPos()); 
       
    //         }else if (node.getChar() == "&"){
    //             if(left.isNullable()){
    //                 node.addSetToFirstPos(left.getFirstPos());
    //                 node.addSetToFirstPos(right.getFirstPos());

    //             }else{
    //                 node.addSetToFirstPos(left.getFirstPos());

    //             }
    //             if(right.isNullable()){
    //                 node.addSetToLastPos(left.getLastPos());        
    //                 node.addSetToLastPos(right.getLastPos()); 

    //             }else{
    //                 node.addSetToLastPos(right.getLastPos()); 

    //             }

    //         }else if(node.getChar() == "*"){
    //             node.addSetToFirstPos(left.getFirstPos());
    //             node.addSetToLastPos(left.getLastPos());
      

    //         }
    //     }
    // }
    private void generateFirstposLastPos(Node node) {
        if (node == null) {
            return;
        }
        if (node instanceof LeafNode) {
            LeafNode lnode = (LeafNode) node;
            node.addToFirstPos(lnode.getNum());
            node.addToLastPos(lnode.getNum());
        } else {
            Node left = node.getLeftNode();
            Node right = node.getRightNode();
            generateFirstposLastPos(left);
            generateFirstposLastPos(right);
            switch (node.getChar()) {
                case "|":
                    node.addSetToFirstPos(left.getFirstPos());
                    node.addSetToFirstPos(right.getFirstPos());
                    //
                    node.addSetToLastPos(left.getLastPos());
                    node.addSetToLastPos(right.getLastPos());
                    break;
                case "&":
                    if (left.isNullable()) {
                        node.addSetToFirstPos(left.getFirstPos());
                        node.addSetToFirstPos(right.getFirstPos());
                    } else {
                        node.addSetToFirstPos(left.getFirstPos());
                    }
                    //
                    if (right.isNullable()) {
                        node.addSetToLastPos(left.getLastPos());
                        node.addSetToLastPos(right.getLastPos());
                    } else {
                        node.addSetToLastPos(right.getLastPos());
                    }
                    break;
                case "*":
                    node.addSetToFirstPos(left.getFirstPos());
                    node.addSetToLastPos(left.getLastPos());
                    break;
            }
        }
    }
    private void generateNullable(Node node2) {
        if (node2 == null){
            return;
        }
        if(!(node2 instanceof LeafNode)){
            Node left = node2.getLeftNode();
            Node right = node2.getRightNode();
            
            generateNullable(left);
            generateNullable(right);
            if (node2.getChar() == "|"){
                node2.setNullable(left.isNullable() | right.isNullable());
            }else if (node2.getChar() == "&"){
                node2.setNullable(left.isNullable() & right.isNullable());
            }else if (node2.getChar() == "*"){
                node2.setNullable(true);
            }
            
        }
    }
    public void show(Node node) {
        if (node == null) {
            return;
        }
        show(node.getLeftNode());
        Object s[] = node.getLastPos().toArray();
        System.out.println(s[0]);
        show(node.getRightNode());
    }

    public void showFollowPos() {
        for (int i = 0; i < followPos.length; i++) {
            System.out.println(followPos[i]);
            //  
        }
    }

    public Set<Integer>[] getFollowPos() {
        return followPos;
    }

    public Node getRoot() {
        return this.root;
    }



}
