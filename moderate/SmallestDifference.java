package moderate;

import java.util.Arrays;

/**
 * Given two arrays of integers, compute the pair of values (one value in each
 * array) with the smallest (non-negative) difference. Return the difference.
 * Ex: Input: {1,3,15,11,2},{23,127,235,19,8} Output: 3. That is, the pair
 * (11,8)
 */
public class SmallestDifference {

	public static int getSmallestDiff(int[] a, int[] b) {
		if (a == null || b == null || a.length == 0 || b.length == 0)
			return Integer.MAX_VALUE;
		Arrays.sort(a);
		Arrays.sort(b);
		int aIndex = 0;
		int bIndex = 0;
		int min = Integer.MAX_VALUE;
		while (aIndex < a.length && bIndex < b.length) {
			min = Math.min(min, Math.abs(a[aIndex] -b[bIndex]));
			if (min == 0)
				return min;
			if (a[aIndex] < b[bIndex])
				aIndex++;
			else
				bIndex++;

		}
		return min;

	}
	
	public static void main(String[] args) {
		int[] array1 = {1, 3, 15, 11, 2};
		int[] array2 = {23, 127, 234, 19, 8};
		int difference = getSmallestDiff(array1, array2);
		System.out.println(difference);
	}
}
