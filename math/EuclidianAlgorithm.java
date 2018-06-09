package math;
/**
 * Eficient algorithm for finding GCD
 *
 */
public class EuclidianAlgorithm {
	
	public static int gcd(int a, int b){
		if(a==0 || b==0) throw new NumberFormatException("O is not valid for GCD");
		if(a==b) return a;
		int max= Math.max(a, b);
		int min= max==a? b:a;
		int reminder;
		while((reminder=max%min)!=0){
			max=min;
			min=reminder;
		}
		return min;
		
	}
	
	public static void main(String[] args) {
		System.out.println(gcd(2,3));
	}

}
