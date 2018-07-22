package Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given two arrays, one shorter (with all distinct elements) and one
 * longer. Find the shortest subarray on the longer array that contains all the
 * elements in the shorter array. The items can appear in any order. Ex: Input:
 * {1,5,9} {7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7}
 * 
 * Output: [7,10] (indexes of shortest subarray)
 */
public class ShortestSuperSeq {

	public static Range getShorterSeq(int[] big, int[] small) {
		Map<Integer, Queue<Integer>> map = new HashMap<>();
		for (int i = 0; i < small.length; i++) {
			Queue<Integer> queue = new LinkedList<>();
			map.put(small[i], queue);
		}

		for (int i = 0; i < big.length; i++) {
			Queue<Integer> queue = map.get(big[i]);
			if (queue != null) {
				queue.add(i);
			}
		}

		List<Queue<Integer>> locations = new ArrayList<>();
			
		locations.addAll(map.values());
		if (locations.size() != small.length){
			return null;
		}
		return getShortestClosure(locations);

	}

	public static Range getShortestClosure(List<Queue<Integer>> lists) {
		PriorityQueue<HeapNode> minHeap = new PriorityQueue<>();
		int max = Integer.MIN_VALUE;

		/* Insert min element from each list */
		for (int i = 0; i < lists.size(); i++) {
			int head = lists.get(i).remove();
			minHeap.add(new HeapNode(head, i));
			max = Math.max(max, head);
		}

		int min = minHeap.peek().locationWithinList;
		int bestRangeMin = min;
		int bestRangeMax = max;

		while (true) {
			HeapNode n = minHeap.poll();
			Queue<Integer> list = lists.get(n.listId);

			/* Compare range with best range */
			min = n.locationWithinList;
			if (max - min < bestRangeMax - bestRangeMin) {
				bestRangeMin = min;
				bestRangeMax = max;
			}
			if (list.isEmpty())
				break;

			/* Add new head of list to heap */
			n.locationWithinList = list.remove();
			minHeap.add(n);
			max = Math.max(max, n.locationWithinList);
		}

		return new Range(bestRangeMin, bestRangeMax);
	}

	public static void main(String[] args) {
		int[] array = { 7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7 };
		int[] set = { 1, 5, 9 };
		System.out.println(array.length);
		Range shortest = getShorterSeq(array, set);
		if (shortest == null) {
			System.out.println("not found");
		} else {
			System.out.println(shortest.getStart() + ", " + shortest.getEnd());
		}
	}
}

class HeapNode implements Comparable<HeapNode> {
	public int locationWithinList;
	public int listId;

	public HeapNode(int location, int list) {
		locationWithinList = location;
		listId = list;
	}

	@Override
	public int compareTo(HeapNode n) {
		return locationWithinList - n.locationWithinList;
	}
}

class Range {
	private int start;
	private int end;

	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int length() {
		return end - start + 1;
	}

	public boolean shorterThan(Range other) {
		return this.length() < other.length();
	}
}
