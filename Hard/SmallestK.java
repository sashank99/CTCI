package Hard;

import java.util.Random;

/**
 * Design an algorithm to find the smallest k numbers in an array
 */
public class SmallestK {
	public int[] smallestK(int[] arr, int k) {
		if (arr == null || arr.length < k)
			return arr;
		int kthSmallest = findKSmallest(0, arr.length - 1, arr, k-1);
		int[] kSmallArr = new int[k];
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= kthSmallest) {
				kSmallArr[j] = arr[i];
				j++;
			}
		}
		return kSmallArr;
	}

	public int findKSmallest(int start, int end, int[] arr, int k) {
		int pivot = arr[findRandomPivot(start, end)];
		int leftEnd = partition(start, end, pivot, arr);
		int leftSize = leftEnd - start + 1;
		if (leftSize - 1 == k) {
			return max(arr, start, leftEnd);
		} else if (k < leftSize) {
			return findKSmallest(start, leftEnd, arr, k);
		} else {
			return findKSmallest(leftEnd + 1, end, arr, k - leftSize);
		}
	}

	public int partition(int start, int end, int pivot, int[] arr) {

		while (start <= end) {
			if (arr[start] > pivot) {
				swap(start, end, arr);
				end--;
			} else if (arr[end] <= pivot) {
				swap(start, end, arr);
				start++;
			} else {
				start++;
				end--;
			}
		}
		return start - 1;
	}

	public void swap(int start, int end, int[] arr) {
		int temp = arr[start];
		arr[start] = arr[end];
		arr[end] = temp;
	}

	public int findRandomPivot(int start, int end) {
		Random random = new Random();
		return random.nextInt(end + 1 - start) + start;
	}

	public int max(int[] arr, int start, int end) {
		int max = Integer.MIN_VALUE;
		for (int i = start; i <= end; i++) {
			max = Math.max(arr[i], max);
		}
		return max;
	}
	
	public static void main(String[] args) {
		int arr[]={5,6,3,7,1,9,2};
		int[] kSmallArr= new SmallestK().smallestK( arr, 4);
		for(int i=0;i<kSmallArr.length;i++){
			System.out.print(kSmallArr[i]+"\t");
		}
		System.out.println();
	}
}
