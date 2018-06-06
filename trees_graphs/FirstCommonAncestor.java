package trees_graphs;

import java.util.ArrayList;
import java.util.List;

/**
* Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree.
* Avoid storing additional nodes in a data structure. 
* Note: This is not necessarily a binary search tree.
*/
public class FirstCommonAncestor{

	public static BTreeNode findAncestor(BTreeNode root, BTreeNode a, BTreeNode b){
		
		if(!doesNodeExists(root,a) || !doesNodeExists(root,b)) return null;
	
		return findFirstAncestor(root, a, b);
	}
	
	public static BTreeNode findFirstAncestor(BTreeNode root, BTreeNode a, BTreeNode b){
		
		if(root==null){
			return null;
		}
		
		if(root==a || root==b){
			return root;
		}
		BTreeNode left=findFirstAncestor(root.left,a,b);
		BTreeNode right=findFirstAncestor(root.right,a,b);
		
		if(left!=null && right!=null){
			return root;
		}
		
		return left!=null? left: right;
		
	}
	
	public static boolean doesNodeExists(BTreeNode root, BTreeNode node){
		if(root==null) return false;
		if(root==node) return true;
		return doesNodeExists(root.left,node) || doesNodeExists(root.right,node);
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		BTreeNode root = MinimalTree.createMinimalBST(array);
		
		List<BTreeNode> nodes = new ArrayList<>();
		for (int a : array) {
			nodes.add(root.find(a));
		}
		nodes.add(new BTreeNode(11));
		
		for (BTreeNode x : nodes) {
			for (BTreeNode y : nodes) {
				BTreeNode node = findAncestor(root,x, y);
				Integer val= node==null? null:node.val;
				System.out.println("common ancestor of "+x.val+" and "+y.val+" is:"+val);
				
			}
		}
	}
	

}
