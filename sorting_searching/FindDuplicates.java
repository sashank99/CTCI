package sorting_searching;

import java.util.Arrays;

import CtCILibrary.AssortedMethods;

/**
 * You have an array with all the numbers from 1 to N, where N is at most
 * 32,000. The array may have duplicate entries and you do not know what N is.
 * With only 4 kilobytes of memory available, how would you print all duplicate
 * elements in the array?
 */
public class FindDuplicates {

	public static void printDuplicates(int[] arr){
		
		byte[] bitVector=new byte[32000/8];// setting 32000 bits and is less than 4 KB ( 4* 1024 * 8 bits)
		for(int element: arr){
			int index= (element-1)/8;
			int mask= 1 << ((element-1)%8);
			if( (bitVector[index] & mask) ==0){
				bitVector[index] |= mask;
			}else{
				System.out.println(element);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] array = AssortedMethods.randomArray(100, 1, 100);
		Arrays.sort(array);
		System.out.println(AssortedMethods.arrayToString(array));
		printDuplicates(array);
	}
}
