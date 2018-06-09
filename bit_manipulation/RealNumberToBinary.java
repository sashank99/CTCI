package bit_manipulation;

/**
 * Given a real number between 0 and 1 (e.g., 0.72) that is passed in as
 * dopuble, print the binary representation. If the number cannot be represented
 * accurately in binary with an most 32 characters, print "ERROR"
 */
public class RealNumberToBinary {

	public static String convert(double n) {
		if (n <= 0 || n >= 1) {
			return "ERROR";
		}
		StringBuilder binaryBuilder = new StringBuilder(".");
		while (n > 0) {
			if (binaryBuilder.length() >= 32) {
				return "ERROR";
			}

			n = n * 2;
			if (n >= 1) {
				binaryBuilder.append(1);
				n = n - 1;
			} else {
				binaryBuilder.append(0);
			}
		}
		return binaryBuilder.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(convert(.125));
		
		for (int i = 0; i < 1000; i++) {
			double num = i / 1000.0;
			String binary = convert(num);
			if (!binary.equals("ERROR")) {
				System.out.println(num + " : " + binary);
			}
		}
	}
}
