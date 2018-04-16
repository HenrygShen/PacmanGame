package group23.pacman.model;

import java.util.ArrayList;

public class Node {

	/* The nodes which this node is connected to */
	private ArrayList<Node> edges = new ArrayList<Node>();

	private int x;
	private int y;
	 
	 
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void addEdges(ArrayList<Node> nodes, Board board) {
		
		/* Loops through all the nodes and checks if it has a clear path to this node */
		for (int i =0; i < nodes.size(); i++) {
			/* Ignores if the nodes are the same */
			if (!(nodes.get(i).y == y && nodes.get(i).x == x)) {
				if (nodes.get(i).y == y) {
					int leftMostNode = Math.min(nodes.get(i).x, x) + 30;
					int rightMostNode = Math.max(nodes.get(i).x, x);
					boolean edge = true;
					/* Increment by 30 from the node on the left towards the right
					 * If the leftMostNode reaches the rightMostNode, then they are connected */
					while (leftMostNode < rightMostNode) {
						/* Not connected if there is a wall in the way, or a different node in the way */
						if (!board.isValidPos(leftMostNode, y) || board.isNode(leftMostNode, y)) {
							edge = false;
							break;
						}
						leftMostNode += 30;
					}
					if (edge) {
						edges.add(nodes.get(i));
					}
				} 
				else if (nodes.get(i).x == x) {
					int topMostNode = Math.min(nodes.get(i).y, y) + 30;
					int bottomMostNode = Math.max(nodes.get(i).y, y);
					boolean edge = true;
					while (topMostNode < bottomMostNode) {
						if (!board.isValidPos(x, topMostNode) || board.isNode(x, topMostNode)) {
							edge = false;
							break;
						}
						topMostNode += 30;
					}
					if (edge) {
						edges.add(nodes.get(i));
					}
				}
			}
		}
	}
	
	/* PUBLIC GETTERS */
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public ArrayList<Node> getEdges(){
		return edges;
	}
	
}