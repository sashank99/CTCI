package Hard;

/**
* Write a function that adds two numbers. You should not use + or any arithmetic operators
*/
public class AddWithoutPlus{

	public static int add(int a, int b){
		if(b==0) return a;
		int sum= a^b;
		int carry= (a & b) << 1;
		return add(sum,carry);
	}
	
	public static void main(String[] args) {
		System.out.println(add(4,2096891011));
	}
}
