package moderate;

/**
 * Write an algorithm which computes the number of trailing zeros in n factorial
 */
public class FactorialZeros {

	public static int countZerosInFactorial(int n) {
		int count = 0;
		for (int i = 5; n / i > 0; i *= 5) {
			count += n / i;
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(countZerosInFactorial(125));
	}

}
