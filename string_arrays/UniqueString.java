package string_arrays;
/**
* Implement an algorithm to determine if a string has all unique characters or not
*
**/
public class UniqueString{

	public static void main(String[] args){
		String str="abcdefghijklmnopqrstuvwxyzA";
		System.out.println("String:::"+str+" is "+isUnique(str));
	}
	
	private static boolean isUnique(String str){
	
		if(str.length()>128){
			return false;
		}	
		boolean[] chars=new boolean[128];
		for(int i=0;i<str.length();i++){
			int val=str.charAt(i);
			if(chars[val]){
				return false;
			}
			chars[val]=true;
		}
		
		return true;
	}
}
