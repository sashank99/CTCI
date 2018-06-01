package stacks_queues;

/**
* Write a program to sort a stack such that the smallest items are on the top. 
* You can use an additional temporary stack, but may not copy elements into any other datat structure ( such as an array).
* The stack supports the following operations: push, pop, peek and isEmpty
*/
public class SortStack{

	public static void sort(Stack<Integer> stack){
		Stack<Integer> sortedStack=new Stack<>();
		
		while(!stack.isEmpty()){
			Integer element=stack.pop();
			while(!sortedStack.isEmpty() && element<sortedStack.peek()){
					stack.push(sortedStack.pop());
			}
			sortedStack.push(element);
		}
		while(!sortedStack.isEmpty()){
			stack.push(sortedStack.pop());
		}
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack=new Stack<>();
		stack.push(5);
		stack.push(1);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		
		//System.out.println(stack.pop());
		System.out.println(stack);
		sort(stack);
		//System.out.println(stack.pop());
		System.out.println(stack);
	}

}
