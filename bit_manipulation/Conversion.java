package bit_manipulation;

/**
 * Write a function to determine the number of bits you need to flip to convert
 * integer A to integer B. Ex: Input: 29 (or: 11101), 15 (or: 01111) Output: 2
 */
public class Conversion {

	public static int bitFlipsRequiredToConvert(int A, int B) {
		int result = A ^ B;
		int count = 0;
		/* Brute Force
		 * while (result != 0) {
			if ( (result & 1) == 1) {
				count++;
			}
			result >>>= 1;
		}*/
		
		//Optimal Solution
		while( result!=0){
			count++;
			result &=(result-1);
		}
		return count;
	}
	
	public static void main(String[] args) {
		int a = -23432;
		int b = 512132;
		System.out.println(a + ": " + toFullBinaryString(a));
		System.out.println(b + ": " + toFullBinaryString(b));
		int nbits = bitFlipsRequiredToConvert(a, b);
		System.out.println("Required number of bits: " + nbits);
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
