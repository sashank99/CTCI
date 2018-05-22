package string_arrays;

/**
* Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficinet space at the end to hold addition characters,
* and that you are given true length of the string.( use char array in java)
*
**/
public class URLify{

	public static void main(String[] args){
		String input="Mr John Smith    ";
		System.out.println("input:::"+replaceSpaces(input));
	}
	
	private static String replaceSpaces(String input){
	
		boolean foundEnd=false;
		int endIndex=input.length()-1;
		char[] chars=input.toCharArray();
		for(int i=input.length()-1;i>=0;i--){
			if(chars[i] !=' '){
				foundEnd=true;
				chars[endIndex--]=chars[i];
			}else if(foundEnd){
				chars[endIndex--]='0';
				chars[endIndex--]='2';
				chars[endIndex--]='%';
			}
		}
		return String.valueOf(chars);
	}
	
}