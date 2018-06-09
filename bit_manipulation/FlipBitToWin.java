package bit_manipulation;

/**
* You have an Integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest sequence of 1s you could create.
* Ex:
* Input: 1775 (or: 11011101111)
* Output: 8
*/
public class FlipBitToWin{

	public static int findLongestOneSeq(int num){
		if( ~num ==0) return Integer.BYTES * 8;
		int max=1;
		int runningCount= 0;
		int prevCount=0;
		int runningBit;
		while(num!=0){
			runningBit = num & 1;
			
			if(runningBit ==1){
				runningCount++;
			}else{
				prevCount= (num &2)==0? 0: runningCount;
				runningCount=0;
			}
			num =num >>> 1;
			max=Math.max(runningCount+prevCount+1,max);
			
		}
		return max;
		
	}
	
	public static void main(String[] args) {
		int[][] cases = {{-1, 32}, {Integer.MAX_VALUE, 32}, {-10, 31}, {0, 1}, 
				{1, 2}, {15, 5}, {1775, 8}};
		for (int[] c : cases) {
			int x = findLongestOneSeq(c[0]);
			boolean r = (c[1] == x);
			System.out.println(c[0] + ": " + x + ", " + c[1] + " " + r);
		}

	}

}
