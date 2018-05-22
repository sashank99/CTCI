package string_arrays;


/**
* Assume you have a method isSubstring which checks if one word is a substring of another. 
* Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring
* (e.g "waterbottle" is a rotation of "terbottlewa")
*/
public class StringRotation{

	public static void main(String[] args){
		String str1="waterbottle";
		String str2="erbottlewat";
		System.out.println(" is String 1: "+str1 +" rotation of String 2:"+str2+" ? "+isRotation(str1,str2));
	}

	public static boolean isRotation(String str1,String str2){
		
		if(str1.length()==str2.length() && str1.length()!=0){
			str1=str1+str1;
			return isSubString(str1,str2);
		}
		
		return false;
	}

	public static boolean isSubString(String str1, String str2){
		return str1.indexOf(str2)!=-1;
	}
}
