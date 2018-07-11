package moderate;

/**
 * Write a method that finds the maximum of two numbers. You should not use
 * if-else or any other comparison operator.
 */
public class NumberMax {

	/*public static int findMax(int a, int b) {
		int aSign = getSign(a - b);
		int bSign = flip(aSign);
		return a * aSign + b * bSign;
	}*/
	
	/**
	 * For overflow condition
	 */
	public static int findMax(int a,int b){
		int c= a-b;
		int aSign= getSign(a);
		int bSign= getSign(b);
		int cSign= getSign(c);// overflow case
		
		int useASign= aSign ^ bSign ;// if a and b are of same sign
		int useCSign = flip(aSign ^ bSign);// if and b are of different sign.
		int k= aSign *useASign+ cSign*useCSign;
		int q=flip(k);
		return a*k+b*q;
	}

	/**
	 * Return 1 if a is positive else 0.
	 */
	public static int getSign(int a) {
		return flip((a >> 31) & 1);
	}

	public static int flip(int a) {
		return a ^ 1;
	}
	
	public static void main(String[] args) {
		System.out.println(findMax(-19,-10));
	}
}
