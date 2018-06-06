package trees_graphs;

import java.util.HashMap;
import java.util.Map;

/**
* You are given a binary tree in which each node contains an integer value (which might be positive or negative). 
* Design an algorithm to count the number of paths that sum to a given value. 
* The path does not need to start or end at the root or a leaf, but it must go downwards (travelling only from parent nodes to child nodes)
*/
public class PathsWithSum{

	/**
	* Brute force approach takes O(N log n) time.
	
	public int countPathsWithSum(BBTreeNode root, int targetSum){
		if(root== null) return 0;
		int rootSum=countPathsWithSumFromNode(root,targetSum,0);
		int leftSum=countPathsWithSum(root.left,targetSum);
		int rightSum=countPathsWithSum(root.left,targetSum);
		
		return rootSum+leftSum+rightSum;
	}
	
	public int countPathsWithSumFromNode(BBTreeNode node, int targetSum, int currentSum){
		if(node==null) return 0;
		currentSum+=node.val;
		totalPaths=0;
		if(currentSum==targetSum){
			totalPaths++;
		}
		totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
		totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);
		return totalPaths;
	}
	*/
	
	/**
	* O(N) Solution
	*/
	public static int countPathsWithSum(BTreeNode root, int targetSum){
		return countPathsWithSum(root,targetSum,0,new HashMap<Integer,Integer>());
	}
	
	public static int countPathsWithSum(BTreeNode node, int targetSum, int runningSum,Map<Integer,Integer> hash){
		if(node==null) return 0;
		
		runningSum +=node.val;
		int totalPaths= hash.getOrDefault(runningSum-targetSum,0);
		if(runningSum==targetSum){
			totalPaths++;
		}
		incrementHash(hash,runningSum,1);
		totalPaths +=countPathsWithSum(node.left,targetSum,runningSum,hash);
		totalPaths +=countPathsWithSum(node.right,targetSum,runningSum,hash);
		incrementHash(hash,runningSum,-1);
		return totalPaths;
	}
	
	public static void incrementHash(Map<Integer,Integer> hash,int key,int delta){
		int countVal= hash.getOrDefault(key,0)+delta;
	 	if(countVal==0){
	 		hash.remove(key);
	 	}else{
	 		hash.put(key,countVal);
	 	}
	}
	
	public static void main(String[] args) {
		/*
		BTreeNode root = new BTreeNode(5);
		root.left = new BTreeNode(3);		
		root.right = new BTreeNode(1);
		root.left.left = new BTreeNode(-8);
		root.left.right = new BTreeNode(8);
		root.right.left = new BTreeNode(2);
		root.right.right = new BTreeNode(6);	
		root.right.left.left = new BTreeNode(0);	
		System.out.println(countPathsWithSum(root, 0));
		*/
		
		BTreeNode root = new BTreeNode(-7);
		root.left = new BTreeNode(-7);
		root.left.right = new BTreeNode(1);
		root.left.right.left = new BTreeNode(2);
		root.right = new BTreeNode(7);
		root.right.left = new BTreeNode(3);
		root.right.right = new BTreeNode(20);
		root.right.right.left = new BTreeNode(0);
		root.right.right.left.left = new BTreeNode(-3);
		root.right.right.left.left.right = new BTreeNode(2);
		root.right.right.left.left.right.left = new BTreeNode(1);
		System.out.println(countPathsWithSum(root, 3));
		
		/*BTreeNode root = new BTreeNode(0);
		root.left = new BTreeNode(0);
		root.right = new BTreeNode(0);
		root.right.left = new BTreeNode(0);
		root.right.left.right = new BTreeNode(0);
		root.right.right = new BTreeNode(0);
		System.out.println(countPathsWithSum(root, 0));
		System.out.println(countPathsWithSum(root, 4));*/
	}
}
