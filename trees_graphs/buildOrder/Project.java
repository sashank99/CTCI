package trees_graphs.buildOrder;

import java.util.HashSet;
import java.util.Set;

public class Project{
	private String name;
	private Set<Project> dependents=new HashSet<>();
	private int dependencies;
	public Project(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void addDependent(Project project){
	
		if(!dependents.contains(project)){
			dependents.add(project);
			project.incrementDependencies();
		}
	}
	
	public void removeDependent(Project project){
		if(dependents.contains(project)){
			dependents.remove(project);
			project.decrementDependencies();
		}
	}
	
	public void removeAllDependents(){
		for(Project project:dependents){
			project.decrementDependencies();
		}
		dependents.clear();
	}
	
	public void incrementDependencies(){
		dependencies++;
	}
	
	public void decrementDependencies(){
		dependencies--;
	}
	
	public int getDependenciesCount(){
		return dependencies;
	}
	

}
