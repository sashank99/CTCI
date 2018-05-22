package string_arrays;

/**
* There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character.
* Given two strings, write a function to check if they are one edit(or zero edits away)
*
*/
public class OneArray{

	public static void main(String[] args){
	
		String str1= "pale";
		String str2= "pale";
		
		System.out.println("is string:::"+str1+" one edit away of string:::"+str2+" ? "+checkOneEditAway(str1,str2));
	
	}
	
	private static boolean checkOneEditAway(String str1,String str2){
		if(str1.length()==str2.length()){
			return checkForEdits(str1,str2);
		}else if(str1.length()+1==str2.length()){
			return checkForDeletes(str2,str1);
		}else if(str1.length()==str2.length()+1){
			return checkForDeletes(str1,str2);
		}else{
			return false;
		}
	}
	
	private static boolean checkForEdits(String str1,String str2){
		int editCount=0;
		
		for(int i=0;i<str1.length();i++){
			if(str1.charAt(i)!= str2.charAt(i)){
				editCount++;
			}
			if(editCount>1){
				return false;
			}
		}
		return true;
	}
	
	private static boolean checkForDeletes(String str1,String str2){
		int i=0;
		int j=0;
		
		while(i<str1.length() && j<str2.length()){
			if(str1.charAt(i)!=str2.charAt(j)){
				i++;
			}else{
				i++;
				j++;
			}
		}
		return i-j<=1;
	}
}