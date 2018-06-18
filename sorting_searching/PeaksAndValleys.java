package sorting_searching;

import CtCILibrary.AssortedMethods;

/**
 * In an array of Integers, a “peak” is an element which is greater than or
 * equal to adjacent integers and a “vela” is an element which is less than or
 * equal to the adjacent integers. For example, in the array {5,8,6,2,3,4,6},
 * {8,6} are peaks and {5,2} are valleys. Given an array of Integers, sort the
 * array into an alternating sequence of peaks and valleys. Example Input:
 * {5,3,1,2,3} Output: {5,1,3,2,3}
 */
public class PeaksAndValleys {

	/**
	 * This method will set even indexes to peak and odd indexes to valley
	 * @param arr
	 */
	public static void sortPeakValley(int[] arr) {
		if (arr == null)
			return;
		for (int i = 0; i < arr.length - 1; i++) {

			if (i % 2 == 0) {
				// For setting peak
				if (arr[i] < arr[i + 1]) {
					swap(arr, i);
				}
			} else {
				// For setting valley
				if (arr[i] > arr[i + 1]) {
					swap(arr, i);
				}
			}
		}

	}

	public static void swap(int[] array, int index) {
		int temp = array[index];
		array[index] = array[index + 1];
		array[index + 1] = temp;
	}
	
	public static void main(String[] args) {
		int[] arr={5,8,6,2,3,4,6};
		AssortedMethods.printIntArray(arr);
		sortPeakValley(arr);
		AssortedMethods.printIntArray(arr);
		
		arr=new int[]{9,1,0,4,8,7};
		AssortedMethods.printIntArray(arr);
		sortPeakValley(arr);
		AssortedMethods.printIntArray(arr);
		
		arr=new int[]{0,1,2};
		AssortedMethods.printIntArray(arr);
		sortPeakValley(arr);
		AssortedMethods.printIntArray(arr);
		
		int[] array = {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
		AssortedMethods.printIntArray(array);
		sortPeakValley(array);
		AssortedMethods.printIntArray(array);
	}
}
