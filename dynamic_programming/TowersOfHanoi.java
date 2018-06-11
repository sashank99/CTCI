package dynamic_programming;

import java.util.ArrayDeque;
import java.util.Deque;
/**
* In the classic problem of the towers of Hanoi, you have 3 towers and N disks of different sizes which can slide onto any tower.
* The puzzle starts with disks sorted in ascending order of size from top to bottom (i.e., each disk sits on the top of an even larger one).
* You have the following constraints:
* 1) Only one disk can be moved at a time.
* 2) A disk is slid off the top of one tower onto another tower.
* 3) A disk cannot be placed on top of a smaller disk
*/
public class TowersOfHanoi{

	public static void moveDisks(int n,Deque<Integer> src,Deque<Integer> buff,Deque<Integer> dest){
		if(n>0){
			moveDisks(n-1,src,dest,buff);
			dest.push(src.pop());
			moveDisks(n-1,buff,src,dest);
		}
	}
	
	public static void main(String[] args){
		Deque<Integer> src=new ArrayDeque<>();
		int n=20;
		for(int i=n;i>0;i--){
			src.push(i);
		}
		System.out.println(src);
		ArrayDeque<Integer> buff=new ArrayDeque<>();
		ArrayDeque<Integer> dest=new ArrayDeque<>();
		moveDisks(n,src,buff,dest);
		System.out.println(dest);
	}
}

