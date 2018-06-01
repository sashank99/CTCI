package trees_graphs;

/**
 * Implement a function to check if a binaryTree is a binary search tree.
 */
public class BSTValidator {

	public static boolean validateBST(BTreeNode root) {
		if (root == null)
			return false;
		return validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean validateBST(BTreeNode node, int min, int max) {
		if (node == null) {
			return true;
		}
		if (node.val < min || node.val > max) {
			return false;
		}

		boolean leftNodeValidation = validateBST(node.left, min, node.val);
		if (!leftNodeValidation) {
			return false;
		}

		boolean rightNodeValidation = validateBST(node.right, node.val, max);
		if (!rightNodeValidation) {
			return false;
		}

		return true;
	}
	
	public static void main(String[] args) {
		int[] array=new int[]{1,9,4,4,5,6,7,8,9,10,11};
		BTreeNode node=MinimalTree.createMinimalBST(array);
		System.out.println(validateBST(node));
	}

}
