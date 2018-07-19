package Hard;

import java.util.HashSet;
import java.util.Set;

import CtCILibrary.AssortedMethods;

/**
 * Oh, no! You have accidentally removed all spaces, punctuation, and
 * capitalization in a lengthy document. A sentence like "I reset the computer.
 * It still didn’t boot!" became "iresetthecomputeritstilldidntreboot". You’ll
 * deal with the punctuation and capitalization later; right now you need to
 * re-insert the spaces. Most of the words are is a dictionary but a few are
 * not. Given a dictionary (a list of strings) and the document (a string),
 * design an algorithm to unconcatenate the document in a way that minimizes the
 * number of unrecognized characters. Example: Input:
 * jesslookedjustliketimherbrother Output: jess looked just like tim her brother
 */
public class ReShape {

	public static String minimizeInvalidChars(String string, Set<String> dictionary) {
		if (string == null)
			return null;
		ParseResult[] memo = new ParseResult[string.length()];
		ParseResult result = split(string, dictionary, memo, 0);
		return result == null ? null : result.parsed;
	}

	public static ParseResult split(String string, Set<String> dictionary, ParseResult[] memo, int start) {
		if (start >= string.length())
			return new ParseResult(0, "");
		if (memo[start] != null)
			return memo[start];

		int bestInvalid = Integer.MAX_VALUE;
		String bestParsed = null;
		String partial = "";
		int index = start;
		while (index < string.length()) {
			char c = string.charAt(index);
			partial += c;
			int invalid = dictionary.contains(partial) ? 0 : partial.length();
			if (invalid < bestInvalid) {
				ParseResult result = split(string, dictionary, memo, index + 1);
				if (invalid + result.invalid < bestInvalid) {
					bestInvalid = invalid + result.invalid;
					bestParsed = partial + " " + result.parsed;
					if (bestInvalid == 0) {
						break;
					}
				}
			}
			index++;
		}

		memo[start] = new ParseResult(bestInvalid, bestParsed);
		return memo[start];
	}
	
		public static String clean(String str) {
			char[] punctuation = {',', '"', '!', '.', '\'', '?', ','};
			for (char c : punctuation) {
				str = str.replace(c, ' ');
			}
			return str.replace(" ", "").toLowerCase();
		}
		
		public static void main(String[] args) {
			HashSet<String> dictionary = AssortedMethods.getWordListAsHashSet();
			String sentence = "As one of the topk companies in the world, Google will surely attract the attention of computer gurus. This does not, however, mean the company is for everyone.";
			sentence = clean(sentence);
			System.out.println(sentence);
			//Result v = parse(0, 0, new HashMap<Integer, Result>());
			//System.out.println(v.parsed);
			System.out.println(minimizeInvalidChars(sentence,dictionary));
		}
}

class ParseResult {
	public int invalid = Integer.MAX_VALUE;
	public String parsed;

	public ParseResult(int invalid, String parsed) {
		this.invalid = invalid;
		this.parsed = parsed;
	}
}
