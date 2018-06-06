package trees_graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
* A binary search tree was created by traversing through an array from left to right and inserting each element. 
* Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
*/
public class BSTSequences{

	public List<LinkedList<Integer>> generateSequences(BTreeNode root){
		List<LinkedList<Integer>> result=new ArrayList<>();
		if(root==null){
			result.add(new LinkedList<>());
			return result; 
		}
		
		LinkedList<Integer> prefix=new LinkedList<>();
		prefix.add(root.val);
		
		List<LinkedList<Integer>> leftSequences=generateSequences(root.left);
		List<LinkedList<Integer>> rightSequences=generateSequences(root.right);
		
		for(LinkedList<Integer> left:leftSequences){
			for(LinkedList<Integer> right:rightSequences){
				List<LinkedList<Integer>> weaved=new ArrayList<>();
				weaveLists(left,right,weaved,prefix);
				result.addAll(weaved);
			}
		}
		return result;
	}
	
	public void weaveLists(LinkedList<Integer> left,LinkedList<Integer> right,List<LinkedList<Integer>> results,LinkedList<Integer> prefix){
	
		if( left.isEmpty() || right.isEmpty()){
			LinkedList<Integer> clonePrefix = (LinkedList<Integer>) prefix.clone();
			clonePrefix.addAll(left);
			clonePrefix.addAll(right);
			results.add(clonePrefix);
			return;
		}
		
		Integer leftHead=left.removeFirst();
		prefix.addLast(leftHead);
		weaveLists(left,right,results,prefix);
		prefix.removeLast();
		left.addFirst(leftHead);
		
		Integer rightHead=right.removeFirst();
		prefix.addLast(rightHead);
		weaveLists(left,right,results,prefix);
		prefix.removeLast();
		right.addFirst(rightHead);
		
	}
	
	public static void main(String[] args) {
		//int[] array = {100, 50, 20, 75, 150, 120, 170};
		int[] array = {50,20,60,10,25,70,5,15,65,80};
		Arrays.sort(array);
		BTreeNode node=MinimalTree.createMinimalBST(array);
		BSTSequences bstSequences=new BSTSequences();
		List<LinkedList<Integer>> allSeq = bstSequences.generateSequences(node);
		for (LinkedList<Integer> list : allSeq) {
			System.out.println(list);
		}
		System.out.println(allSeq.size());
	}

}
