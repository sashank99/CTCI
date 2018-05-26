package linked_lists;
/**
* Sum Lists: You have two numbers represented by a linked list, whereh each node contains a single digit. 
* The digits are stored in reverse order, such that 1's digitis at the head of the list. 
* Write a function that ads the two numbers and  returns the sum as linked list.
* Ex:
* Input: (7 -> 1-> 6)+ (5 -> 9 -> 2). That is, 617 +295
* Output: 2 -> 1 -> 9. That is 912.
* Follow Up
* Suppose the digits are stored in forward order. Repeat the above problem.
* Ex:
* Input: ( 6 -> 1 -> 7 )+ ( 2-> 9-> 5). That is, 617 +295
* Output: 9 -> 1 -> 2. That is 912
*/
public class SumLists{

	public static void main(String[] args){
		Node node1=new Node(7);
		node1.next=new Node(1);
		node1.next.next=new Node(6);
		
		Node node2=new Node(5);
		node2.next=new Node(9);
		node2.next.next=new Node(3);
		System.out.println("node1:::"+ node1);
		System.out.println("node2:::"+ node2);
		System.out.println(reverseOrderListSum(node1,node2));
		
		/**
		 * Forward oder sum list
		 */
		Node node3=new Node(6);
		node3.next=new Node(1);
		node3.next.next=new Node(7);
		
		Node node4=new Node(2);
		node4.next=new Node(9);
		node4.next.next=new Node(5);
		node4.next.next.next=new Node(7);
		System.out.println("****Forward order sum****");
		System.out.println("node3:::"+ node3);
		System.out.println("node4:::"+ node4);
		System.out.println(forwardOrderListSum(node3,node4));
	}
	
	public static Node reverseOrderListSum(Node node1, Node node2){
		Node finalSumNode=null;
		Node referenceSumNode=null;
		int carryOn=0;
		
		while(node1!=null || node2!=null || carryOn!=0){
			int a=0;
			int b=0;
			if(node1!=null){
				a=node1.val;
				node1=node1.next;
			}
			if(node2!=null){
				b=node2.val;
				node2=node2.next;
			}
			int sum= a+b+carryOn;
			carryOn=sum/10;
			int val= sum%10;
			if(finalSumNode==null){
				finalSumNode=new Node(val);
				referenceSumNode=finalSumNode;
			}else{
				finalSumNode.next =new Node(val);
				finalSumNode=finalSumNode.next;
			}
			
		}
		return referenceSumNode;
	}
	
	public static Node forwardOrderListSum(Node node1, Node node2){
		int node1Len=0;
		int node2Len=0;
		Node tempNode=node1;
		while(tempNode!=null){
			node1Len++;
			tempNode=tempNode.next;
		}
		tempNode=node2;
		while(tempNode!=null){
			node2Len++;
			tempNode=tempNode.next;
		}
		if(node1Len>node2Len){
			node2=fillZerosAt(node2,node1Len-node2Len);
		}else if(node2Len>node1Len){
			node1=fillZerosAt(node1,node2Len-node1Len);
		}
		Node sumNode=new Node(0);
		Integer carryOn=getCarryOn(node1,node2,sumNode);
		if(carryOn==null){
			return null;
		}else if(carryOn==0){
			return sumNode;
		}else{
			Node carryOnNode=new Node(carryOn);
			carryOnNode.next=sumNode;
			return carryOnNode;
		}
		
	}
	
	private static Node fillZerosAt(Node actualNode, int count) {
		Node node=null;
		Node nodePointer=null;
		for(int i=0;i<count;i++){
			if(node==null){
				node=new Node(0);
				nodePointer=node;
			}else{
				node.next=new Node(0);
				node=node.next;
			}
			
		}
		if(node!=null){
			node.next=actualNode;
			return nodePointer;
		}else{
			return actualNode;
		}
		
	}

	public static Integer getCarryOn(Node node1,Node node2, Node sumNode){
		
		if(node1==null && node2==null){
			return null;
		}
		int a=0;
		int b=0;
		a=node1.val;
		node1=node1.next;
		b=node2.val;
		node2=node2.next;
		Node nextNode=new Node(0);
		sumNode.next=nextNode;
		Integer carryOn=getCarryOn(node1,node2,sumNode.next);
		if(carryOn==null){
			sumNode.next=null;
			carryOn=0;
		}
		int sum=a+b+carryOn;
		sumNode.val=sum%10;
		return sum/10;
	}
	
}
