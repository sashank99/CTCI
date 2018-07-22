package Hard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Numbers are randomly generated and passed to a method. Write a method to find
 * and maintain the median value as new values are generated.
 */
public class ContinuousMedian {
	private static PriorityQueue<Integer> minHeap = new PriorityQueue<>(new MinHeapComparator());
	private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());

	public static double getMedian() {
		if (maxHeap.isEmpty())
			return 0;
		if (maxHeap.size() == minHeap.size()) {
			return (maxHeap.peek() + minHeap.peek()) / 2;
		} else {
			return maxHeap.peek();
		}
	}

	public static void addNewNumber(int randomNumber) {
		if (maxHeap.size() == minHeap.size()) {
			if (minHeap.peek() != null && randomNumber > minHeap.peek()) {
				maxHeap.offer(minHeap.poll());
				minHeap.offer(randomNumber);
			} else {
				maxHeap.offer(randomNumber);
			}
		} else {
			if (randomNumber < maxHeap.peek()) {
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(randomNumber);
			} else {
				minHeap.offer(randomNumber);
			}

		}
	}

	public static void main(String[] args) {
		int arrSize=19;
		Random random=new Random();
		for(int i=0;i<arrSize;i++){
			addNewNumber(random.nextInt(arrSize));
			System.out.println("minHeap:::"+minHeap);
			System.out.println("maxHeap:::"+maxHeap);
			System.out.println("median:::"+getMedian());
		}
		
	}
}

class MaxHeapComparator implements Comparator<Integer> {
	public int compare(Integer a, Integer b) {
		return b - a;
	}
}

class MinHeapComparator implements Comparator<Integer> {

	public int compare(Integer a, Integer b) {
		return a - b;
	}
}
