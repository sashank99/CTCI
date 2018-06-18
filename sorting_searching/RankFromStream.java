/**
* Imagine you are reading in stream of integers. Periodically, you wish to be able to look up the rank of a number x (the number of values less than or equal to x).
* Implement the data structures and algorithms to support these operations. That is, implement the method track(int x), which is called when each number is generated, 
* and the method getRankOfNumber(int x), which returns the number of values less than or equal to x (not including x itself).
* Example:
* Stream (in order of appearance): 5,1,4,4,5,9,7,13,3
*/
public class RankFromStream{
}

class RankNode{
	int data;
	RankNode left;
	RankNode right;
	int leftSize;
	
	public RankNode(int data){
		this.data=data;
	}
	
	public void insert(int value){
		if(value <= data){
			if(left ==null) left=new RankNode(value);
			else left.insert(value);
			left++;
		}else{
			if(right==null) right=new RankNode(value);
			else right.insert(value);
		}
	}

	public int getRank(int value){
		if(value== data) return leftSize;
		if(value < data){
			if(left==null) return -1;
			else return left.getRank(value);
		}else{
			int rightRank= right==null? -1:right.getRank(value);
			if(rightRank==-1) return -1;
			return leftSize + 1+ rightRank;
		}
	}

}