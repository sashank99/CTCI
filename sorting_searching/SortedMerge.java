/**
* You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B.
* Write a method to merge B into A in sorted order.
*/
public class SortedMerge{

	public static void mergeSortedArrays(int[] A,int[] B,int aLastIndex){
		if(B.length==0) return;
		int bLastIndex=B.length-1;
		int arrLastIndex= A.length-1;
		while(arrLastIndex>=0){
			if(B[bLastIndex]>A[aLastIndex]){
				A[arrLastIndex]=B[bLastIndex];
				bLastIndex—;
			}else{
				A[arrLastIndex]=A[aLastIndex];
				aLastIndex—;
			}
			arrLastIndex—;
		}
	}
}