package string_arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings, write a method to decide if one is a perumutation of other
 *
 **/
public class CheckPermutations {

	public static void main(String[] args) {

		String str1 = "abba";
		String str2 = "baba";
		System.out.println(" is String:::"+ str1+" permutation of String:::"+str2+" ?::"+isPermutation(str1, str2));
	}

	private static boolean isPermutation(String str1, String str2) {

		if (str1.length() != str2.length()) {
			return false;
		}
		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < str1.length(); i++) {
			Integer val = map.get(str1.charAt(i));
			if (val == null) {
				val = 0;
			}
			val++;
			map.put(str1.charAt(i), val);
		}

		for (int i = 0; i < str2.length(); i++) {
			Integer val = map.get(str2.charAt(i));
			if (val == null || val == 0) {
				return false;
			}
			val--;
			map.put(str2.charAt(i), val);
		}

		return true;
	}

}