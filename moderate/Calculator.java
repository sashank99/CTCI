package moderate;

import java.util.ArrayDeque;

/**
 * Given an arithmetic equation consisting of positive integers, +,-,* and / (no
 * parentheses). Compute the result. Ex: Input 2*3+5/6*3+15 Output: 23.5
 */
public class Calculator {
	public static double compute(String sequence) {
		if (sequence == null || sequence.length() == 0)
			return 0;

		ArrayDeque<Double> numberStack = new ArrayDeque<>();
		ArrayDeque<Operator> operatorStack = new ArrayDeque<>();
		for (Integer i = 0; i < sequence.length(); i++) {
			int value = parseNextNumber(sequence, i); 
			numberStack.push((double)value);
			i+= Integer.toString(value).length();
			
			if (i >= sequence.length())
				break;

			Operator operator = parseNextOperator(sequence.charAt(i));

			collapseTop(operator, numberStack, operatorStack);
			operatorStack.push(operator);
		}
		collapseTop(Operator.BLANK, numberStack, operatorStack);
		if (numberStack.size() == 1 && operatorStack.size() == 0)
			return numberStack.pop();
		return 0;
	}

	public static void collapseTop(Operator op, ArrayDeque<Double> numberStack, ArrayDeque<Operator> operatorStack) {
		while (operatorStack.size() >= 1 && numberStack.size() >= 2) {
			if (priorityOfOperator(op) <= priorityOfOperator(operatorStack.peek())) {
				double right = numberStack.pop();
				double left = numberStack.pop();
				numberStack.push(applyOp(left, right, operatorStack.pop()));
			} else {
				break;
			}
		}
	}

	public static double applyOp(double left, double right, Operator op) {
		switch (op) {
		case ADD:
			return left + right;
		case SUBSTRACT:
			return left - right;
		case MULTIPLY:
			return left * right;
		case DIVIDE:
			return left / right;
		}
		return right;

	}

	public static Operator parseNextOperator(char op) {
		switch (op) {
		case '+':
			return Operator.ADD;
		case '-':
			return Operator.SUBSTRACT;
		case '*':
			return Operator.MULTIPLY;
		case '/':
			return Operator.DIVIDE;
		}
		return Operator.BLANK;
	}

	public static int parseNextNumber(String sequence, Integer offset) {
		StringBuilder sb = new StringBuilder();
		while (offset < sequence.length() && Character.isDigit(sequence.charAt(offset))) {
			sb.append(sequence.charAt(offset));
			offset++;
		}
		return Integer.parseInt(sb.toString());
	}

	public static int priorityOfOperator(Operator op) {
		switch (op) {
		case ADD:
			return 1;
		case SUBSTRACT:
			return 1;
		case MULTIPLY:
			return 2;
		case DIVIDE:
			return 2;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		String sequence="6/5*3+4*5/2-12/6*3/3+3+3";
		System.out.println(compute(sequence));
	}
}

enum Operator {
	ADD, SUBSTRACT, MULTIPLY, DIVIDE, BLANK;
}
