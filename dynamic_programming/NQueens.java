package dynamic_programming;

import java.util.List;
import java.util.ArrayList;

/**
* Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board so that none of them share the same row, column, or diagonal.
* In this case, “diagonal” means all diagonals, not just the two that bisect the board.
*/
public class NQueens{
	private static int size=5;
	public static void arrangeQueens(int row, Integer[] cols, List<Integer[]> results){
		if(row == size){
			results.add(cols.clone());
		}else{
			for(int col=0;col<size;col++){
				if(isValid(row, col, cols)){
					cols[row]=col;
					arrangeQueens(row+1,cols,results);
				}
			}
		}
	}
	
	public static boolean isValid(int row1,int col1,Integer[] cols){
		for(int row2=0;row2<row1;row2++){
			int col2=cols[row2];
			//same column
			if(col2 == col1) return false;
			
			//Diagnoals same distance between 2 cols and 2 rows
			if( Math.abs(col2-col1)== (row1-row2)) return false;
		}
	
		return true;
	}

	public static void main(String[] args){
		List<Integer[]> results=new ArrayList<>();
		arrangeQueens(0,new Integer[size],results);
		System.out.println(results.size());
		for(Integer[] result:results){
			for(int row=0;row<size;row++){
				int col2=result[row];
				for(int col=0;col<size;col++){
					if(col2==col){
						System.out.print("Q");
					}else{
						System.out.print("X");
					}
					System.out.print("\t");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}
