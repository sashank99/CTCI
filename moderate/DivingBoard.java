package moderate;

import java.util.HashSet;
import java.util.Set;

/**
 * You are building a diving board by placing a bunch of planks of wood
 * end-to-end. There are two types of planks, one of length shorter and one of
 * length longer. You must use exactly K planks of wood. Write a method to
 * generate all possible lengths for the diving board.
 */
public class DivingBoard {

	public static Set<Integer> getAllLengths(int k, int shorter, int longer){
		
		Set<Integer> allLengths=new HashSet<>();
		for(int i=0;i<=k;i++){
			allLengths.add(shorter*(k-i)+longer *i);
		}
		return allLengths;
	}
	
	public static void main(String[] args) {
		int nSticks = 12;
		int shorter = 2;
		int longer = 3;

		System.out.println(getAllLengths(nSticks,shorter,longer));
		
	}
}
