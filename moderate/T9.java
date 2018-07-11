package moderate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CtCILibrary.AssortedMethods;
import CtCILibrary.HashMapList;

/**
 * On Old cell phones, users typed on a numeric keypad and the phone would
 * provide a list of words that matched these numbers. Each digit mapped to a
 * set of 0-4 letters. Implement an algorithm to return a list of matching
 * words, given a sequence of digits. You are provided a list of valid words
 * (provided in whatever data structure you'd like). Ex: Input 8733 Output
 * tree,used
 */
public class T9 {
	private static char[][] t9Letters ={null, null, {'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};

	public static Map<Character, Integer> convertLetterToNumberMap() {
		Map<Character, Integer> letterToNumberMap = new HashMap<>();
		for (int i = 2; i < t9Letters.length; i++) {
			for (int j = 0; j < t9Letters[i].length; j++) {
				letterToNumberMap.put(t9Letters[i][j], i);
			}
		}
		return letterToNumberMap;
	}

	public static Map<String, List<String>> initializeDictionary(String[] words) {
		Map<Character, Integer> letterToNumberMap = convertLetterToNumberMap();
		Map<String, List<String>> dictionary = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			String t9No = convertWordToT9No(words[i], letterToNumberMap);
			List<String> wordList = dictionary.get(t9No);
			if (wordList == null) {
				wordList = new ArrayList<>();
			}
			wordList.add(words[i]);
			dictionary.put(t9No, wordList);
		}
		return dictionary;
	}

	public static String convertWordToT9No(String word, Map<Character, Integer> letterToNumberMap) {
		StringBuilder builder = new StringBuilder();
		for (char c : word.toCharArray()) {
			if (letterToNumberMap.containsKey(c))
				builder.append(letterToNumberMap.get(c));
		}
		return builder.toString();
	}

	public static List<String> getValidT9Words(String numbers, Map<String, List<String>> dictionary) {
		return dictionary.get(numbers);
	}
	
	public static void main(String[] args) {
		Map<String, List<String>> dictionary = initializeDictionary(AssortedMethods.getListOfWords());
		List<String> words = getValidT9Words("8733", dictionary);
		for (String w: words) {
			System.out.println(w);
		}	

	}
}
