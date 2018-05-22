package string_arrays;

import java.util.Set;
import java.util.HashSet;

/**
* Write an algorithm such that if an element in an MXN matrix is 0, its entire row and column are set to 0.
*
*/
public class ZeroMatrix{

	public static void main(String[] args){
	
		int[][] matrix={{0,1,2,3},{3,0,5,8},{6,7,9,10}};
		zeroMatrix(matrix);
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[i].length;j++){
				System.out.print(matrix[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public static void zeroMatrix(int[][] matrix){
	
		Set<Integer> rowSet=new HashSet<>();
		Set<Integer> colSet=new HashSet<>();
		
		for(int i=0;i<matrix.length;i++){
		
			for(int j=0;j<matrix[i].length;j++){
				if(matrix[i][j]==0){
					rowSet.add(i);
					colSet.add(j);
				}
				
			}
		}
		
		for(int row: rowSet){
			for(int i=0;i<matrix[row].length;i++){
				matrix[row][i]=0;
			}
		}
		
		for(int col:colSet){
			for(int i=0;i<matrix.length;i++){
				matrix[i][col]=0;
			}
		}
	
	}

}