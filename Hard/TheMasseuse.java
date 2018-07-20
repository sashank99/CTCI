package Hard;

/**
 * A popular masseuse receives a sequence of back to back appointment requests
 * and is debating which ones to accept. She needs a 15 minute break between
 * appointments and therefore she cannot accept any adjacent requests. Given a
 * sequence of back to back appointment requests (all multiple of 15 minutes,
 * none overlap, and none can be moved), find the optimal (highest total booked
 * minutes) set the masseuse can honor. Return the number of minutes. Example:
 * Input {30, 15, 60, 75, 45, 15, 15, 45} Output: 180 minutes ({30, 60,45,45})
 */
public class TheMasseuse {

	public static int findHighestTotalMins(int[] arr) {
		if (arr == null || arr.length == 0)
			return 0;
		int oneAwayBest=0;
		int twoAwayBest=0;
		for(int i=arr.length-1;i>=0;i--){
			int currentBest=Math.max(oneAwayBest, twoAwayBest+arr[i]);
			twoAwayBest=oneAwayBest;
			oneAwayBest=currentBest;
		}
		return oneAwayBest;
	}

	
	public static void main(String[] args) {
		int[] massages = {150, 30, 135, 150, 60, 105}; // 2 4 3 3
		System.out.println(findHighestTotalMins(massages));
	}
}
