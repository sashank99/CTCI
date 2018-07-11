package moderate;

import CtCILibrary.AssortedMethods;

/**
 * Write a method to implement the multiply, subtract and divide operations for
 * integers. The results of all these are integers. Use only the add operator.
 */
public class Operations {

	public int negate(int a) {
		if (a == 0)
			return 0;
		int sign = a < 0 ? 1 : -1;
		int delta = sign;
		int neg = 0;
		while (a != 0) {
			boolean differentSigns = (a + delta) < 0 != a < 0;
			if ((a + delta) != 0 && differentSigns) {
				delta = sign;
			}
			neg += delta;
			a += delta;
			delta += delta;
		}
		return neg;
	}

	public int abs(int a) {
		if (a < 0) {
			return negate(a);
		}
		return a;
	}

	public int substract(int a, int b) {
		return a + negate(b);
	}

	public int multiply(int a, int b) {
		if (a == 0 || b == 0)
			return 0;
		if (b > a) {
			return multiply(b, a);
		}
		int result = 0;
		int absb = abs(b);
		while (absb > 0) {
			result += a;
			absb = substract(absb, 1);

		}
		if (b < 0)
			result = negate(result);
		return result;

	}

	public int divide(int a, int b) {
		if (b == 0) {
			throw new java.lang.ArithmeticException("Error");
		}
		int absa = abs(a);
		int absb = abs(b);
		if (absb > absa) {
			return 0;
		}

		int product = 0;
		int x = 0;
		while (product+ absb <= absa) {
			product += absb;
			x += 1;
		}
		
		if ((a > 0 && b > 0) || (a < 0 && b < 0))
			return x;
		else
			return negate(x);
	}

	public static void main(String[] args) {
		Operations operations = new Operations();
		int minRange = -100;
		int maxRange = 100;
		int cycles = 100;

		for (int i = 0; i < cycles; i++) {
			int a = AssortedMethods.randomIntInRange(minRange, maxRange);
			int b = AssortedMethods.randomIntInRange(minRange, maxRange);
			int ans = operations.substract(a, b);
			if (ans != a - b) {
				System.out.println("ERROR");
			}
			System.out.println(a + " - " + b + " = " + ans);
		}
		for (int i = 0; i < cycles; i++) {
			int a = AssortedMethods.randomIntInRange(minRange, maxRange);
			int b = AssortedMethods.randomIntInRange(minRange, maxRange);
			int ans = operations.multiply(a, b);
			if (ans != a * b) {
				System.out.println("ERROR");
			}
			System.out.println(a + " * " + b + " = " + ans);
		}
		for (int i = 0; i < cycles; i++) {
			int a = AssortedMethods.randomIntInRange(minRange, maxRange);
			int b = AssortedMethods.randomIntInRange(minRange, maxRange);
			System.out.print(a + " / " + b + " = ");
			int ans = operations.divide(a, b);
			if (ans != a / b) {
				System.out.println("ERROR");
			}
			System.out.println(ans);
		}
	}
}
