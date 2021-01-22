package vacuumCleaner;

public class State {
	boolean dirtLeft;
	boolean dirtRight;
	String vacuumPosition;
	
	public State (boolean inDirtLeft, boolean inDirtRight, String inVacuumPosition) {
		dirtLeft = inDirtLeft;
		dirtRight = inDirtRight;
		vacuumPosition = inVacuumPosition;
	}
}
