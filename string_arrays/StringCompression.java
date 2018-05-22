package string_arrays;


/**
* Implement a method to perform basic string compression using the counts of repeated characters.
* For Ex, the string aabcccccaaa would become a2b1c5a3.
* If the compressed string would not become snaller than the original string, your method should return original string.
*
*/
public class StringCompression{

	public static void main(String[] args){
		String string="abcdefgh";
		System.out.println("compressed version of string:::"+string+" is ::: "+compressString(string));
	}
	
	public static String compressString(String string){
		if(string.length()==0){
			return string;
		}
		StringBuilder builder=new StringBuilder();
		
		char runningChar=' ';
		int runningCount=0;
		for(int i=0;i<string.length();i++){
			if(string.charAt(i)==runningChar){
				runningCount++;
			}else{
				if(runningChar!=' '){
					builder.append(runningChar).append(runningCount);
				}
				runningChar=string.charAt(i);
				runningCount=1;
			}
		}
		builder.append(runningChar).append(runningCount);
		
		if(builder.length()>string.length()){
			return string;
		}
		
		return builder.toString();
	}

}
