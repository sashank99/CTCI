package trees_graphs;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
/**
* Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g if you have a tree with depth D, you'll have d linked lists)
*/
public class ListOfDepths{

	public static void createDepthLinkedLists(BTreeNode root, List<LinkedList<BTreeNode>> lists,int depth){
		
		if(root==null){
			return;
		}
		LinkedList<BTreeNode> depthLinkedList;
		if(lists.size()>depth){
			depthLinkedList=lists.get(depth);
		}else{
			depthLinkedList=new LinkedList<>();
			lists.add(depthLinkedList);
		}
		depthLinkedList.add(root);
		createDepthLinkedLists(root.left,lists,depth+1);
		createDepthLinkedLists(root.right,lists,depth+1);
	
	}
	
	public static List<LinkedList<BTreeNode>> createDepthLinkedLists(BTreeNode root){
		List<LinkedList<BTreeNode>> lists=new ArrayList<>();
		createDepthLinkedLists(root,lists,0);
		return lists;
	}
	
	public static void main(String[] args) {
		int[] array=new int[]{1,2,3,4,5,6,7,8,9};
		BTreeNode root=MinimalTree.createMinimalBST(array);
		System.out.println(root);
		System.out.println(createDepthLinkedLists(root));
	}

}