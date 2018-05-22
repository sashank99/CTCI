package string_arrays;


/**
* Given a string, write a function to check if it is a perumatation of a palindrome. The palindrome does not need to b elimited to just dictionary words.
* Example
* Input: Tact Coa
* Output: true ( "taco cat","atco cta", etc.)
*/
public class PalindromePerumatation{

	public static void main(String[] args){
		String str="anana";
		System.out.println("Is string :::"+str+" a palindrome perumatation? "+isPermutationPalindrome(str));
	}
	
	public static boolean isPermutationPalindrome(String string){
		int[] count=new int[26];
		int a=Character.getNumericValue('a');
		int z=Character.getNumericValue('z');
		for(int i=0;i<string.length();i++){
			int val=Character.getNumericValue(Character.toLowerCase(string.charAt(i)));
			if(a<=val && val<=z){
				count[val-a]++;
			}
		}
		
		int oddCnt=0;
		for(int cnt:count){
			if(cnt%2==1){
				oddCnt++;
				if(oddCnt>1){
					return false;
				}
			}
		}
		
		return oddCnt<=1;
	}
}