package moderate;

/**
 * Given an array of integers, write a method to find indices m and n such that
 * if you sorted elements m through n, the entire array would be sorted.
 * Minimize n-m (that is, find the smallest such sequence)
 */
public class SubSort {

	public static void findUnSortedSequence(int[] a) {
		if (a == null || a.length <= 1)
			return;

		int max_value = a[0];
		int max_un_index = -1;
		int min_un_val = Integer.MAX_VALUE;
		for (int i = 1; i < a.length; i++) {
			if (max_value > a[i]) {
				min_un_val = Math.min(min_un_val, a[i]);
				max_un_index = i;
				continue;
			}
			max_value = Math.max(max_value, a[i]);
		}
		if (max_un_index == -1){
			System.out.println("array is already sorted");
			return; // array is already sorted.
		}
			
		int min_un_index = 0;
		for (int i = 0; i < a.length; i++) {
			if (min_un_val <= a[i]) {
				min_un_index = i;
				break;
			}
		}
		System.out.println("m:" + min_un_index + " n:" + max_un_index);
	}
	
	public static void main(String[] args) {
		int[] arr={1,2,4,7,10,11,7,12,6,7,16,18,19};
		findUnSortedSequence(arr);
		arr=new int []{1, 9, 4, 3, 5};
		findUnSortedSequence(arr);
		arr=new int []{1,2,3,4,5,6,7};
		findUnSortedSequence(arr);
		arr=new int []{7,6,5,4,3,2,1};
		findUnSortedSequence(arr);
	}
}
