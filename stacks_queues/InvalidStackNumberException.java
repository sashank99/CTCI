package stacks_queues;

public class InvalidStackNumberException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidStackNumberException(int stackNumber){
		super("Stack Number should be from 1 to "+stackNumber);
	}

}
