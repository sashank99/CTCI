package Hard;

/**
 * Imagine a histogram (bar graph). Design an algorithm to compute volume of
 * water it could hold if someone poured water across the top. You can assume
 * that each histogram bar has width 1. Ex:
 * Input:{0,0,4,0,0,6,0,0,3,0,5,0,1,0,0,0} Output: 26
 */
public class HistogramVolume {

	public static int getHistogramVolume(int[] volume) {
		if (volume == null)
			return 0;
		int[] leftMaxes = new int[volume.length];
		int[] rightMaxes = new int[volume.length];
		int maxLeft = 0;
		for (int i = 0; i < volume.length; i++) {
			if (volume[i] > maxLeft) {
				maxLeft = volume[i];
			}
			leftMaxes[i] = maxLeft;
		}

		int maxRight = 0;
		for (int i = volume.length - 1; i >= 0; i--) {
			if (volume[i] > maxRight) {
				maxRight = volume[i];
			}
			rightMaxes[i] = maxRight;
		}

		int sum = 0;
		for (int i = 0; i < volume.length; i++) {
			sum += Math.abs(Math.min(leftMaxes[i], rightMaxes[i]) - volume[i]);
		}
		return sum;

	}

	public static void main(String[] args) {
		String[][] tests = { { "6 1 8 1 2 1 5", "16" }, { "5 1 2 1 8", "11" }, { "15 12 20 16 17 25", "10" },
				{ "28 25 26", "1" }, { "28 25 28", "3" }, { "22", "0" }, { "22 22", "0" },
				{ "0 0 4 0 0 6 0 0 3 0 8 0 2 0 5 2 0 3 0 0", "46" } };
		for (int i = 0; i < tests.length; i++) {
			String input = tests[i][0];
			String output = tests[i][1];
			String[] inputStringArray = input.split(" ");
			int[] histogram = new int[inputStringArray.length];
			for (int j = 0; j < inputStringArray.length; j++) {
				histogram[j] = Integer.parseInt(inputStringArray[j]);
			}
			int targetVolume = Integer.parseInt(output);
			if (targetVolume == getHistogramVolume(histogram)) {
				System.out.println("Success");
			} else {
				System.out.println("Fail");
			}
		}
	}
}
