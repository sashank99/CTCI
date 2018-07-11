package moderate;

import java.util.Arrays;

/**
 * Design an algorithm to find all pairs of integers within an array which sum
 * to a specified value.
 */
public class PairsWithSum {

	public static void printPairSums(int[] arr, int sum) {
		if (arr == null || arr.length == 0)
			return;
		Arrays.sort(arr);
		int first = 0;
		int last = arr.length - 1;
		while (first < last) {
			int s = arr[first] + arr[last];
			if (s == sum) {
				System.out.println(arr[first] + " " + arr[last]);
				first++;
				last--;
			} else if (s < sum) {
				first++;
			} else {
				last--;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] test = {9, 3, 6, 5, 7, 7, -1, 13, 14, -2, 12, 0};
		printPairSums(test, 12);
	}
}