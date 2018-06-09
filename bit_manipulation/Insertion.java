package bit_manipulation;

/**
 * You are given two 32- bit numbers, N and M, and two bit positions, i and j.
 * Write a method to insert M into N such that M starts at bit j and ends at bit
 * i. You can assume that the bits j through i have enough space to fit all M.
 * That is, if M=10011, you can assume that there are at least 5 bits between j
 * and i. You would not, for example, have j=3 and i=2, because M could not
 * fully fit between a bit 3 and bit 2. Example Input N=10000000000, M=10011,
 * i=2, j=6 Output N= 10001001100
 */
public class Insertion {

	public static int updateBits(int n, int m, int i, int j) {
		int ones = ~0; //sets all bits to ones

		// set ones from start to j+1
		int left = ones << (j + 1);

		// set ones from i+1 to end
		int right = ~(ones << (i));
		//right = ((1 << i)-1);
		int mask = left | right;

		// clear bits from j to i in N
		int n_cleared = n & mask;

		// shift m by i positions to set 0 from i+2 to end
		int m_shifted = m << i;

		return n_cleared | m_shifted;
	}
	
	public static void main(String[] args) {
		int a = 103217;
		System.out.println(toFullBinaryString(a));
		int b = 13;
		System.out.println(toFullBinaryString(b));		
		int c = updateBits(a, b, 4, 12);
		System.out.println(toFullBinaryString(c));
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