package linked_lists;

import java.util.ArrayDeque;
/**
* Implement a function to check if a linked list is a palindrome
*
**/
public class PalindromeLinkedList{
	public static void main(String[] args) {
		Node node=new Node(1);
		node.next=new Node(2);
		node.next.next=new Node(3);
		node.next.next.next=new Node(3);
		node.next.next.next.next=new Node(2);
		node.next.next.next.next.next=new Node(1);
		System.out.println(node+" is palindrome "+isPalindrome(node));
	}

	public static boolean isPalindrome(Node node){
		
		ArrayDeque<Integer> stack=new ArrayDeque<>();
		Node slowPointer=node;
		Node fastPointer=node;
		
		while(fastPointer!=null){
			stack.add(slowPointer.val);
			
			if(fastPointer.next!=null){
				fastPointer=fastPointer.next.next;
			}else{
				break;
			}
			
			slowPointer=slowPointer.next;
		}
		if(stack.isEmpty()){
			return false;
		}
		
		while(slowPointer!=null){
			if(stack.removeLast()!=slowPointer.val){
				return false;
			}
			slowPointer=slowPointer.next;
		}
		return true;
	}

}
