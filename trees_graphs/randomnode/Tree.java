package trees_graphs.randomnode;

import java.util.Random;
/**
* You are implementing a binary search tree class from scratch, which, in addition to insert, find and delete, has a method getRandomNode() which returns a random node from the tree.
* All nodes should be equally likely to be chosen. Design and implement an algorithm for getRandomNode, and explain how would you implement the rest of the methods.
*/
public class Tree{

	private TreeNode root=null;
	private final Random random= new Random();
	
	public int getSize(){
		return root==null? 0: root.size();
	}
	
	public TreeNode getRandomNode(){
		if(root==null) return null;
		int i=random.nextInt(getSize());
		return root.getIthNode(i);
	}
	
	public void insertInOrder(int val){
		if(root ==null) root=new TreeNode(val);
		else root.insertInOrder(val);
	}
	
	/**
	* not working
	*/
	public boolean delete(int val){
		if(root ==null) return false;
		Result result=new Result(root);
		root.delete(val,result);
		root=result.getRoot();
		return result.found();
	}
	
	public static void main(String[] args) {
		int[] counts = new int[10];
		for (int i = 0; i < 1000000; i++) {
			Tree tree = new Tree();
			int[] array = {1, 0, 6, 2, 3, 9, 4, 5, 8, 7};
			for (int x : array) {
				tree.insertInOrder(x);
			}
			int d = tree.getRandomNode().getData();
			counts[d]++;
		}
		
		for (int i = 0; i < counts.length; i++) {
			System.out.println(i + ": " + counts[i]);
		}
	}

}

/**
* This class is used for traking delete node status and if in case root is deleted it helps with providing new root;
*/
class Result{
	private boolean foundNode;
	private TreeNode root;
	
	public Result(TreeNode root){
		this.root=root;
	}
	
	public void setRoot(TreeNode root){
		this.root=root;
	}
	
	public void setFound(boolean foundNode){
		this.foundNode=foundNode;
	}
	
	public TreeNode getRoot(){
		return root;
	}
	
	public boolean found(){
		return foundNode;
	}

}

class TreeNode{
	private int size;
	private int data;
	private TreeNode left;
	private TreeNode right;
	
	public TreeNode(int data){
		this.data=data;
		size=1;
	}
	
	public int getData(){
		return data;
	}
	
	public void insertInOrder(int value){
		if(value<data){
			if(left==null) left=new TreeNode(value);
			else left.insertInOrder(value);
		}else{
			if(right==null) right=new TreeNode(value);
			else right.insertInOrder(value);
		}
		size++;
	}
	
	public int size(){
		return size;
	}
	
	public TreeNode find(int value){
		if(value == data){
			return this;
		}if(value<data){
			return left!=null? left.find(value): null;
		}else{
			return right!=null? right.find(value): null;
		}
	}
	
	public TreeNode getIthNode(int i){
		int leftSize = left==null? 0: left.size();
		if(i==leftSize){
			return this;
		}else if(i<leftSize){
			return left.getIthNode(i);
		}else{
			return right.getIthNode(i-(leftSize+1));
		}
	
	}
	
	public void delete(int val,Result result){
		if(data==val){
			shuffleNodes();
			result.setFound(true);
			if(data==result.getRoot().getData()){
				result.setRoot(this.right);
			}
			return;
			
		}else if(data<val){
			if(left!=null) left.delete(val,result);
			else return;
		}else{
			if(right!=null) right.delete(val,result);
			else return;
		}
	}
	
	/**
	* problem with size
	*/
	private void shuffleNodes(){
		//TODO
		/*if(root.left==null && root.right==null){
			root = null;
		}else if(root.left==null){
			root=root.right;
		}else if(root.right==null){
			root=root.left;
		}else{
			TreeNode temp=root.left;
			root=root.right;
			TreeNode leftPointer=root;
			while(leftPointer.left!=null){
				leftPointer=leftPointer.left;
			}
			leftPointer.left.left=temp;
		}
		size--;*/
	}
	
}
