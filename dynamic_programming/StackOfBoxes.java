package dynamic_programming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * You have a stack of n boxes, with widths w1.heights h1, and depth d1. The
 * boxes cannot be rotated and can only be stacked on top of another if each box
 * in the stack is strictly larger than the box above it in width, height and
 * depth. Implement a method to compute the height of the tallest possible
 * stack. The height of the stack is the sum of the heights of each box.
 */
public class StackOfBoxes {

	public static int createStack(List<Box> boxes) {
		if (boxes == null)
			return 0;
		Collections.sort(boxes, new BoxComparator());
		int[] stackMap = new int[boxes.size()];
		int maxHeight = Integer.MIN_VALUE;
		for (int i = 0; i < boxes.size(); i++) {
			maxHeight = Math.max(maxHeight, createStack(i, boxes, stackMap));
		}
		return maxHeight;
	}

	public static int createStack(int bottomIndex, List<Box> boxes, int[] stackMap) {
		if (bottomIndex < boxes.size() && stackMap[bottomIndex] > 0)
			return stackMap[bottomIndex];
		Box bottomBox = boxes.get(bottomIndex);
		int maxHeight = 0;
		for (int i = bottomIndex + 1; i < boxes.size(); i++) {
			if (boxes.get(i).canBeAbove(bottomBox)) {
				maxHeight = Math.max(maxHeight, createStack(i, boxes, stackMap));
			}
		}
		maxHeight += bottomBox.height;
		stackMap[bottomIndex] = maxHeight;
		return maxHeight;
	}

	public static void main(String[] args) {
		Box[] boxList = { new Box(6, 4, 4), new Box(8, 6, 2), new Box(5, 3, 3), new Box(7, 8, 3), new Box(4, 2, 2),
				new Box(9, 7, 3) };
		List<Box> boxes = new ArrayList<>();
		for (Box b : boxList) {
			boxes.add(b);
		}

		int height = createStack(boxes);
		System.out.println(height);
	}
}

class Box {
	public int height;
	public int depth;
	public int width;

	public Box(int height, int depth, int width) {
		this.height = height;
		this.depth = depth;
		this.width = width;
	}

	public boolean canBeAbove(Box bottomBox) {
		return this.height < bottomBox.height && depth < bottomBox.depth && this.width < bottomBox.width;
	}
}

class BoxComparator implements Comparator<Box> {
	public int compare(Box a, Box b) {
		return b.height - a.height;
	}
}
