package Hard;

/**
 * Consider a simple data structure called BiNode, which has pointers to two
 * other nodes. The data structure BiNode could be used to represent both a
 * binary tree(where node1 is the left node and node2 is the right node) or a
 * doubly linked list (where node1 is the previous node and node2 is next
 * node).Implement a method to convert a binary search tree (implemented with
 * BiNode) into a doubly linked list. The values should be kept in order and the
 * operation should be performed in place( that is, on the original data
 * structure).
 */
public class BiNode {
	public BiNode node1;
	public BiNode node2;
	public int data;

	public BiNode(int d) {
		data = d;
	}
	
	public static void main(String[] args) {
		BiNode root = Solution.createTree();
		Solution.printAsTree(root, "");
		BiNode r = Solution.convert(root);
		Solution.printLinkedListTree(r);
	}

}

class Solution {

	public static BiNode convertToCircular(BiNode root) {
		if (root == null)
			return null;
		BiNode part1 = convertToCircular(root.node1);
		BiNode part2 = convertToCircular(root.node2);
		if (part1 == null && part2 == null) {
			root.node1 = root;
			root.node2 = root;
			return root;
		}
		BiNode tail2 = (part2 == null) ? null : part2.node1;
		if (part1 == null) {
			concat(part2.node1, root);
		} else {
			concat(part1.node1, root);
		}

		if (part2 == null) {
			concat(root, part1);
		} else {
			concat(root, part2);
		}

		if (part1 != null && part2 != null) {
			concat(tail2, part1);
		}
		return part1 == null ? root : part1;
	}

	public static BiNode convert(BiNode root) {
		BiNode head = convertToCircular(root);
		head.node1.node2 = null;
		head.node1 = null;
		return head;
	}

	public static void concat(BiNode x, BiNode y) {
		x.node2 = y;
		y.node1 = x;
	}

	public static BiNode createTree() {
		BiNode[] nodes = new BiNode[7];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new BiNode(i);
		}
		nodes[4].node1 = nodes[2];
		nodes[4].node2 = nodes[5];
		nodes[2].node1 = nodes[1];
		nodes[2].node2 = nodes[3];
		nodes[5].node2 = nodes[6];
		nodes[1].node1 = nodes[0];
		return nodes[4];
	}

	public static void printLinkedListTree(BiNode root) {
		for (BiNode node = root; node != null; node = node.node2) {
			if (node.node2 != null && node.node2.node1 != node) {
				System.out.print("inconsistent node: " + node);
			}
			System.out.print(node.data + "->");
		}
		System.out.println();
	}

	public static void printAsTree(BiNode root, String spaces) {
		if (root == null) {
			System.out.println(spaces + "- null");
			return;
		}
		System.out.println(spaces + "- " + root.data);
		printAsTree(root.node1, spaces + "   ");
		printAsTree(root.node2, spaces + "   ");
	}

	
}
