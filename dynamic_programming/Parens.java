package dynamic_programming;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement an algorithm to print all valid(i.e, properly opened and closed)
 * combinations of n pairs of parentheses Input: 3 Output: ((())), (())
 */
public class Parens {

	public static List<String> generateParens(int n) {
		if (n <= 0)
			return null;
		List<String> parensList = new ArrayList<>();
		char[] charArr = new char[2 * n];
		generateParens(n, n, parensList, charArr, 0);
		return parensList;
	}

	public static void generateParens(int left, int right, List<String> parensList, char[] charArr, int index) {
		if (left < 0 || right < left)
			return;

		if (left == 0 && right == 0) {
			parensList.add(new String(charArr));
			return;
		}
		charArr[index] = '(';
		generateParens(left - 1, right, parensList, charArr, index + 1);
		charArr[index] = ')';
		generateParens(left, right - 1, parensList, charArr, index + 1);
	}
	
	public static void main(String[] args) {
		System.out.println(generateParens(5).size());
	}

}
