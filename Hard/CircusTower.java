package Hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A circus is designing a tower routine consisting of people standing atop one
 * another’s shoulders. For practical and aesthetic reasons, each person must be
 * both shorter and lighter than the person below him or her. given the heights
 * and weights of each person in the circus, write a method to compute the
 * largest possible number of people in such a tower.
 */
public class CircusTower {
	public List<HtWt> longestIncreasingSeq(List<HtWt> persons) {
		if (persons == null)
			return null;
		Collections.sort(persons);

		List<List<HtWt>> solutions = new ArrayList<>();
		List<HtWt> bestSequence = new ArrayList<>();

		for (int i = 0; i < persons.size(); i++) {
			List<HtWt> longestAtIndex = bestSeqAtIndex(persons, solutions, i);
			solutions.add(longestAtIndex);
			bestSequence = max(bestSequence, longestAtIndex);
		}

		return bestSequence;
	}

	public List<HtWt> bestSeqAtIndex(List<HtWt> persons, List<List<HtWt>> solutions, int index) {
		HtWt value = persons.get(index);
		List<HtWt> bestSequence = new ArrayList<HtWt>();

		for (int i = 0; i < index; i++) {
			List<HtWt> solution = solutions.get(i);
			if (canAppend(solution, value)) {
				bestSequence = max(solution, bestSequence);
			}
		}
		List<HtWt> best = (ArrayList<HtWt>) ((ArrayList<HtWt>) bestSequence).clone();
		best.add(value);
		return best;
	}

	public List<HtWt> max(List<HtWt> seq1, List<HtWt> seq2) {
		if (seq1 == null)
			return seq2;
		if (seq2 == null)
			return seq1;

		return seq1.size() > seq2.size() ? seq1 : seq2;
	}

	public boolean canAppend(List<HtWt> solution, HtWt value) {
		if (solution == null)
			return false;
		if (solution.size() == 0)
			return true;

		HtWt last = solution.get(solution.size() - 1);
		return last.isBefore(value);
	}

	public static ArrayList<HtWt> initialize() {
		ArrayList<HtWt> items = new ArrayList<HtWt>();

		HtWt item = new HtWt(65, 60);
		items.add(item);

		item = new HtWt(70, 150);
		items.add(item);

		item = new HtWt(56, 90);
		items.add(item);

		item = new HtWt(75, 190);
		items.add(item);

		item = new HtWt(60, 95);
		items.add(item);

		item = new HtWt(68, 110);
		items.add(item);

		item = new HtWt(35, 65);
		items.add(item);

		item = new HtWt(40, 60);
		items.add(item);

		item = new HtWt(45, 63);
		items.add(item);

		return items;
	}

	public static void printList(List<HtWt> list) {
		for (HtWt item : list) {
			System.out.println(item.toString() + " ");
		}
	}

	public static void main(String[] args) {
		ArrayList<HtWt> items = initialize();
		printList(items);
		List<HtWt> solution = new CircusTower().longestIncreasingSeq(items);
		System.out.println("\nAfter solution\n");
		printList(solution);
	}
}

class HtWt implements Comparable<HtWt> {
	private int ht;
	private int wt;

	public HtWt(int ht, int wt) {
		this.ht = ht;
		this.wt = wt;
	}

	public int compareTo(HtWt other) {
		if (this.ht != other.ht) {
			return ((Integer) ht).compareTo((Integer) other.ht);
		} else {
			return ((Integer) wt).compareTo((Integer) wt);
		}
	}

	public boolean isBefore(HtWt other) {
		if (ht < other.ht && wt < other.wt) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ht:"+ht+" wt:"+wt;
	}
}
