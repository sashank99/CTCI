package trees_graphs;

/**
* Write an algorithm to find the next node (i.e., in-order successor) of a given node in a binary search tree. 
* You may assume that each node has a link to its parent
*/
public class InOrderSuccessor{

	public static BTreeNode nextNode(BTreeNode findNode,BTreeNode rootNode){
		if(findNode==null){
			return null;
		}
		BTreeNode rightNode=findNode.right;
		
		if(rightNode!=null){
			return getLeftLeafNode(rightNode);
		}else{
			return getNextRightLeaf(findNode,rootNode,null,null);
		}
	}
	
	private static BTreeNode getNextRightLeaf(BTreeNode findNode,BTreeNode rootNode,BTreeNode minNode,BTreeNode maxNode){
	
		if(findNode==rootNode){
			return maxNode;
		}else if(findNode.val< rootNode.val){
			return getNextRightLeaf(findNode,rootNode.left,minNode,rootNode);
		}else{
			return getNextRightLeaf(findNode,rootNode.right,rootNode,maxNode);
		}
		
		
	}
	
	private static BTreeNode getLeftLeafNode(BTreeNode node){
		BTreeNode previousNode=node;
		while(node.left!=null){
			previousNode=node.left;
			node=node.left;
		}
		
		return previousNode;
	}
	
	public static void main(String[] args) {
		int[] array=new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33};
		BTreeNode root = MinimalTree.createMinimalBST(array);
		for (int i = 0; i < array.length; i++) {
			BTreeNode node = root.find(array[i]);
			BTreeNode next = nextNode(node,root);
			if (next != null) {
				System.out.println(node.val + "->" + next.val);
			} else {
				System.out.println(node.val + "->" + null);
			}
		}
		
	}

}
