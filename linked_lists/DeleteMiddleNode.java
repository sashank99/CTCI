package linked_lists;

/**
*
* Implement an algorithm to delete a node in the middle (i.e., any node but the first and last node, not necessarily the exact middle) of a singly linked list,
* given only access to that node
* Eg: Input: Node c from Linked list a -> b -> c-> d-> e -> f
* Returned: nothing returned, but linked list changes to a -> b -> d -> e -> f
*/
public class DeleteMiddleNode{

	public static void main(String[] args){
		NodeS a= new NodeS('a');
		NodeS b= new NodeS('b'); 
		NodeS c= new NodeS('c'); 
		NodeS d= new NodeS('d'); 
		NodeS e= new NodeS('e'); 
		NodeS f= new NodeS('f'); 
		a.next=b;
		b.next=c;
		c.next=d;
		d.next=e;
		e.next=f;
		System.out.println(a);
		deleteNode(d);
		System.out.println(a);
	}
	
	private static void deleteNode(NodeS c){
	
		if(c.next!=null){
			c.val=c.next.val;
			c.next=c.next.next;
		}
	}

}

class NodeS {
	char val;
	NodeS next;
	NodeS(char val){
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

