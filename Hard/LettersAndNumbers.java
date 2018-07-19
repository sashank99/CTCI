/**
* Given an array filled with letters and numbers, find the longest subarray with an equal number of letters and numbers
*/
public class LettersAndNumbers{

	public char[] findLongestSubArray(char[] array){
		if(array==null || array.length()==0) return array;
		
		int[] deltas= computeDeltaArray(array);

		int[] match= findLongestMatch(deltas);
		return Arrays.copyOfRange(array, match[0]+1, match[1]);
	}

	private int[] computeDeltaArray(char[] array){
		int[] deltas=new char[array.length];
		int letterCount=0;
		int numberCount=0;

		for(int i=0;i<array.length;i++){
			if(Character.isDigit(array[i]){
				numberCount++;
			}else{
				letterCount++;
			}
			deltas[i]= numberCount-letterCount;
		}
		return deltas;
	}

	private int[] findLongestMatch(int[] deltas){
		Map<Integer,Integer> map=new HashMap<>();
		map.put(0,-1);// if the array contains equal no of letters and numbers
		int[] max=new int[2];
		for(int i=0;i<deltas.length();i++){
			int match=map.get(deltas[i]);
			if(match ==null){
				map.put(deltas[i],i);
			}else{
				int distance= i-match;
				int longest=max[1]-max[0];
		 		if(distance>longest){
					max[0]=match;
					max[1]=i;
				}
			}
		}
		return max;
	}
}