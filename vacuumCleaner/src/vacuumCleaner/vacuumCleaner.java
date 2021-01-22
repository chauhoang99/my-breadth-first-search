package vacuumCleaner;
import java.util.LinkedList;

public class vacuumCleaner {
	
	public static State[] action(Node node) {
		State state = node.state;
		State left = null;
		State right = null;
		State suck = null;

		if (state.dirtLeft & state.dirtRight & state.vacuumPosition == "left") {
			// state 1
			left = new State(true, true, "left");
			right = new State(true, true, "right");
			suck = new State(false, true, "left");
		} else if (state.dirtLeft & state.dirtRight & state.vacuumPosition == "right") {
			// state 2
			left = new State(true, true, "left");
			right = new State(true, true, "right");
			suck = new State(true, false, "right");
		} else if (!state.dirtLeft & state.dirtRight & state.vacuumPosition == "left") {
			// state 3
			left = new State(false, true, "left");
			right = new State(false, true, "right");
			suck = new State(false, true, "left");
		} else if (!state.dirtLeft & state.dirtRight & state.vacuumPosition == "right") {
			// state 4
			left = new State(false, true, "left");
			right = new State(false, true, "right");
			suck = new State(false, false, "right");
			
		} else if (state.dirtLeft & !state.dirtRight & state.vacuumPosition == "left") {
			// state 5
			left = new State(true, false, "left");
			right = new State(true, false, "right");
			suck = new State(false, false, "left");
		} else if (state.dirtLeft & !state.dirtRight & state.vacuumPosition == "right") {
			// state 6
			left = new State(true, false, "left");
			right = new State(true, false, "right");
			suck = new State(true, false, "right");
		} else if (!state.dirtLeft & !state.dirtRight & state.vacuumPosition == "left") {
			// state 7
			left = new State(false, false, "left");
			right = new State(false, false, "right");
			suck = new State(false, false, "left");
		} else if (!state.dirtLeft & !state.dirtRight & state.vacuumPosition == "right") {
			// state 8
			left = new State(false, false, "left");
			right = new State(false, false, "right");
			suck = new State(false, false, "right");
		}
		
		State[] action = {left, right, suck};
		return action;
	}
	
	public static Node childNode(State state, Node parent, String actionTaken) {
		Node node = new Node(state, parent, actionTaken);
		return node;
	}
	
	public static boolean goalTest(Node node) {
		if (node.state.dirtLeft == false & node.state.dirtRight == false) {
			System.out.println("cleaned!!");
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isExplored(LinkedList<State> explored, State state) {
		for (State x: explored) {
			if (x.dirtLeft == state.dirtLeft & x.dirtRight == state.dirtRight & x.vacuumPosition == state.vacuumPosition) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFrontier(LinkedList<Node> frontier, Node node) {
		for (Node x: frontier) {
			if (x.state.dirtLeft == node.state.dirtLeft & x.state.dirtRight == node.state.dirtRight &
					x.state.vacuumPosition == node.state.vacuumPosition) {
				return true;
			}
		}
		return false;
	}
	
	public static void result(Node node) {
		if (node.parent != null) {
			result(node.parent);
		}
		System.out.println(node.action);
		System.out.println("Left: " + node.state.dirtLeft);
		System.out.println("Right: " + node.state.dirtRight);
		System.out.println("In: " + node.state.vacuumPosition);
		System.out.println("==============================");		
	}

	public static Node breadthFirstSearch(State initState) {
		LinkedList<Node> frontier = new LinkedList<Node>();
		LinkedList<State> explored = new LinkedList<State>();

		Node node = new Node(initState);
		boolean isSolved = goalTest(node);
		if (isSolved) {
			explored.add(initState);
			return node;
		}
		frontier.add(node);
		
		while (true) {			
			if (frontier.isEmpty()) {
				return null;
			}
			
			Node currentNode = frontier.removeFirst();
			
			explored.add(currentNode.state);
			
			State[] actions = action(currentNode);
			for (int i = 0; i <= 2; i++) {
				String actionTaken;
				if (i== 0) {
					actionTaken = "go left";
				} else if (i == 1) {
					actionTaken = "go right";
				} else if (i == 2) {
					actionTaken = "suck!";
				} else {
					actionTaken = "an unexpected move";
				}
				Node childNode = childNode(actions[i], currentNode, actionTaken);

				if (!isExplored(explored, childNode.state) | !isFrontier(frontier, childNode)) {
					isSolved = goalTest(childNode);
					if (isSolved) {
						explored.add(childNode.state);
						return childNode;
					} else {
						frontier.add(childNode);
					}
				}
			}	
		}
	}
	
	public static void main(String[] args) {
//		State initialState = new State(true, true, "left");
//		State initialState = new State(true, true, "right");
//		State initialState = new State(false, true, "left");
//		State initialState = new State(false, true, "right");
//		State initialState = new State(true, false, "left");
//		State initialState = new State(true, false, "right");
//		State initialState = new State(false, false, "left");
		State initialState = new State(false, false, "right");

		Node res;
		res = breadthFirstSearch(initialState);
		result(res);
	}

}
