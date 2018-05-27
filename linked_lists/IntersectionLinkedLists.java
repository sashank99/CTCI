package linked_lists;

/**
* Given two singly linked lists, determine if the two lists intersect. Return the intersecting node.
* Note that the intersection is defined based on reference, not value. 
* That is, the kth node of the first linked list is the exact same node (by reference) as the jth node node second linked list, then they are intersecting
*
*/
public class IntersectionLinkedLists{
	
	public static void main(String[] args) {
		Node node1=new Node(2);
		node1.next=new Node(3);
		node1.next.next=new Node(4);
		node1.next.next.next=new Node(5);
		Node node2=new Node(2);
		node2.next=new Node(6);
		node2.next.next=node1.next;
		System.out.println(node1);
		System.out.println(node2);
		System.out.println(getIntersection(node1,node2));
	}

	public static Node getIntersection(Node a,Node b){
		int aLen=0;
		Node temp=a;
		Node aLastNode=null;
		while(temp!=null){
			aLastNode=temp;
			temp=temp.next;
			aLen++;
		}
		
		int bLen=0;
		temp=b;
		Node bLastNode=null;
		while(temp!=null){
			bLastNode=temp;
			temp=temp.next;
			bLen++;
		}
		
		if( aLastNode!=null && aLastNode!=bLastNode){
			return null;
		}
		
		int diff=aLen-bLen;
		Node aPointerNode=a;
		Node bPointerNode=b;
		
		if(diff<0){
			bPointerNode=moveNodePointer(bPointerNode,Math.abs(diff));
		}else{
			aPointerNode=moveNodePointer(aPointerNode,Math.abs(diff));
		}
		
		while(aPointerNode!=bPointerNode){
			aPointerNode=aPointerNode.next;
			bPointerNode=bPointerNode.next;
		}
		return aPointerNode;
	}
	
	public static Node moveNodePointer(Node node, int moveCnt){
		for(int i=0;i<moveCnt;i++){
			node=node.next;
		}
		return node;
	}

}
