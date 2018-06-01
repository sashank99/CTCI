package trees_graphs.buildOrder;

import java.util.Iterator;
import java.util.Set;

/**
* You are given a list of projects and a list of dependencies (which is a list of pairs of projects, where the second project is dependent on first project).
* All of a project's dependencies must be built before the project is. Find a build order that will allow the project to be built.
* If there is no build order, return an error.
*/
public class BuildOrder{

	public static String[] build(String[] projects,String[][] dependencies){
		Graph graph=new Graph();
		for(String project:projects){
			graph.createNode(project);
		}
		for(String[] dependency: dependencies){
			graph.getNode(dependency[0]).addDependent(graph.getNode(dependency[1]));
		}		
		
		return buildOder(graph.getNodes());
		
	}
	
	public static String[] buildOder(Set<Project> projects){
		int initialSize=projects.size();
		String[] order=new String[initialSize];
		int index=0;
		while(!projects.isEmpty()){
			for (Iterator<Project> iterattor = projects.iterator(); iterattor.hasNext();) {
				Project project = iterattor.next();
				if(project.getDependenciesCount()==0){
					order[index]=project.getName();
					project.removeAllDependents();
					iterattor.remove();
					index++;
				}
			}
			
			if(initialSize==projects.size()){
				return null;
			}
			initialSize=projects.size();
		}
		
		return order;
	}
	
	public static void main(String[] args) {
		String[] projects = {"a", "b", "c", "d", "e", "f"};
		String[][] dependencies = {
				{"a","d"},
				{"f","b"},
				{"b","d"},
				{"f","a"},
				{"d","c"}
		};
		//String[] projects = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		/*String[][] dependencies = {
				{"a", "b"},
				{"b", "c"},
				{"a", "c"},
				{"a", "c"},
				{"d", "e"},
				{"b", "d"},
				{"e", "f"},
				{"a", "f"},
				{"h", "i"},
				{"h", "j"},
				{"i", "j"},
				{"g", "j"}};*/
		String[] buildOrder = build(projects, dependencies);
		if (buildOrder == null) {
			System.out.println("Circular Dependency.");
		} else {
			for (String s : buildOrder) {
				System.out.println(s);
			}
		}
	}
}

