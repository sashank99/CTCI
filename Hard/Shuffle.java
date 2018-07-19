package Hard;

import CtCILibrary.AssortedMethods;

/**
 * Write a method to shuffle a deck of cards. It must be a perfect shuffle - in
 * other words, each of the 52! permutation of the deck has to be equally
 * likely. Assume that you are given a random number generator which is perfect.
 */
public class Shuffle {

	public static int random(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}

	public static void shuffleArrayRecursively(int[] cards) {

		for (int i = 0; i < cards.length; i++) {
			int k = random(0, i);
			int temp = cards[k];
			cards[k] = cards[i];
			cards[i] = temp;
		}
	}
	
	public static void main(String[] args) {
		int[] cards = {0, 1};
		System.out.println(AssortedMethods.arrayToString(cards));
		shuffleArrayRecursively(cards);
		System.out.println(AssortedMethods.arrayToString(cards));
	}
}
