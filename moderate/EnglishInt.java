package moderate;

import CtCILibrary.AssortedMethods;

/**
 * Given any integer, print an english phrase the describes the integer (e.g.,
 * "One Thousand, Two Hundred Thirty Four")
 */
public class EnglishInt {

	static String[] smallNos = { "Zero ", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ",
			"Nine ", "Ten ", "Eleven", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ",
			"Eighteen ", "Nineteen " };
	static String[] tens = { "", "", "Twenty ", "Thirty ", "Fourty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ",
			"Ninety " };

	public static String convert(int n) {
		if (n == 0)
			return smallNos[n];
		else if (n < 0)
			return "Minus " + convert(Math.abs(n));

		StringBuilder builder = new StringBuilder();
		int chunks;
		int chunksCount = 1;
		while (n != 0) {
			// Divide the no to chunks of three
			chunks = n % 1000;
			n = n / 1000;
			if (chunksCount == 2 && chunks != 0) {
				builder.insert(0, "Thousand ");
			} else if (chunksCount == 3 && chunks != 0) {
				builder.insert(0, "Million ");
			} else if (chunksCount == 4 && chunks != 0) {
				builder.insert(0, "Billion ");
			}
			convertChunks(chunks, builder);

			chunksCount++;
		}
		return builder.toString();
	}

	public static void convertChunks(int n, StringBuilder builder) {
		if (n == 0)
			return;
		int twoDigitNo = n % 100;
		n = n / 100;
		// For one digit and two digit places
		if (0 < twoDigitNo && twoDigitNo < 20) {
			builder.insert(0, smallNos[twoDigitNo]);
		} else if (twoDigitNo != 0) {
			if (twoDigitNo % 10 != 0) {
				builder.insert(0, smallNos[twoDigitNo % 10]);
			}
			twoDigitNo /= 10;
			builder.insert(0, tens[twoDigitNo]);
		}
		// For no at 3 digits place
		if (n > 0) {
			builder.insert(0, "Hundred ");
			builder.insert(0, smallNos[n]);
		}

	}

	public static void main(String[] args) {
		/* numbers between 100000 and 1000000 */
		for (int i = 0; i < 8; i++) {
			int value = (int) (-1 * Math.pow(10, i));
			String s = convert(value);
			System.out.println(value + ": " + s);
		}

		/* numbers between 0 and 100 */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(0, 100);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}

		/* numbers between 100 and 1000 */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(100, 1000);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}

		/* numbers between 1000 and 100000 */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(1000, 100000);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}

		/* numbers between 100000 and 100000000 */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(100000, 100000000);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}

		/* numbers between 100000000 and 1000000000 */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(100000000, 1000000000);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}

		/* numbers between 1000000000 and Integer.MAX_VALUE */
		for (int i = 0; i < 10; i++) {
			int value = AssortedMethods.randomIntInRange(1000000000, Integer.MAX_VALUE);
			String s = convert(value);
			System.out.println(value + ": " + s);
		}
	}
}
