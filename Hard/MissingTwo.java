package Hard;

/**
 * You are given an array with all the numbers with all the numbers from 1 to N
 * appearing exactly once, except for one number that is missing. How can you
 * find the missing number in O(N) time and O(1) space? What if there were two
 * missing?
 */
public class MissingTwo {

	public static int[] findMissingTwo(int[] arr){
		if(arr==null) return null;
		int n=arr.length+2;
		// sum of first n natural numbers
		int remSum= n * (n+1)/2; 
		// sum of the squares of first n natural numbers
		int remSqSum = n * (n+1) *(2*n+1)/6;
		
		//for finding a+b and a^2+b^2 values
		for(int i=0;i<arr.length;i++){
			remSum -=arr[i];
			remSqSum -= (int)Math.pow(arr[i],2);
		}
		
		//Finding ab value using (a+b)^2 equation a^2+b^2+2ab
		int product_of_2= (int)(Math.pow(remSum,2)-remSqSum)/2;
		//Finding a-b values using (a-b)^2 =a^2+b^2-2ab
		int sum_diff=(int)Math.sqrt(remSqSum - 2 * product_of_2);
		int a= (remSum+ sum_diff)/2;
		int b= remSum-a;
		
		return new int[]{a,b};
	}
	
	public static void main(String[] args) {
		int max = 100;
		for (int x = 1; x < max; x++) {
			for (int y = 1; y < max; y++) {
				if (x != y) {
					int len = max - 2;
					int count = 0;
					int[] array = new int[len];
					for (int i = 1; i <= max; i++) {
						if (i != x && i != y) {
							array[count] = i;
							count++;
						}
					}
					int[] solution = findMissingTwo(array);
					
					if ((solution[0] == x && solution[1] == y) ||
						(solution[1] == x && solution[0] == y)) {	
						System.out.println("Success: " + solution[0] + ", " + solution[1]);
					} else {
						System.out.println("Error: " + x + ", " + y + " | " + solution[0] + ", " + solution[1]);
					}
				}
			}
		}
	}
}
