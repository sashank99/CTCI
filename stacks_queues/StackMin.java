package stacks_queues;

/**
* How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element?
* Push, pop and min should all operate in O(1) time.
*/
public class StackMin extends Stack<Integer>{

	private Stack<Integer> minStack=new Stack<>();
	
	@Override
	public void push(Integer data){
		if(data < min()){
			minStack.push(data);
		}
		super.push(data);
	}
	
	@Override
	public Integer pop(){
		Integer data=super.pop();
		if(data==min()){
			minStack.pop();
		}
		return data;
	} 
	
	public Integer min(){
		if(minStack.isEmpty()){
			return Integer.MAX_VALUE;
		}else{
			return minStack.peek();
		}
	}
	
	public static void main(String[] args) {
		StackMin minStack=new StackMin();
		minStack.push(1);
		minStack.push(100);
		minStack.push(20);
		
		minStack.pop();
		//minStack.pop();
		System.out.println(minStack.min());
	}
}
