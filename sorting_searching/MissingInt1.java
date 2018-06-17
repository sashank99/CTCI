package sorting_searching;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Given an input file with four billion non-negative integers, provide and
 * algorithm to generate an integer that is not contained in the file. Assume
 * you have 1 GB of memory available for this task.
 *
 * FOLLOW UP What if you have only 10 MB of memory? Assume that all the values
 * are distinct and we now have no more than one billion non-negative integers.
 */
public class MissingInt1 {
	private static long intSize = ((long) Integer.MAX_VALUE) + 1;
	private static byte[] bitVector = new byte[(int) (intSize / 8)];

	public static int findFirstMissingOne(File file) throws IOException {

		FileReader reader = new FileReader(file);
		Scanner scanner = new Scanner(reader);
		while (scanner.hasNextInt()) {
			int n = scanner.nextInt();
			bitVector[n / 8] |= 1 << (n % 8);
		}
		scanner.close();
		reader.close();
		for (int i = 0; i < bitVector.length; i++) {
			for (int j = 0; j < 8; j++) {
				if ((bitVector[i] & (1 << j)) == 0) {
					return i * 8 + j;
				}
			}
		}
		return -1;

	}

	public static void main(String[] args) throws IOException {
		System.out.println(findFirstMissingOne(new File("input.txt")));
	}
}
