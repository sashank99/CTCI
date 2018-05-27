package linked_lists;

public class Node{
	int val;
	Node next;
	Node(int val){
		this.val=val;
	}
		
	@Override
	public String toString() {
		if(next!=null){
			return this.val+ " -> "+next.toString();
		}else{
			return String.valueOf(this.val);
		}
	}
}
