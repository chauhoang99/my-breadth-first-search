package vacuumCleaner;

public class Node {
	State state;
	Node parent;
	
	// seems like this is to store action of the parent
	String action = "start";
	
	public Node (State inState) {
		state = inState;
	}
	
	public Node (State inState, Node inParent, String actionTaken) {
		state = inState;
		parent = inParent;
		action = actionTaken;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState (State inState) {
		state = inState;
	}
}
