package linked_lists;


/**
* Implement an algorithm to find the kth to last element of a singly linked list.
*
*/
public class FindKthElementToLast{

	public static void main(String[] args){
		Node head=new Node(1);
		head.next=new Node(2);
		head.next.next=new Node(5);
		head.next.next.next=new Node(4);
		head.next.next.next.next=new Node(3);
		head.next.next.next.next.next=new Node(7);
		int k=2;
		Node kToLoat=findKToLast(head,k);
		System.out.println(kToLoat.val);
	
	}
	
	public static Node findKToLast(Node head,int k){
	
		
		Node secondPt=head;
		for(int i=1;i<k && secondPt!=null ;i++){
			secondPt=secondPt.next;
		}
		
		Node firstPt=null;
		while(secondPt!=null){
			secondPt=secondPt.next;
			if(firstPt==null){
				firstPt=head;
			}
			else{
				firstPt=firstPt.next;
			}
				
			
		}
		
		return firstPt;
	}

}

