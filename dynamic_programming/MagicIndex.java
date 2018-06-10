package dynamic_programming;

import java.util.Arrays;

import CtCILibrary.AssortedMethods;

/**
 * A magic index in an array A[1....n-1] is defined to be an index such that
 * A[i]=i. Given a sorted array of distinct integers, while a method to find a
 * magic index, if one exists, in array A.
 *
 * FOLLOW UP What if the values are not distinct.
 */
public class MagicIndex {

	public static int findMagicIndex(int[] arr) {
		if (arr == null || arr.length == 0)
			return -1;
		return findMagicIndex(arr, 0, arr.length - 1);
	}

	/**
	*
	*/
	public static int findMagicIndex(int[] arr, int start, int end) {
		if (start > end) {
			return -1;
		}

		int mid = (start + end) / 2;
		if (arr[mid] == mid)
			return mid;
		else if (arr[mid] > mid)
			return findMagicIndex(arr, start, mid - 1);
		return findMagicIndex(arr, mid + 1, end);

	}

	/**
	 * For non distinct
	 */
	public static int findMagicIndexOfDups(int[] arr, int start, int end) {
		if (start > end) {
			return -1;
		}

		int mid = (start + end) / 2;
		if (arr[mid] == mid)
			return mid;
		else {
			int left = findMagicIndex(arr, start, Math.min(arr[mid], mid - 1));
			if (left >= 0)
				return left;
			return findMagicIndex(arr, Math.max(mid + 1, arr[mid]), end);
		}

	}
	
	/* Creates an array that is sorted */
	public static int[] getSortedArray(int size) {
		int[] array = AssortedMethods.randomArray(size, -1 * size, size);
		Arrays.sort(array);
		return array;
	}
	
	public static void main(String[] args){
		for (int i = 0; i < 1000; i++) {
			int size = AssortedMethods.randomIntInRange(5, 20);
			int[] array = getSortedArray(size);
			int v2 = findMagicIndexOfDups(array, 0, array.length-1);
			System.out.println("magic index:::"+v2);
			System.out.println(AssortedMethods.arrayToString(array));
		}
		
		int[] arr= new int[]{-1,1,3,4,5,6,7,8};
		System.out.println(findMagicIndex(arr));
	}

}
