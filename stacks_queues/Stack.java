package stacks_queues;

import java.util.EmptyStackException;

public class Stack<T>{
	private static class StackNode<T>{
		public T data;
		private  StackNode<T> next;
		public StackNode(T data){
			this.data=data;
		}
		
		@Override
		public String toString() {
			if(next!=null){
				return this.data+ " -> "+next.toString();
			}else{
				return String.valueOf(this.data);
			}
		}
	}
	
	private StackNode<T> top;
	
	public void push(T data){
		StackNode<T> node=new StackNode<>(data);
		if(top==null){
		 top=node;
		}else{
			node.next=top;
			top=node;
		}
		
	}
	
	public T pop(){
		if(top==null) throw new EmptyStackException();
		T data=top.data;
		top=top.next;
		return data;
	}
	
	public T peek(){
		if(top==null) throw new EmptyStackException();
		return top.data;
	}
	
	public boolean isEmpty(){
		return top==null;
	}

	@Override
	public String toString() {
		if(top==null) return "";
		return top.toString();
	}
	
	
}
