package moderate;

import java.util.Random;

/**
 * The Game of Master Mind is played as follows: The computer has four slots,
 * and each slot will contain a ball that is red(R), yellow(Y), green(G) or
 * blue(B). For example, the computer might have RGGB (Slot #1 is red, Slots #2
 * and #3 are green, Slot #4 is blue). You, the user, are trying to guess the
 * solution. You might, for example, guess YRGB. When you guess the correct
 * color for the correct slot, you get a “hit”. If you guess a color that exists
 * but is in the wrong slot, you get a “pseudo-hit”. Note that a slot that is a
 * hot can never count as a pseudo-hit. For example, if the actual solution is
 * RGBY and you guess GGRR, you have one hit and one pseudo-hit. Write a method
 * that, given a guess and a solution, returns the number of hits and
 * pseudo-hits.
 */
public class MasterMind {
	public static final int MAX_COLORS = 4;

	public static Result getCounts(String guess, String solution) throws Exception {
		if (guess == null || solution == null || solution.length() != guess.length()) {
			throw new Exception("Invalid input parameters passed");
		}
		int[] frequencies = new int[MAX_COLORS];
		int hits = 0;
		int pseudoHits = 0;
		for (int i = 0; i < solution.length(); i++) {
			if (solution.charAt(i) == guess.charAt(i)) {
				hits++;
			} else {
				frequencies[code(solution.charAt(i))]++;
			}
		}

		for (int i = 0; i < guess.length(); i++) {
			if (solution.charAt(i) != guess.charAt(i) && frequencies[code(solution.charAt(i))] > 0) {
				pseudoHits++;
				frequencies[code(solution.charAt(i))]--;
			}
		}

		return new Result(hits, pseudoHits);

	}

	public static int code(char ch) {
		switch (ch) {
		case 'R':
			return 0;
		case 'G':
			return 1;
		case 'B':
			return 2;
		case 'Y':
			return 3;
		default:
			return -1;
		}
	}
	
/************************** TEST CODE **********************************/
	
	public static char letterFromCode(int k) {
		switch (k) {
		case 0:
			return 'B';
		case 1:
			return 'G';
		case 2:
			return 'R';
		case 3:
			return 'Y';
		default:
			return '0';
		}		
	}
	
	public static Result estimateBad(String g, String s) {
		char[] guess = g.toCharArray();
		char[] solution = s.toCharArray();
		int hits = 0;
		for (int i = 0; i < guess.length; i++) {
			if (guess[i] == solution[i]) {
				hits++;
				solution[i] = '0';
				guess[i] = '0';
			}
		}
		
		int pseudohits = 0;
		
		for (int i = 0; i < guess.length; i++) {
			if (guess[i] != '0') {
				for (int j = 0; j < solution.length; j++) {
					if (solution[j] != '0') {
						if (solution[j] == guess[i]) {
							pseudohits++;
							solution[j] = '0';
							break;
						}
					}
				}
			}
		}
		
		return new Result(hits, pseudohits);
	}
	
	public static String randomString() {
		int length = 4;
		char[] str = new char[length];
		Random generator = new Random();
		
		for (int i = 0; i < length; i++) {
			int v = generator.nextInt(4);
			char c = letterFromCode(v);
			str[i] = c;
		}
		
		return String.valueOf(str);
	}
	
	public static boolean test(String guess, String solution) throws Exception {
		Result res1 = getCounts(guess, solution);
		Result res2 = getCounts(guess, solution);
		if (res1.getHits() == res2.getHits() && res1.getPseudoHits() == res2.getPseudoHits()) {
			return true;
		} else {
			System.out.println("FAIL: (" + guess + ", " + solution + "): " + res1.toString() + " | " + res2.toString());
			return false;
		}
	}
	
	public static boolean testRandom() throws Exception {
		String guess = randomString();
		String solution = randomString();
		return test(guess, solution);
	}
	
	public static boolean test(int count) throws Exception {
		for (int i = 0; i < count; i++) {
			if (!testRandom()) {
				return true;
			}
		}
		return false;
	}
	
	/********************** END TEST CODE 
	 * @throws Exception ************************/
	
	
	public static void main(String[] args) throws Exception {
		test(1000);
	}
}

class Result {

	private int hits;
	private int pseudoHits;

	public Result(int hits, int pseudoHits) {
		this.hits = hits;
		this.pseudoHits = pseudoHits;
	}

	public int getHits() {
		return hits;
	}

	public int getPseudoHits() {
		return pseudoHits;
	}
	
	public String toString(){
		return "("+ hits +", "+pseudoHits+")";
	}
}