package stacks_queues;

import java.util.List;
import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Imagine a (literal) stack of plates. If the stack gets too high, it might
 * topple. Therefore, in real life, we would likely start a new stack when the
 * previous stack exceeds some threshold. Implement a data structure SetOfStacks
 * that mimics this. SetOfStacks should be composed of several stacks and should
 * create a new stack once the previous one exceeds capacity. SetOfStacks.push()
 * and SetOfStacks.pop() should behave identically to a single stack ( that is,
 * pop() should return the same values as it would if there just a single
 * stack). FOLLOW UP Implement a function popAt(int index) which performs a pop
 * operation on a specific sub-stack.
 */
public class StackOfPlates<T> {

	private static final int THRESHOLD_LIMIT = 3;
	private List<StackWithSize<T>> stackList = new ArrayList<>();

	public void push(T data) {
		StackWithSize<T> stack = getLastitem();
		if (stack == null || stack.getSize() == THRESHOLD_LIMIT) {
			stack = new StackWithSize<>();
			stackList.add(stack);
		}
		stack.push(data);
	}

	public T pop() {
		StackWithSize<T> stack = getLastitem();
		if (stack == null || (stack.getSize() == 0 && stackList.size() == 1))
			throw new EmptyStackException();
		if (stack.getSize() == 0) {
			
			
			stack = getAvailableStack();
		}
		return stack.pop();
	}
	
	private StackWithSize<T> getAvailableStack(){
		
		while(!stackList.isEmpty() && stackList.get(stackList.size() - 1).getSize()==0){
			stackList.remove(stackList.size() - 1);
		}
		if(stackList.isEmpty()) throw new EmptyStackException();
		else return stackList.get(stackList.size() - 1);
	}

	public T popAtIndex(int index) {
		if (0 <= index && index <= stackList.size()) {
			return stackList.get(index).pop();
		} else {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	private StackWithSize<T> getLastitem() {
		StackWithSize<T> stack;
		if (stackList.isEmpty()) {
			stack = null;
		} else {
			stack = stackList.get(stackList.size() - 1);

		}
		return stack;
	}
	
	
	
	@Override
	public String toString() {
		return  stackList.toString();
	}

	public static void main(String[] args){
		StackOfPlates<Integer> stack=new StackOfPlates<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		System.out.println(stack);
		stack.popAtIndex(1);
		stack.popAtIndex(1);
		stack.popAtIndex(1);
		System.out.println(stack);
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
		System.out.println(stack);
		
	}

}

class StackWithSize<T> extends Stack<T> {
	private int size;

	public int getSize() {
		return size;
	}

	@Override
	public void push(T data) {
		size++;
		super.push(data);
	}

	@Override
	public T pop() {
		if (!super.isEmpty())
			size--;
		return super.pop();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
