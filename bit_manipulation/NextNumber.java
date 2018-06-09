package bit_manipulation;

/**
 * Given a positive integer, print the next smallest and the next largest number
 * that have the same number of 1 bits in their binary representation.
 */
public class NextNumber {
	/**
	 * Returns the next big number containing same number of 1's as given input.
	 */
	public static int getNext(int n) {
		int c = n;
		int c0 = 0; // no of trailing zeros from right before non trailing zero
		int c1 = 0; // no of trailing ones from right before non trailing zero
		while ((c & 1) == 0 && c != 0) {
			c0++;
			c >>= 1;
		}

		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}

		if (c0 + c1 == 31 || c0 + c1 == 0) {
			return -1;
		}

		int p = c0 + c1; // position of right most non trailing zero.
		n |= (1 << p); // flip the right most non trailing zero to 1.
		n &= (~0 << p);// clear all the bits from 0 to right of P
		n |= (1 << (c1 - 1)) - 1;// insert c1-1 ones on the right.
		return n;
	}

	/**
	 * Returns the next smallet number containing same number of 1's as given
	 * input.
	 */
	public static int getPrevious(int n) {
		int c = n;
		int c0 = 0; // no of trailing zeros from right before non trailing one
		int c1 = 0; // no of trailing ones from right before non trailing one
		while ((c & 1) == 1) {
			c1++;
			c >>= 1;
		}
		if (c == 0)
			return -1;

		while ((c & 1) == 0 && c != 0) {
			c0++;
			c >>= 1;
		}

		int p = c0 + c1; // position of right most non trailing one.
		n &= (~0 << (p + 1));// clear all the bits from 0 to P
		int mask = (1 << (c1 + 1)) - 1; // sequence of (c1+1) ones
		n |= mask << (c0 - 1);
		return n;
	}

	public static void main(String[] args) {
		int i = 13948;
		System.out.println(toFullBinaryString(i));
		int small = getPrevious(i);
		int big = getNext(i);
		System.out.println(toFullBinaryString(small));
		System.out.println(toFullBinaryString(big));
	}

	public static String toFullBinaryString(int a) {
		String s = "";
		for (int i = 0; i < 32; i++) {
			Integer lsb = new Integer(a & 1);
			s = lsb.toString() + s;
			a = a >> 1;
		}
		return s;
	}

}
