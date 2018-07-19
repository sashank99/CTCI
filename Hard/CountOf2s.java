package Hard;

/**
 * Write a method to count the number of 2s that appear in all the numbers
 * between 0 and n(inclusive) Example: Input: 25 Output: 9 (2, 12, 20, 21, 22,
 * 23, 24 and 25. Note that 22 counts for two two 2s.)
 */
public class CountOf2s {

	public static int count2s(int n) {
		int count = 0;
		for (int i = 0; i < String.valueOf(n).length(); i++) {
			int powerOf10 = (int) Math.pow(10, i);
			int nextPowerOf10 = powerOf10 * 10;
			int right = n % powerOf10;
			int digit = (n / powerOf10) % 10;
			int roundDown = n - (n % nextPowerOf10);
			int roundUp = roundDown + nextPowerOf10;// for 25 round up is 30
			if (digit < 2) {
				count += roundDown / 10;
			} else if (digit == 2) { // if no is 2 then right side no+1 should
										// be counted: for ex: for 25.. now 20-
										// 25=6 will be counted
				count += roundDown / 10 + right + 1;
			} else {
				count += roundUp / 10;
			}
		}

		return count;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i <= 230; i++) {
			int v2 = count2s(i);	
			System.out.println("Between 0 and " + i + ": " + v2);
		}
	}
}
