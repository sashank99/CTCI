package dynamic_programming;

/**
 * Write a recursive function to multiply two positive integers with out using
 * the * operator. You can use addition, substraction, and bit sharing, but you
 * should minimize the number of those operators.
 */
public class RecursiveMultiply {

	public static int multiply(int a, int b) {
		int smaller = Math.min(a, b);
		int bigger = Math.max(a, b);
		return minProductHelper(smaller, bigger);
	}

	public static int minProductHelper(int a, int b) {
		if (a == 0)
			return 0;
		if (a == 1)
			return b;
		boolean isOdd = a % 2 != 0;
		int multVal = multiply(a / 2, b);
		multVal += multVal;
		if (isOdd)
			return multVal + b;
		return multVal;
	}
	
	public static void main(String[] args){
		System.out.println(multiply(100,22));
	}
}
