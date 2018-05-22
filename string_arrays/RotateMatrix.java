package string_arrays;

import java.util.Scanner;
/**
* Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees.
* Can you do this in place?
*
**/
public class RotateMatrix{

	public static void main(String args[]){
		System.out.println("Please enter matrix size: ");
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		s.close();
		int[][] matrix= new int[n][n];
		int count=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				matrix[i][j]=count++;
			}
		}
		rotate90Degrees(matrix);
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(matrix[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public static void rotate90Degrees(int[][] matrix){
		int n=matrix.length;
		if(matrix.length<=1){
			return;
		}
		
		int i;
		int j;
		int temp1;
		int temp2;
		int last;
		for(int first=0;first<n/2;first++){
			i=first;
			j=n-1-i;
			last=j;
			while(i<last){
				temp1=matrix[first][i];
				matrix[first][i]=matrix[j][first];
				temp2=matrix[i][last];
				matrix[i][last]=temp1;
				temp1=matrix[last][j];
				matrix[last][j]=temp2;
				matrix[j][first]=temp1;
				i++;
				j--;
			}
		}
		
	}

}
