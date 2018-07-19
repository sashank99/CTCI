package Hard;

import java.util.Arrays;

import CtCILibrary.AssortedMethods;

/**
 * Write a method to randomly generate a set of m integers from an array of size
 * n. Each element must have equal probability of being chosen.
 */
public class RandomSet {

	public static int random(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}

	public static int[] randomSet(int[] arr, int m) {
		if (arr == null || m > arr.length)
			return null;

		for (int i = 0; i < m; i++) {
			int k = random(i, arr.length-1);
			int temp = arr[k];
			arr[k] = arr[i];
			arr[i] = temp;
		}
		return Arrays.copyOf(arr, m);
	}

	public static void main(String[] args) {
		int[] cards = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println(AssortedMethods.arrayToString(cards));
		int[] set = randomSet(cards, 4);
		System.out.println(AssortedMethods.arrayToString(set));
	}

}
