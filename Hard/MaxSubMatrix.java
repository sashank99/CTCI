package Hard;

import CtCILibrary.AssortedMethods;

/**
 * Given an NxN matrix of positive and negative integers, write code to find the
 * sub matrix with the largest possible sum.
 */
public class MaxSumMatrix {

	public static SubMatrix getMaxMatrix(int[][] matrix) {
		if (matrix == null)
			return null;
		int rowCount = matrix.length;
		int colCount = matrix[0].length;
		SubMatrix best = null;

		for (int row1 = 0; row1 < rowCount; row1++) {
			int[] partialSum = new int[colCount];
			for (int row2 = row1; row2 < rowCount; row2++) {
				for (int col = 0; col < colCount; col++) {
					partialSum[col] += matrix[row2][col];
				}
				ColRange bestRange = maxSubArray(partialSum, colCount);
				if (best == null || bestRange.sum > best.getSum()) {
					best = new SubMatrix(row1, bestRange.start, row2, bestRange.end, bestRange.sum);
				}
			}
		}
		return best;
	}

	private static ColRange maxSubArray(int[] arr, int count) {
		ColRange best = null;
		int sum = 0;
		int start = 0;
		for (int i = 0; i < count; i++) {
			sum += arr[i];
			if (best == null || sum > best.sum) {
				best = new ColRange(start, i, sum);
			}

			if (sum < 0) {
				start = i + 1;
				sum = 0;
			}
		}
		return best;
	}
	
	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(4, 4, -5, 5);
		AssortedMethods.printMatrix(matrix);
		System.out.println(getMaxMatrix(matrix));
	}
}

class ColRange {
	public int start;
	public int end;
	public int sum;

	public ColRange(int start, int end, int sum) {
		this.start = start;
		this.end = end;
		this.sum = sum;
	}
}

class SubMatrix {
	private int row1;
	private int row2;
	private int col1;
	private int col2;
	private int sum;

	public SubMatrix(int row1, int col1, int row2, int col2, int sum) {
		this.row1 = row1;
		this.row2 = row2;
		this.col1 = col1;
		this.col2 = col2;
		this.sum = sum;
	}

	public int getSum() {
		return sum;
	}

	@Override
	public String toString() {
		return "[(" + row1 + "," + col1 + ") -> (" + row2 + "," + col2 + ") = " + sum + "]";
	}
}
