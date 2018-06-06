package trees_graphs;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Given a directed graph, design an algorithm to find out whether there is a
 * route between two nodes.
 */
public class TwoNodesRoute {

	public static boolean searchRoute(Node start, Node end) {
		if (start == end)
			return true;

		Queue<Node> queue = new LinkedList<>();
		start.state = State.Visiting;
		queue.add(start);
		Node node;
		while (!queue.isEmpty()) {
			node = queue.remove();
			for (Node children : node.adjacent) {
				if (children.state == State.Unvisited) {
					if (children == end) {
						return true;
					} else {
						queue.add(children);
						children.state = State.Visiting;
					}
				}
			}
			node.state = State.Visited;
		}
		return false;

	}

	public static Graph createNewGraph() {
		Graph g = new Graph();
		Node[] temp = new Node[6];

		temp[0] = new Node("a", 3);
		temp[1] = new Node("b", 0);
		temp[2] = new Node("c", 0);
		temp[3] = new Node("d", 1);
		temp[4] = new Node("e", 1);
		temp[5] = new Node("f", 0);

		temp[0].adjacent = new Node[] { temp[1], temp[2], temp[3] };
		temp[3].adjacent = new Node[] { temp[4] };
		temp[4].adjacent = new Node[] { temp[5] };
		g.nodes = temp;
		return g;
	}

	public static void main(String[] args) {
		Graph g = createNewGraph();
		Node[] node = g.nodes;
		Node start = node[0];
		Node end = node[3];
		// Node end=new Node("g",6);
		System.out.println(searchRoute(start, end));
	}

}
