package sorting_searching;

/**
 * Given a sorted array of n integers that has been rotated an unknown number of
 * times, write code to find an element in the array. You may assume that array
 * was originally sorted in increasing order. Ex: Input: find 5 in {15, 16, 19,
 * 20, 25, 1, 3, 4, 5, 7, 10, 14} Output: 8 (index 0f 5 in the array)
 */
public class SearchRotatedArray {

	public static int search(int[] arr, int x) {
		if (arr == null || arr.length == 0)
			return -1;
		return search(arr, 0, arr.length - 1, x);
	}

	public static int search(int[] arr, int left, int right, int x) {
		if (left > right)
			return -1;
		int mid = (left + right) / 2;
		if (arr[mid] == x)
			return mid;

		if (arr[left] < arr[mid]) {
			if (x >= arr[left] && x < arr[mid])
				return search(arr, left, mid - 1, x);
			else
				return search(arr, mid + 1, right, x);
		} else if (arr[left] > arr[mid]) {
			if (x > arr[mid] && x <= arr[right])
				return search(arr, mid + 1, right, x);
			else
				return search(arr, left, mid - 1, x);
		} else {
			if (arr[mid] != arr[right])
				return search(arr, mid + 1, right, x);
			else {
				int index = search(arr, left, mid - 1, x);
				if (index == -1)
					search(arr, mid + 1, right, x);
				else
					return index;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{15,16,19,20,25,1,3,4,5,7,10,14};
		System.out.println(search(arr,14));
		int[] a = { 2, 3, 1, 2, 2, 2, 2, 2 , 2 , 2 };
		System.out.println(search(a, 2));
		System.out.println(search(a, 3));
		System.out.println(search(a, 4));
		System.out.println(search(a, 1));
		System.out.println(search(a, 8));
	}
}
