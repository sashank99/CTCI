package sorting_searching;

import CtCILibrary.AssortedMethods;

/**
 * Given an MxN matrix in which each row and each column is sorted in ascending
 * order, write a method to find an element.
 */
public class SortedMatrixSearch {

	public static boolean findElement(int[][] matrix, int element) {
		if (matrix == null)
			return false;
		int row = 0;
		int col = matrix[0].length - 1;
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] == element) {
				return true;
			} else if (element < matrix[row][col]) {
				col--;
			} else {
				row++;
			}

		}
		return false;
	}
	
	public static void main(String[] args) {
		int M = 10;
		int N = 5;
		int[][] matrix = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				matrix[i][j] = 10 * i + j;
			}
		}
		
		AssortedMethods.printMatrix(matrix);
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				int v = 10 * i + j;
				System.out.println(v + ": " + findElement(matrix, v));
			}
		}
		
	}

}