package dynamic_programming;

import java.util.Arrays;

/**
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5
 * cents), and pennies (1 cent), Write code to calculate the number of ways of
 * representing n cents
 */
public class Coins {

	public static int findWays(int n) {
		if (n <= 0)
			return 0;
		// for 1 cents
		int[] countArr = new int[n + 1];
		
		Arrays.fill(countArr, 1);
		
		for(int i=5;i<countArr.length;i++){
			countArr[i] += countArr[i-5];
		}
		
		//for 10 cents
		for(int i=10;i<countArr.length;i++){
			countArr[i] += countArr[i-10];
		}
		
		//for 25 cents
		for(int i=25;i<countArr.length;i++){
			countArr[i] += countArr[i-25];
		}
		

		return countArr[n];
	}	
	
	public static void main(String[] args) {
		System.out.println(findWays(10000));
	}

}
