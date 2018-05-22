package linked_lists;

import java.util.Set;
import java.util.HashSet;

/**
* Write code to removed duplicates from an unsorted linked list
* Follow Up
* How would you solve this problem if a temporary buffer is not allowed?
*
*/
public class RemoveDups{

	public static void main(String[] args){
		Node head=new Node(1);
		head.next=new Node(2);
		head.next.next=new Node(1);
		head.next.next.next=new Node(4);
		head.next.next.next.next=new Node(2);
		System.out.println(head);
		removeDuplicates(head);
		//removeDuplicatesWithoutTempBuff(head);
		System.out.println(head);
	}
	
	private static void removeDuplicates(Node head){
		Set<Integer> set=new HashSet<>();
		set.add(head.val);
		
		while(head.next!=null){
			if(set.contains(head.next.val)){
				head.next=head.next.next;
				continue;
			}
			set.add(head.next.val);
			head=head.next;
		}
		
	}
	
	private static void removeDuplicatesWithoutTempBuff(Node head){
		
		
		while(head!=null){
			Node current=head;
			while(current.next!=null){
				if(head.val==current.next.val){
					current.next=current.next.next;
				}else{
					current=current.next;
				}
			}
			head=head.next;
		}
		
	}

}

class Node{
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
