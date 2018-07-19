package Hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Design an algorithm to find the nth number such that only prime factors are
 * 3,5 and 7. Note that 3,5, and 7 do not have to be factors, but it should not
 * have any other prime factors. For example, the first several multiple would
 * be (in order ) 1,3,5,7,9,15,21
 */
public class KthMultiple {

	public static int getKthMagicNumber(int k) {
		if (k < 0)
			return 0;
		Queue<Integer> q3 = new LinkedList<>();
		Queue<Integer> q5 = new LinkedList<>();
		Queue<Integer> q7 = new LinkedList<>();
		q3.add(1);
		int val = 0;
		for (int i = 0; i <= k; i++) {
			int v3 = q3.isEmpty() ? Integer.MAX_VALUE : q3.peek();
			int v5 = q5.isEmpty() ? Integer.MAX_VALUE : q5.peek();
			int v7 = q7.isEmpty() ? Integer.MAX_VALUE : q7.peek();
			val = Math.min(v3, Math.min(v5, v7));
			if (val == v3) {
				q3.remove();
				q3.add(3 * val);
				q5.add(5 * val);
			} else if (val == v5) {
				q5.remove();
				q5.add(5 * val);
			} else {
				q7.remove();
			}
			q7.add(7 * val);
		}
		return val;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 200; i++) {
			System.out.println(getKthMagicNumber(i));
		}
	}
}
