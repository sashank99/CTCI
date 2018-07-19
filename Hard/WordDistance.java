package Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CtCILibrary.AssortedMethods;

/**
 * You have a large text file containing words. Given any two words, find the
 * shortest distance (in terms of number of words) between them in the file. If
 * the operation will be repeated many times for the same file( but different
 * pairs of words), can you optimize your solution?
 */
public class WordDistance {

	public static LocationPair findClosest(String word1, String word2, Map<String, List<Integer>> locations) {
		List<Integer> locations1 = locations.get(word1);
		List<Integer> locations2 = locations.get(word2);
		return findMinDistancePair(locations1, locations2);
	}

	public static LocationPair findMinDistancePair(List<Integer> array1, List<Integer> array2) {
		if (array1 == null || array2 == null || array1.isEmpty() || array2.isEmpty())
			return null;
		int index1 = 0;
		int index2 = 0;
		LocationPair best = new LocationPair(array1.get(0), array2.get(0));
		LocationPair current = new LocationPair(array1.get(0), array2.get(0));

		while (index1 < array1.size() && index2 < array2.size()) {
			current.setLocations(array1.get(index1), array2.get(index2));
			best.updateWithMin(current);
			if (index1 < index2) {
				index1++;
			} else {
				index2++;
			}
		}
		return best;
	}

	public static Map<String, List<Integer>> getWordLocations(String[] words) {
		Map<String, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			List<Integer> locationList = map.get(words[i]);
			if (locationList == null) {
				locationList = new ArrayList<>();
			}
			locationList.add(i);
			map.put(words[i], locationList);
		}
		return map;
	}
	
	public static void main(String[] args) {
		String[] wordlist = AssortedMethods.getLongTextBlobAsStringList();
		String word1 = "river";
		String word2 = "life";
		Map<String, List<Integer>> locations = getWordLocations(wordlist);
		LocationPair pair = findClosest(word1, word2, locations);
		System.out.println("Distance between <" + word1 + "> and <" + word2 + ">: " + pair.toString());	
	}
}

class LocationPair {
	private int location1;
	private int location2;

	public LocationPair(int location1, int location2) {
		setLocations(location1, location2);
	}

	public void setLocations(int first, int second) {
		this.location1 = first;
		this.location2 = second;
	}

	public void setLocations(LocationPair loc) {
		setLocations(loc.location1, loc.location2);
	}

	public int distance() {
		return Math.abs(location1 - location2);
	}

	public boolean isValid() {
		return location1 >= 0 && location2 >= 0;
	}

	public void updateWithMin(LocationPair loc) {
		if (!isValid() || loc.distance() < distance()) {
			setLocations(loc);
		}
	}
	
	@Override
	public String toString() {
		return "(" + location1 + ", " + location2 + ")";
	}
}
