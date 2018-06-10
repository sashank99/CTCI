package dynamic_programming;

/**
 * A child is running up a staircase with n steps and can hop either 1 step, 2
 * steps , 3 steps at a time. Implement a method to count how many ways the
 * child can run up the stairs
 */
public class TripleStep {

	public static int findPossibleWays(int n) {
		if (n <= 0)
			return 0;
		else if (n == 1)
			return 1;
		else if (n == 2)
			return 2;
		else if (n == 3)
			return 4;
		else {

			/**
			 * Top down approach
			 */
			int[] memo = new int[n + 1];
			memo[1] = 1;
			memo[2] = 2;
			memo[3] = 4;
			return findPossibleWays(n, memo);

			/**
			 * bottom up approach
			 * 
			 * int a=1; int b=2; int c=4; int d; for(int i=4;i<n;i++){ d=a+b+c;
			 * a=b; b=c; c=d; }
			 * 
			 * return a+b+c;
			 */

		}

	}

	/**
	 * Top down approach
	 */
	public static int findPossibleWays(int n, int[] memo) {
		if (memo[n] == 0) {
			memo[n] = findPossibleWays(n - 1, memo) + findPossibleWays(n - 2, memo) + findPossibleWays(n - 3, memo);
		}
		return memo[n];
	}

	public static void main(String[] args) {
		System.out.println(findPossibleWays(20));
	}

}
