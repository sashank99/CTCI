package linked_lists;


/**
* Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
* DEFINITION
* Circular linked list: A (corrupt) linked in which a node's next pointer points to an earlier node, so as to make a loop in the linked list.
* Ex:
* Input: A -> B -> C -> D -> E -> C (the same C as earlier)
* OUTPUT: C
*/
public class LoopDetection{

	public static void main(String[] args){
		NodeLoop node1=new NodeLoop(1);
		NodeLoop node2=new NodeLoop(2);
		NodeLoop node3=new NodeLoop(3);
		NodeLoop node4=new NodeLoop(4);
		NodeLoop node5=new NodeLoop(5);
		NodeLoop node6=new NodeLoop(6);
		NodeLoop node7=new NodeLoop(7);
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		node4.next=node5;
		node5.next=node6;
		node6.next=node7;
		node6.next=node2;
		System.out.println(getCircularLinkedNode(node1));
		
		
	}
	
	public static NodeLoop getCircularLinkedNode(NodeLoop node){
		NodeLoop slowPointer=node;
		NodeLoop fastPointer=node;
		while(fastPointer!=null){
			slowPointer=slowPointer.next;
			if(fastPointer.next!=null){
				fastPointer=fastPointer.next.next;
			}else{
				return null;
			}
			if(slowPointer==fastPointer){
				break;
			}
		}
		if(fastPointer==null){
			return null;
		}
		slowPointer=node;
		while(slowPointer!=fastPointer){
			slowPointer=slowPointer.next;
			fastPointer=fastPointer.next;
		}
		return slowPointer;
	}

}

class NodeLoop{
	int val;
	NodeLoop next;
	NodeLoop(int val){
		this.val=val;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.val);
	}
		
}
