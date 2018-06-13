package dynamic_programming;

/**
 * Implement the "paint fill" function that one might see on many image editing
 * programs. That is, given a screen (represented by a two-dimensional array of
 * colors), a point, and a new color, fill in the surrounding area until the
 * color changes from the original color.
 */
public class PaintFill {
	public static boolean fillPaint(Color[][] screen, int row, int col, Color newColor) {
		if (screen[row][col] == newColor)
			return false;
		return fillPaint(screen, row, col, screen[row][col], newColor);
	}

	public static boolean fillPaint(Color[][] screen, int row, int col, Color oldColor, Color newColor) {
		if (row < 0 || row >= screen.length || col < 0 || col >= screen.length) {
			return false;
		}

		if (screen[row][col] == oldColor) {
			screen[row][col] = newColor;
			fillPaint(screen, row + 1, col, oldColor, newColor);
			fillPaint(screen, row - 1, col, oldColor, newColor);
			fillPaint(screen, row, col - 1, oldColor, newColor);
			fillPaint(screen, row, col + 1, oldColor, newColor);
		}
		return true;
	}

	public static void main(String[] args) {
		int N = 10;
		Color[][] screen = new Color[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				screen[i][j] = Color.BLACK;
			}
		}
		for (int i = 0; i < 100; i++) {
			screen[randomInt(N)][randomInt(N)] = Color.GREEN;
		}
		PrintScreen(screen);
		fillPaint(screen, 2, 2, Color.WHITE);
		System.out.println();
		PrintScreen(screen);
	}

	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}

	public static void PrintScreen(Color[][] screen) {
		for (int r = 0; r < screen.length; r++) {
			for (int c = 0; c < screen[0].length; c++) {
				System.out.print(PrintColor(screen[r][c]));
			}
			System.out.println();
		}
	}

	public static String PrintColor(Color c) {
		switch (c) {
		case BLACK:
			return "B";
		case WHITE:
			return "W";
		case RED:
			return "R";
		case YELLOW:
			return "Y";
		case GREEN:
			return "G";
		}
		return "X";
	}

}

enum Color {
	BLACK, WHITE, BLUE, RED, GREEN, YELLOW
}
