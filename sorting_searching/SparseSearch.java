package sorting_searching;

/**
 * Given a sorted array of strings that is interspersed with empty strings,
 * write a method to find the location of a given string Example: Input: balls,
 * {"at","","","","ball","","","car","","","dad","",""} Output: 4
 */
public class SparseSearch {

	public static int search(String str, String[] arr) {
		if (str == null || "".equals(str) || arr == null || arr.length == 0)
			return -1;
		return search(str, arr, 0, arr.length - 1);
	}

	public static int search(String str, String[] arr, int start, int end) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if ("".equals(arr[mid])) {

			int index = findNextNonEmptyIndex(arr, start, end, mid);
			if (index == -1)
				return -1;
			int compareVal = str.compareTo(arr[index]);
			if (compareVal == 0)
				return index;
			else if (compareVal < 0)
				return search(str, arr, start, index - 1);
			else {
				if (index < mid) {
					return search(str, arr, mid + 1, end);
				} else {
					return search(str, arr, start, mid - 1);
				}
			}
		}
		int diff = str.compareTo(arr[mid]);
		if (diff == 0)
			return mid;
		else if (diff < 0)
			return search(str, arr, start, mid - 1);
		else
			return search(str, arr, mid + 1, end);
	}

	public static int findNextNonEmptyIndex(String[] arr, int start, int end, int mid) {
		int tempMid = mid;
		// Find non empty string on left;
		while (tempMid >= start && "".equals(arr[tempMid]))
			tempMid--;

		if (tempMid >= start)
			return tempMid;

		// search for non empty string on right if tempMid less than start
		tempMid = mid;
		while (tempMid <= end && "".equals(arr[tempMid]))
			tempMid++;
		if (tempMid <= end)
			return tempMid;
		return -1;

	}

	public static void main(String[] args) {
		String[] stringList = { "apple", "", "", "banana", "", "", "", "carrot", "duck", "", "", "eel", "", "flower" };
		System.out.println(search("banana", stringList));

	}
}
