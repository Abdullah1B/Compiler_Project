import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
public class State {
    
    private int ID;
    private String name;
    private Set<Integer> FollowSet;
    private HashMap<String,State> move;

    private boolean IsAccepted;
    private boolean IsMarked;

    public State(int ID){
        this.name = null;
        this.ID = ID;
        move = new HashMap<>();
        FollowSet = new HashSet<>();
        IsAccepted = false;
        IsMarked = false;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getID() {
        return ID;
    }
    public void addMove(String Ch,State s){
        move.put(Ch,s);
    }
    
    public void addToFollowSet(int number){
        FollowSet.add(number);
    }

    public void addSetToFollowSet(Set<Integer> number){
        FollowSet.addAll(number);
    }

    public void setIsMarked(boolean isMarked) {
        IsMarked = isMarked;
    }
    public boolean getIsMarked(){
        return IsMarked;
    }

    public Set<Integer> getFollowSet() {
        return FollowSet;
    }    

    public boolean IsEmpty(){
        return FollowSet.size() == 0;
    }
    public void print(){
        System.out.print(FollowSet);
    }

    public void setIsAccepted(boolean isAccepted) {
        IsAccepted = isAccepted;
    }

    public boolean getIsAccepted(){
        return IsAccepted;
    }

    public State getNextState(String str){
        return this.move.get(str);
    }

    public List getArrayOfFollowSet(){
 
        List<Integer> list = new ArrayList<Integer>(FollowSet);
        return list;
    }

    public HashMap<String, State> getMove() {
        return move;
    }
}
