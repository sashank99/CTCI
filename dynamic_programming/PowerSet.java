package dynamic_programming;

import java.util.ArrayList;
import java.util.List;

/**
* Write a method to return all sub sets of a Set
*/
public class PowerSet{

	
	public static List<ArrayList<Integer>> createSubSets(List<Integer> set){
		List<ArrayList<Integer>> subSetList=new ArrayList<>();
		
		subSetList.add(new ArrayList<Integer>());
		for(int i=0;i<set.size();i++){
			List<ArrayList<Integer>> newSets=new ArrayList<>();
			for(int j=0;j<subSetList.size();j++){
				ArrayList<Integer> cloneSet=(ArrayList<Integer>)subSetList.get(j).clone();
				cloneSet.add(set.get(i));
				newSets.add(cloneSet);
			}
			if(newSets!=null){
				subSetList.addAll(newSets);
			}
			
		}
		
		return subSetList;
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		List<ArrayList<Integer>> subsets = createSubSets(list);
		System.out.println(subsets.toString());	
		System.out.println("size:::"+subsets.size());	
	}
}
