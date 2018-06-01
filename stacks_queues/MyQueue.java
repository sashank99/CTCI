package stacks_queues;

/**
* Implement a MyQueue class which implements a queue using two stacks
*/
public class MyQueue<T>{

	private Stack<T> stack1;
	private Stack<T> stack2;
	
	public MyQueue(){
		stack1=new Stack<>();
		stack2=new Stack<>();
	}
	
	public void add(T data){
		stack1.push(data);
	}
	
	public T remove(){
		if(stack2.isEmpty()){
			shiftStackElements();
		}
		return stack2.pop();
	}
	
	private void shiftStackElements(){
		while(!stack1.isEmpty()){
			stack2.push(stack1.pop());
		}
	}
	
	public T peek(){
		if(stack2.isEmpty()){
			shiftStackElements();
		}
		return stack2.peek();
	}
	
	public static void main(String[] args) {
		MyQueue<Integer> queue= new MyQueue<>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		System.out.println(queue.remove());
		queue.add(4);
		queue.add(5);
		System.out.println(queue.remove());
		queue.add(6);
		queue.add(7);
		System.out.println(queue.remove());
		
	}
	
}
