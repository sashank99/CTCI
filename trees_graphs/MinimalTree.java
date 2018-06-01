package trees_graphs;

/**
* Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height.
*
*/
public class MinimalTree{
	
	public static void main(String[] args){
		int[] array=new int[]{1,2,3,4,5,6,7,8,9};
		System.out.println(createMinimalBST(array));
	}
	
	public static BTreeNode createMinimalBST(int[] array){
		return createMinimalBST(array,0,array.length-1);
	}

	public static BTreeNode createMinimalBST(int[] array,int startPos,int endPos){
		if(startPos >endPos){
			return null;
		}
		int midPos=(startPos+endPos)/2;
		BTreeNode midNode=new BTreeNode(array[midPos]);
		midNode.left=createMinimalBST(array,startPos,midPos-1);
		midNode.right=createMinimalBST(array,midPos+1,endPos);
		return midNode;
		
	}

}
