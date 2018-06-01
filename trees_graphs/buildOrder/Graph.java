package trees_graphs.buildOrder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph{
	private Set<Project> nodes=new HashSet<>();
	private Map<String,Project> map=new HashMap<>();
	public void createNode(String name){
		Project project=new Project(name);
		nodes.add(project);
		map.put(name,project);
	}
	
	public Project getNode(String name){
		return map.get(name);
	}
	
	public Set<Project> getNodes(){
		return nodes;
	}
}
