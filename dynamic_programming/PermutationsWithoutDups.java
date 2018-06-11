package dynamic_programming;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a method to compute all permutations of a string of unique characters
 */
public class PermutationsWithoutDups {

	public static List<String> findPermutations(String string) {
		if (string == null)
			return null;
		List<String> permutations = new ArrayList<>();
		if (string.length() == 0) {
			permutations.add("");
			return permutations;
		}
		char startPos = string.charAt(0);
		List<String> subPermutations = findPermutations(string.substring(1));
		for (String permutation : subPermutations) {
			for (int i = 0; i <= permutation.length(); i++) {
				permutations.add(insertCharAt(i, startPos, permutation));
			}
		}

		return permutations;
	}

	public static String insertCharAt(int pos, char c, String word) {
		return word.substring(0, pos) + c + word.substring(pos);
	}
	
	public static void main(String[] args) {
		System.out.println(findPermutations("abcde"));
	}

}