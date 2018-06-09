package bit_manipulation;

/**
 * Write a program to swap odd and even bits in an integer with as few
 * instructions as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3
 * are swapped and so on)
 */
public class PairWiseSwap {

	public static int swapOddEvenBits(int num) {
		return (((num & 0xaaaaaaaa) >>> 1) | ((num & 0x55555555) << 1));
	}
	
	public static void main(String[] args) {
		int a = -23432;
		System.out.println(a + ": " + toFullBinaryString(a));
		int res = swapOddEvenBits(a);
		System.out.println(res + ": " + toFullBinaryString(res));
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
