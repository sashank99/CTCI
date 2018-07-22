package Hard;

import java.util.*;

/**
 * Given two words of equal length that are in a dictionary, write a method to
 * transform one word into another word by changing only one letter at a time.
 * The new word you get in each step must be in the dictionary. Ex: Input: DAMP,
 * LIKE Output: DAMP -> LAMP -> LIMP -> LIME -> LIKE
 */
public class WordTransformer {

	public static LinkedList<String> transform(String startWord, String stopWord, String[] words) {
		if (startWord == null || stopWord == null || words == null)
			return null;
		Map<String, List<String>> wildCardToWordMap = createWildCardToWordMap(words);

		BFSData srcData = new BFSData(startWord);
		BFSData destData = new BFSData(stopWord);

		while (!srcData.isFinished() && !destData.isFinished()) {

			String collision = searchLevel(wildCardToWordMap, srcData, destData);
			if (collision != null) {
				return mergePaths(srcData, destData, collision);
			}

			collision = searchLevel(wildCardToWordMap, destData, srcData);
			if (collision != null) {
				return mergePaths(srcData, destData, collision);
			}
		}
		return null;
	}

	private static String searchLevel(Map<String, List<String>> wildCardToWordMap, BFSData primary, BFSData secondary) {

		int count = primary.toVisit.size();

		for (int i = 0; i < count; i++) {
			PathNode pathNode = primary.toVisit.poll();
			String word = pathNode.getWord();
			if (secondary.visited.containsKey(word))
				return pathNode.getWord();

			List<String> words = getValidLinkedWords(word, wildCardToWordMap);
			for (String w : words) {
				if (!primary.visited.containsKey(w)) {
					PathNode next = new PathNode(w, pathNode);
					primary.visited.put(w, next);
					primary.toVisit.add(next);
				}
			}
		}

		return null;
	}

	private static LinkedList<String> mergePaths(BFSData bfs1, BFSData bfs2, String connection) {
		PathNode end1 = bfs1.visited.get(connection);
		PathNode end2 = bfs2.visited.get(connection);
		LinkedList<String> pathOne = end1.collapse(false); // forward
		LinkedList<String> pathTwo = end2.collapse(true); // reverse
		pathTwo.removeFirst();
		pathOne.addAll(pathTwo);
		return pathOne;
	}

	private static Map<String, List<String>> createWildCardToWordMap(String[] words) {
		Map<String, List<String>> wildCardToWords = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			List<String> linked = getWildCardRoots(words[i]);
			for (String linkedWord : linked) {
				List<String> list = wildCardToWords.get(linkedWord);
				if (list == null) {
					list = new ArrayList<>();
				}
				list.add(words[i]);
				wildCardToWords.put(linkedWord, list);
			}
		}
		return wildCardToWords;
	}

	private static List<String> getWildCardRoots(String word) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			String w = word.substring(0, i) + "_" + word.substring(i + 1);
			list.add(w);
		}
		return list;
	}

	private static List<String> getValidLinkedWords(String word, Map<String, List<String>> wildCardToWords) {
		List<String> wildCardRoots = getWildCardRoots(word);
		List<String> linkedWords = new ArrayList<>();
		for (String wildcard : wildCardRoots) {
			List<String> words = wildCardToWords.get(wildcard);
			for (String linkedWord : words) {
				if (!linkedWord.equals(word)) {
					linkedWords.add(linkedWord);
				}
			}
		}
		return linkedWords;
	}
	
	public static void main(String[] args) {
		String[] words = {"maps", "tan", "tree", "apple", "cans", "help", "aped", "pree", "pret", "apes", "flat", "trap", "fret", "trip", "trie", "frat", "fril"};		
		LinkedList<String> list = transform("tree", "flat", words);
		
		if (list == null) {
			System.out.println("No path.");
		} else {
			System.out.println(list.toString());
		}
	}
}

class BFSData {
	public Queue<PathNode> toVisit = new LinkedList<>();
	public Map<String, PathNode> visited = new HashMap<>();

	public BFSData(String root) {
		PathNode srcPath = new PathNode(root, null);
		toVisit.add(srcPath);
		visited.put(root, srcPath);
	}

	public boolean isFinished() {
		return toVisit.isEmpty();
	}
}

class PathNode {
	private String word;
	private PathNode previousNode;

	public PathNode(String word, PathNode previousNode) {
		this.word = word;
		this.previousNode = previousNode;
	}

	public String getWord() {
		return word;
	}

	public LinkedList<String> collapse(boolean startsWithRoot) {
		LinkedList<String> path = new LinkedList<>();
		PathNode node = this;
		while (node != null) {
			if (startsWithRoot) {
				path.addLast(node.getWord());
			} else {
				path.addFirst(node.getWord());
			}
			node = node.previousNode;
		}
		return path;
	}
}
