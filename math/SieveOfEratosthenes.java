package math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Efficient algorithm for finding list of prime numbers
 * O( n log log n)
 *
 */
public class SieveOfEratosthenes {

	public static List<Integer> findPrimeNos(int n){
		boolean[] flags=new boolean[n+1];
		Arrays.fill(flags, true);
		int sqrt=(int)Math.sqrt(n);
		int prime=2;
		while(prime<=sqrt){
			if(flags[prime]){
				for(int j=prime*prime; j<=n;j+=prime){
					flags[j]=false;
				}
			}
			prime=findNextPrime(flags,prime);
		}
		List<Integer> list=new ArrayList<>();
		for(int i=2;i<flags.length;i++){
			if(flags[i]) list.add(i);
		}
		
		return list;
		
	}

	private static int findNextPrime(boolean[] flags, int prime) {
		int next=prime+1;
		while(next<flags.length && !flags[next]) next++;
		return next;
	}
	
	public static void main(String[] args) {
		List<Integer> list=findPrimeNos(100000000);
		//System.out.println(list);
		System.out.println(list.size());
	}
}
