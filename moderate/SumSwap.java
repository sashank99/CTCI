package moderate;

import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays of integers, find a pair of values (one value from each
 * array) that you can swap to give the two arrays the same sum. Example:
 * Input:{4,1,2,1,1,2} and {3,6,3,3} Output:{1,3}
 */
public class SumSwap {
	public static int[] findSwapValues(int[] a, int[] b) {
		if (a == null || b == null)
			return null;
		Integer target = getTarget(a, b);
		if (target == null)
			return null;
		int[] pair = new int[2];
		if (target == 0)
			return pair;
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < b.length; i++) {
			set.add(b[i]);
		}
		for (int i = 0; i < a.length; i++) {
			if (set.contains(a[i] - target)) {
				pair[0] = a[i];
				pair[1] = a[i] - target;
				break;
			}
		}
		return pair;

	}

	public static Integer getTarget(int[] a, int[] b) {
		int aSum = sum(a);
		int bSum = sum(b);
		if ((aSum - bSum) % 2 != 0)
			return null;
		return aSum - bSum;
	}

	public static int sum(int[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int[] array1 = {-9, -1, -4, 8, 9, 6, -5, -7, 3, 9};
		int[] array2 = {6, 6, 4, -1, 7, -6, -9, 4, -8, 8};
		int[] swaps = findSwapValues(array1, array2);
		if (swaps == null) {
			System.out.println("null");
		} else {
			System.out.println(swaps[0] + " " + swaps[1]);
		}
	}
}
