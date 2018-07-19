package Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Each year, the government releases a list of the 10,000 most common baby
 * names and their frequencies (the number of babies with that name). The only
 * problem with this is that some names have multiple spellings. For example,
 * “John” and “Jon” are essentially the same name but would be listed separately
 * in the list. Given two lists, one of names/frequencies and the other of pairs
 * of equivalent names, write an algorithm to print a new list of the true
 * frequency of each name. Note that if John and Jon are synonyms, and Jon and
 * Johnny are synonyms, then John and Johnny are synonyms. (it is both
 * transitive and symmetric.) In the final list any name can be used as the real
 * name. Ex: Input: Names: John (15), Jon(12), Chris(13), Kris(4), Christopher
 * (19) Synonyms: (Jon,John), (John, Johnny), (Chris, Kris), (Chris,
 * Christopher) Output: John (27), Kris(36)
 */
public class BabyNames {
	public Map<String, Integer> trulyMostPopular(Map<String, Integer> names, String[][] synonyms) {
		Graph graph = constructGraph(names);
		connectEdges(graph, synonyms);
		Map<String, Integer> rootNames = getTrueFrequencies(graph);
		return rootNames;
	}

	private Graph constructGraph(Map<String, Integer> names) {
		Graph graph = new Graph();
		for (Map.Entry<String, Integer> entry : names.entrySet()) {
			graph.createNode(entry.getKey(), entry.getValue());
		}
		return graph;
	}

	private void connectEdges(Graph graph, String[][] synonyms) {
		for (String[] entry : synonyms) {
			graph.addEdge(entry[0], entry[1]);
		}
	}

	private Map<String, Integer> getTrueFrequencies(Graph graph) {
		Map<String, Integer> rootNames = new HashMap<>();
		for (GraphNode node : graph.getNodes()) {
			if (!node.isVisited()) {
				int frequency = getComponentFrequency(node);
				rootNames.put(node.getName(), frequency);
			}
		}
		return rootNames;
	}

	private int getComponentFrequency(GraphNode node) {
		if (node.isVisited())
			return 0;

		node.setVisited(true);
		int total = node.getFrequency();

		for (GraphNode neighborNode : node.getNeighbors()) {
			total += getComponentFrequency(neighborNode);
		}
		return total;
	}

	public static void main(String[] args) {
		Map<String, Integer> names = new HashMap<>();

		names.put("John", 3);
		names.put("Jonathan", 4);
		names.put("Johnny", 5);
		names.put("Chris", 1);
		names.put("Kris", 3);
		names.put("Brian", 2);
		names.put("Bryan", 4);
		names.put("Carleton", 4);

		String[][] synonyms = { { "John", "Jonathan" }, { "Jonathan", "Johnny" }, { "Chris", "Kris" },
				{ "Brian", "Bryan" } };
		BabyNames babyNames=new BabyNames();
		Map<String, Integer> rootNames = babyNames.trulyMostPopular(names, synonyms);
		for (Map.Entry<String, Integer> entry : rootNames.entrySet()) {
			String name = entry.getKey();
			int frequency = entry.getValue();
			System.out.println(name + ": " + frequency);
		}
	}

}

class Graph {
	private List<GraphNode> nodes;
	private Map<String, GraphNode> map;

	public Graph() {
		nodes = new ArrayList<>();
		map = new HashMap<>();
	}

	public boolean hasNode(String nodeName) {
		return map.containsKey(nodeName);
	}

	public GraphNode createNode(String name, int freq) {
		GraphNode node = map.get(name);
		if (node == null) {
			node = new GraphNode(name, freq);
			nodes.add(node);
			map.put(name, node);
		}
		return node;
	}

	public GraphNode getNode(String name) {
		return map.get(name);
	}

	public List<GraphNode> getNodes() {
		return nodes;
	}

	public void addEdge(String name, String edgeName) {
		GraphNode node = getNode(name);
		GraphNode edgeNode = getNode(edgeName);
		if (node != null && edgeNode != null) {
			node.addNeighbor(edgeNode);
			edgeNode.addNeighbor(node);
		}
	}
}

class GraphNode {
	private List<GraphNode> neighbors;
	private Map<String, GraphNode> map;
	private String name;
	private int frequency;
	private boolean visited;

	public GraphNode(String name, int frequency) {
		this.name = name;
		this.frequency = frequency;
		neighbors = new ArrayList<>();
		map = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public int getFrequency() {
		return frequency;
	}

	public boolean addNeighbor(GraphNode node) {
		if (!map.containsKey(node.getName())) {
			neighbors.add(node);
			map.put(node.getName(), node);
			return true;
		} else {
			return false;
		}

	}

	public List<GraphNode> getNeighbors() {
		return neighbors;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean v) {
		visited = v;
	}

}
