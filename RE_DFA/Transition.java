public class Transition {
    private State state;
    private String a;
    private State target;

    public Transition(State state,String a,State target){
        this.state = state;
        this.a = a;
        this.target = target;

    }

    @Override
    public String toString() {
        return "("+state.getName()+", "+a+") "+" --> " + target.getName();
    }

}
