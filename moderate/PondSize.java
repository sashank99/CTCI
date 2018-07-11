package moderate;

import java.util.List;
import java.util.ArrayList;

/**
 * You have an integer matrix representing a plot of land, where the value at
 * that location represents the height above sea level. A value of zero
 * indicates water. A pond is a region of water connected vertically,
 * horizontally or diagonally. The size of the pond is the total number of
 * connected water cells. Write a method to compute the sizes of all ponds in
 * the matrix. 
 * Ex: Input: 
 * 0 2 1 0
 * 0 1 0 1 
 * 1 1 0 1 
 * 0 1 0 1 
 * Output: 2,4,1 (in any order)
 */
public class PondSize {

	public static List<Integer> computePondSizes(int[][] land) {

		List<Integer> pondSizesList = new ArrayList<>();
		if (land == null)
			return pondSizesList;
		for (int row = 0; row < land.length; row++) {
			for (int col = 0; col < land[row].length; col++) {
				if (land[row][col] == 0) {
					pondSizesList.add(computePondSizes(land, row, col));
				}
			}
		}
		return pondSizesList;
	}

	private static int computePondSizes(int[][] land, int row, int col) {
		if (row < 0 || col < 0 || row >= land.length || col >= land[row].length)
			return 0;
		if (land[row][col] != 0)
			return 0;
		land[row][col] = -1;
		int size = 1;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
					size += computePondSizes(land, i, j);
			}
		}
		return size;

	}
	
	public static void main(String[] args) {
		int[][] land = {{0, 2, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 1}, {0, 1, 0, 1}};
		List<Integer> sizes = computePondSizes(land);
		for (int sz : sizes) {
			System.out.println(sz);
		}
	}
}
