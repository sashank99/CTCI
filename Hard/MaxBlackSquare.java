package Hard;

import CtCILibrary.AssortedMethods;

/**
 * Imagine you have a square matrix, where each cell (pixel) is either black or
 * white. Design an algorithm to find maximum subsquare such that all the four
 * borders are filled with black pixels.
 */
public class MaxBlackSquare {

	public static SubSquare findSquare(int[][] matrix) {
		if (matrix == null)
			return null;
		SquareCell[][] processed = processSquare(matrix);
		for (int i = matrix.length - 1; i >= 0; i--) {
			SubSquare square = findSquareWithSize(processed, i);
			if (square != null)
				return square;
		}
		return null;
	}

	public static SubSquare findSquareWithSize(SquareCell[][] processed, int size) {
		int count = processed.length - size + 1;

		for (int row = 0; row < count; row++) {
			for (int col = 0; col < count; col++) {
				if (isSquare(processed, row, col, size)) {
					return new SubSquare(row, col, size);
				}
			}
		}
		return null;
	}

	public static boolean isSquare(SquareCell[][] matrix, int row, int col, int size) {
		SquareCell topLeft = matrix[row][col];
		SquareCell topRight = matrix[row][col + size - 1];
		SquareCell bottomLeft = matrix[row + size - 1][col];

		if (topLeft.zerosRight < size || topLeft.zerosBelow < size || topRight.zerosBelow < size
				|| bottomLeft.zerosRight < size) {
			return false;
		}
		return true;
	}

	public static SquareCell[][] processSquare(int[][] matrix) {

		SquareCell[][] processed = new SquareCell[matrix.length][matrix.length];

		for (int r = matrix.length - 1; r >= 0; r--) {
			for (int c = matrix.length - 1; c >= 0; c--) {
				int zerosRight = 0;
				int zerosBelow = 0;
				if (matrix[r][c] == 0) {
					zerosRight++;
					zerosBelow++;
					if (r + 1 < matrix.length) {
						zerosBelow += processed[r + 1][c].zerosBelow;
					}
					if (c + 1 < matrix.length) {
						zerosRight += processed[r][c + 1].zerosRight;
					}
				}
				processed[r][c] = new SquareCell(zerosRight, zerosBelow);
			}
		}
		return processed;
	}
	
	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(7, 7, 0, 1);
		AssortedMethods.printMatrix(matrix);
		SubSquare square = findSquare(matrix);
		System.out.println(square);
	}
}

class SubSquare {
	public int row;
	public int col;
	public int size;

	public SubSquare(int row, int col, int size) {
		this.row = row;
		this.col = col;
		this.size = size;
	}

	@Override
	public String toString() {
		return "(row : " + row + " col: " + col + " size: " + size + " )";
	}
}

class SquareCell {
	public int zerosRight;
	public int zerosBelow;

	public SquareCell(int zerosRight, int zerosBelow) {
		this.zerosRight = zerosRight;
		this.zerosBelow = zerosBelow;
	}
}
