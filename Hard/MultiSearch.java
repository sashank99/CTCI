package Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string b and an array of smaller string T, design a method to search
 * b for each small string in T. Ex: T={“is”,”ppi”,”hi”,”sis”,”i”,”ssippi”}
 * b=“mississippi”
 * 
 */
public class MultiSearch {

	public static Map<String, List<Integer>> searchAll(String big, String[] smalls) {
		if (big == null || smalls == null)
			return null;
		Map<String, List<Integer>> lookup = new HashMap<String, List<Integer>>();
		Trie trie = new Trie();
		for (String small : smalls) {
			trie.insertString(small, 0);
		}
		TrieNode root = trie.getRoot();
		for (int i = 0; i < big.length(); i++) {
			List<String> strings = findStringsAtLoc(root, big, i);
			insertIntoHashMap(strings, lookup, i);
		}
		return lookup;
	}

	public static List<String> findStringsAtLoc(TrieNode root, String big, int index) {
		List<String> strings = new ArrayList<>();

		int runningIndex = index;
		while (runningIndex < big.length()) {
			root = root.getChild(big.charAt(runningIndex));
			if (root == null)
				break;
			if (root.terminates()) {
				strings.add(big.substring(index, runningIndex + 1));
			}
			runningIndex++;
		}
		return strings;

	}

	public static void insertIntoHashMap(List<String> strings, Map<String, List<Integer>> lookup, int index) {
		for (String string : strings) {
			List<Integer> indexes = lookup.get(string);
			if (indexes == null) {
				indexes = new ArrayList<>();
			}
			indexes.add(index);
			lookup.put(string, indexes);
		}
	}

	public static void main(String[] args) {
		String big = "mississippi";
		String[] smalls = { "is", "ppi", "hi", "sis", "i", "mississippi" };
		Map<String, List<Integer>> locations = searchAll(big, smalls);
		System.out.println(locations.toString());
	}
}

class Trie {
	private TrieNode root = new TrieNode();

	public Trie(String s) {
		insertString(s, 0);
	}

	public Trie() {
	}

	public List<Integer> search(String s) {
		return root.search(s);
	}

	public void insertString(String str, int location) {
		root.insertString(str, location);
	}

	public TrieNode getRoot() {
		return root;
	}

}

class TrieNode {
	private Map<Character, TrieNode> children;
	private List<Integer> indexes;

	public TrieNode() {
		children = new HashMap<>();
		indexes = new ArrayList<>();
	}

	public void insertString(String s, int index) {
		if (s == null)
			return;
		indexes.add(index);
		if (index >= s.length()) {
			children.put('\0', null); // Terminating character
		} else {
			char value = s.charAt(index);
			TrieNode child = children.get(value);
			if (child == null) {
				child = new TrieNode();
				children.put(value, child);
			}
			child.insertString(s, index + 1);
		}
	}

	public List<Integer> search(String s) {
		if (s == null || s.length() == 0) {
			return indexes;
		} else {
			char first = s.charAt(0);
			if (children.containsKey(first)) {
				String remainder = s.substring(1);
				return children.get(first).search(remainder);
			}
		}
		return null;
	}

	public boolean terminates() {
		return children.containsKey('\0');
	}

	public boolean contains(char c) {
		return children.containsKey(c);
	}

	public TrieNode getChild(char c) {
		return children.get(c);
	}
}
