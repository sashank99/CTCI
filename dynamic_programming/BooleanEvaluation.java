package dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a boolean expression consisting of the symbols 0 (false), 1 (true), &
 * (AND), | (OR), and ^ (XOR), and a desired boolean result value result,
 * implement a function to count the number of ways of paranthesizing the
 * expression such that it evaluates to result. The expression should be fully
 * parenthesized (e.g., (0) ^ (1)) but not extraneously (e.g., (((0)) ^ (1))).
 * Example countEval(“1^0|0|1”,false) -> 2 countEval(“0&0&0&1^1|0”,true) -> 10
 */
public class BooleanEvaluation {

	public static int countEval(String expression, boolean result, Map<String, Integer> memo) {
		if (expression == null || expression.length() == 0)
			return 0;
		if (expression.length() == 1)
			return expression.equals("1") == result ? 1 : 0;
		String memoKey = result + expression;
		Integer count = memo.get(memoKey);
		if (count != null)
			return count;
		else count=0;

		for (int i = 1; i < expression.length(); i += 2) {
			char c = expression.charAt(i);
			String leftExp = expression.substring(0, i);
			String rightExp = expression.substring(i + 1);
			int leftTrueCount = countEval(leftExp, true, memo);
			int leftFalseCount = countEval(leftExp, false, memo);
			int rightTrueCount = countEval(rightExp, true, memo);
			int rightFalseCount = countEval(rightExp, false, memo);
			int total = (leftTrueCount + leftFalseCount) * (rightTrueCount + rightFalseCount);
			int totalTrueCount = 0;
			if (c == '&') {
				totalTrueCount = leftTrueCount * rightTrueCount;
			} else if (c == '^') {
				totalTrueCount = leftTrueCount * rightFalseCount + leftFalseCount * rightTrueCount;
			} else {
				totalTrueCount = leftTrueCount * rightTrueCount + leftTrueCount * rightFalseCount
						+ leftFalseCount * rightTrueCount;
			}
			count += result ? totalTrueCount : total - totalTrueCount;
		}
		memo.put(memoKey, count);
		return count;
	}
	
	public static void main(String[] args) {
		String expression = "0&0&0&1^1|0";
		Map<String,Integer> memo=new HashMap<>();
		System.out.println(countEval(expression,true,memo));
	}

}
