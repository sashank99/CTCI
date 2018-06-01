package trees_graphs;

public class Graph{
	public Node[] nodes;
}

class Node{
	public int val;
	public String name;
	public Node[] adjacent;
	public State state=State.Unvisited;
	
	public Node(String name,int val){
		this.name=name;
		this.val=val;
	}
}

enum State{
	Visited, Visiting, Unvisited;
}
