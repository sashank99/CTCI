package trees_graphs;

import java.util.Arrays;

/**
* T1 and T2 are two large binary trees with T1 much bigger than T2. Creatre an algorithm to determine if T2 is a subTree of T1.
* A tree T2 is a subtree of T1 if there exists a node n in T1 such that subtree of n is identical to T2. 
* That is, if you cut off the tree at node n, the two trees would be identical.
*/
public class CheckSubTree{

	public static boolean containsTree(BTreeNode t1,BTreeNode t2){
		if(t2==null){
			return true;
		}
		return checkSubTree(t1,t2);
		
		/**
		Solution for O(M+N) space.
		StringBuilder t1Builder=new StringBuilder();
		StringBuilder t2Builder=new StringBuilder();
		preOrderTraversalString(t1,t1Builder);
		preOrderTraversalString(t2,t2Builder);
		return t1Builder.indexOf(t2Builder.toString())!=-1;
		*/
		
	}
	
	/**
	* O(log(M)+log(N)) space and O(M+kN) time complexity method
	* where k is no of t2 root node matches in t1 tree.
	*/
	public static boolean checkSubTree(BTreeNode t1,BTreeNode t2){
		if(t1==null){
			return false;
		}
		if(t1.val==t2.val && matchTree(t1,t2)){
			return true;
		}
		
		return containsTree(t1.left,t2) || containsTree(t1.right,t2);
	}
	
	public static boolean matchTree(BTreeNode t1,BTreeNode t2){
		
		if(t1==null && t2==null){
			return true;
		}else if(t1==null || t2==null){
			return false;
		}else if(t1.val!=t2.val){
			return false;
		}else{
			return matchTree(t1.left,t2.left) && matchTree(t1.right,t2.right);
		}
	}
	
	
	/**
	* O(M+N) space and O(M+N) time complexity method
	*/
	public static void preOrderTraversalString(BTreeNode node,StringBuilder treeBuilder){
		if(node==null){
			treeBuilder.append("X");
			return;
		}
		treeBuilder.append(node.val).append(" ");
		preOrderTraversalString(node.left,treeBuilder);
		preOrderTraversalString(node.right,treeBuilder);
	}
	
	public static void main(String[] args) {
		int[] array1 = {1, 2, 1, 3, 1, 1, 5};//1 1 1 1 2 3 5
		Arrays.sort(array1);
		BTreeNode node1=MinimalTree.createMinimalBST(array1);
		BTreeNode node2=new BTreeNode(3);
		node2.left=new BTreeNode(2);
		node2.right=new BTreeNode(5);
		System.out.println(containsTree(node1,node2));
		
		/**
		 * 
		 1
 		1	3
 		1 2	  5
		 */
	}
	
}
