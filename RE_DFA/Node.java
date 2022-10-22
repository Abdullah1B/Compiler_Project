
import java.util.HashSet;
import java.util.Set;

public class Node {

    private String Char;
    private Node parentNode;
    private Node leftNode;
    private Node rightNode;

    private Set<Integer> firstPos;
    private Set<Integer> lastPos;
    private boolean nullable;

    public Node(String Ch){
        this.Char = Ch;
        parentNode = null;
        leftNode = null;
        rightNode = null;

        firstPos = new HashSet<>();
        lastPos = new HashSet<>();
        nullable = false;

    }

    public String getChar() {
        return Char;
    }

    public void setChar(String c) {
        this.Char = c;
    }
    public Node getParentNode() {
        return parentNode;
    }
    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Node getLeftNode() {
        return this.leftNode;
    }
    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }
    public Node getRightNode() {
        return rightNode;
    }
    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public void addToFirstPos(int number){
        firstPos.add(number);
    }


    

    public void addSetToFirstPos(Set set){
        firstPos.addAll(set);
    }
    public void addToLastPos(int number){
        lastPos.add(number);
    }

    public void addSetToLastPos(Set set){
        lastPos.addAll(set);
    }

    public Set<Integer> getFirstPos(){
        return firstPos;
    }

    public Set<Integer> getLastPos(){
        return lastPos;
    }

    public boolean isNullable(){
        return nullable;
    }
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    
}
