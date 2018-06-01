package stacks_queues;

import java.util.EmptyStackException;

/**
 * Describe how you could use a single array to implement three stacks
 */
public class NStackArray<T> {

	private static final int INITIAL_MAX_CAPACITY=10;
	private int maxStackElements;
	private Integer stackCount;
	private T[] stackArray;
	private int[] maxElemntCounter;
	private int[] stackHeadPointers;
	private int[] stacktailPointers;

	/**
	 *  This constructor helps to create stacks of n with a single Array
	 * @param n - no of stacks
	 */
	public NStackArray(Integer n) {
		this.stackCount = n;
		maxElemntCounter = new int[stackCount];
		stackHeadPointers = new int[stackCount];
		stacktailPointers = new int[stackCount];

		increaseArrayCapacity();
	}
	
	/**
	 * This method helps to set initial stack pointers
	 * For 3 Stacks, stack 1 tail points to 9, stack 2 tail points to 19 and stack 3 tail points to 29;
	 * similary heads of stack 1, stack 2 and stack 3 points to 10,20,30 respectively.
	 */
	private void updateStackInitialPointers() {
		int temp = maxStackElements;
		for (int i = 0; i < stackCount; i++) {
			stacktailPointers[i] = temp - 1;
			stackHeadPointers[i] = temp;
			temp += maxStackElements;
		}

	}
	
	/**
	 * This method helps to increase array capacity, if any one of the stack reach max capacity.
	 * it doubles the capacity.
	 * The initial array will be created with allocation capacity of 10 element of each stack.
	 * For 3 stacks, if any one stack reaches initial max capacity. Then this doubles stack capacity of all three stacks and copies old array elements to new array.
	 * 
	 */
	private void increaseArrayCapacity() {
		if (maxStackElements == 0) {
			maxStackElements = INITIAL_MAX_CAPACITY;
			updateStackInitialPointers();
		} else {
			maxStackElements *= 2;
		}

		@SuppressWarnings("unchecked")
		T[] tempArray = (T[])new Object[stackCount * maxStackElements];
		int stackLastIndex;
		if(maxStackElements!=0)
		for (int p = 0; p < stackCount; p++) {
			stackLastIndex=(p+1)*maxStackElements;
			for (int i = stacktailPointers[p]; i >= stackHeadPointers[p]; i--) {
				tempArray[--stackLastIndex] = stackArray[i];
			}
			stackHeadPointers[p] = (p+1)*maxStackElements - (stacktailPointers[p]-stackHeadPointers[p]+1);
			stacktailPointers[p] = (p+1)*maxStackElements-1;
		}
		stackArray = tempArray;
	}

	/**
	 * If method helps to check validness of a stack number.
	 * For a 3 stack Array, stack no should be either 1,2, 3
	 * @param stackNo
	 */
	private void checkStackNumber(int stackNo) {
		if (stackNo <= 0 || stackNo > stackCount)
			throw new InvalidStackNumberException(stackCount);
	}

	/**
	 * pushes the element to head of the stack.
	 * @param stackNo
	 * @param value
	 */
	public void push(int stackNo, T value) {
		checkStackNumber(stackNo);
		stackNo = stackNo - 1;
		if (maxElemntCounter[stackNo] == maxStackElements)
			increaseArrayCapacity();
		stackHeadPointers[stackNo]=stackHeadPointers[stackNo]-1;
		stackArray[stackHeadPointers[stackNo]] = value;
		maxElemntCounter[stackNo]++;
	}

	/**
	 * removes the element from head of the stack
	 * @param stackNo
	 * @return
	 */
	public T pop(int stackNo) {
		checkStackNumber(stackNo);
		stackNo = stackNo - 1;
		if (stackHeadPointers[stackNo] > stacktailPointers[stackNo])
			throw new EmptyStackException();
		T element = stackArray[stackHeadPointers[stackNo]];
		stackArray[stackHeadPointers[stackNo]] = null;
		stackHeadPointers[stackNo]++;
		maxElemntCounter[stackNo]--;
		return element;
	}

	/**
	 * retrieves the element from head of the stack but does not remove
	 * @param stackNo
	 * @return
	 */
	public T peek(int stackNo) {
		checkStackNumber(stackNo);
		stackNo = stackNo - 1;
		if (stackHeadPointers[stackNo] > stacktailPointers[stackNo])
			throw new EmptyStackException();
		return stackArray[stackHeadPointers[stackNo]];
	}
	
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder("[");
		
		for(int i=0;i<stackCount;i++){
			if(i!=0) builder.append(", ");
			builder.append("[");
			for(int j=stacktailPointers[i];j>=stackHeadPointers[i];j--){
				builder.append(stackArray[j]).append(",");
				
			}
			if(builder.charAt(builder.length()-1)==',') builder.setLength(builder.length()-1);
			builder.append("]");
		}
		builder.append("]");
		return builder.toString();
	}

}
