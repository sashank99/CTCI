package moderate;

/**
 * You are given an array of integers (both positive and negative). Find the
 * contiguous sequence with the largest sum. Return the sum. Ex: Input: 2, -8,
 * 3, -2, 4, -10 Output: 5 ( i.e., :{3,-2,4})
 */
public class ContiguousSequence {

	public static int getMaxSum(int[] a) {
		int maxSum = 0;
		if (a == null)
			return maxSum;
		int sum = 0;

		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum > maxSum) {
				maxSum = sum;
			}
			if (sum < 0) {
				sum = 0;
			}
		}
		return maxSum;
	}
	
	public static void main(String[] args) {
		int[] a = {2, -8, 3, -2, 4, -10};
		System.out.println(getMaxSum(a));
	}

}