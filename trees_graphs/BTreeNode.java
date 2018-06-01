package trees_graphs;

/**
 *  Binary Tree Node
 *
 */
public class BTreeNode{
	int val;
	BTreeNode left;
	BTreeNode right;
	
	public BTreeNode(int val){
		this.val=val;
	}
	
	public BTreeNode find(int findVal){
		if(findVal<this.val){
			if(left==null) return null;
			else return left.find(findVal);
		}else if(findVal>this.val){
			if(right==null) return null;
			else return right.find(findVal);
		}else{
			return this;
		}
		
		
	}
	
	@Override
	public String toString(){
		StringBuilder builder=new StringBuilder();
		if(left!=null){
			builder.append(this.val).append(" left:").append(left.toString());
		}
		if(right!=null){
			builder.append(this.val).append(" right:").append(right.toString());
		}
		
		if(left==null && right ==null){
			builder.append(this.val);
		}
		return builder.toString();
	}
}
