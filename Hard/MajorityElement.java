package Hard;

/**
 * A majority element is an element that makes up more than half of the items in
 * an array. Given a positive integers array, find the majority element. If
 * there is no majority element, return -1. Do this in O(N) time and O(1) space.
 * Input: 1 2 3 9 5 9 5 5 5 Output: 5
 */
public class MajorityElement {

	public static int findMajorityElement(int[] arr) {
		if (arr == null || arr.length == 0)
			return -1;
		int count = 0;
		int majorityElement = -1;
		for (int element : arr) {
			if (count == 0) {
				majorityElement = element;
				count++;
			} else if (element == majorityElement) {
				count++;
			} else {
				count--;
			}
		}
		count = 0;
		for (int element : arr) {
			if (element == majorityElement) {
				count++;
			}
		}
		return count > arr.length / 2 ? majorityElement : -1;

	}

	public static void main(String[] args) {
		int[] array = { 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 3 };
		System.out.println(array.length);
		System.out.println(findMajorityElement(array));
	}
}
