package moderate;

import java.util.HashSet;
import java.util.Set;

/**
 * An ant is sitting on an infinite grid of white and black squares. Initially,
 * the grid is all white and the ant faces right. At each step, it does the
 * following: 1) At a white square, flip the color of the square, turn 90
 * degrees right(clockwise), and move forward one unit. 2) At a black square,
 * flip the color of the square, turn 90 degrees left (counter-clockwise), and
 * move forward one unit. Write a program to simulate the first K moves that the
 * ant makes and print the final board as a grid. Note that you are not provided
 * with the data structure to represent the grid. This is something you must
 * design yourself. The only input to your method is K. You should print the
 * final gird and return nothing. The method signature might be something like
 * void printKMoves(int k).
 */
public class LangtonAnt {
	
	public void printKMoves(int k){
		Board board=new Board();
		for(int i=0;i<k;i++)
			board.move();
		System.out.println(board.toString());
	}
	public static void main(String[] args) {
		LangtonAnt langtonAnt=new LangtonAnt();
		langtonAnt.printKMoves(5020);
	}

}

class Board {
	private Set<Position> whites = new HashSet<>();
	private Ant ant = new Ant();
	private Position topLeftCorner = new Position(0, 0);
	private Position bottomRightCorner = new Position(0, 0);

	public void move() {
		ant.turn(isWhite(ant.position));// Turn
		flip(ant.position); // flip
		ant.move(); // move
		ensureFit(ant.position);
	}

	public void flip(Position position) {
		if (whites.contains(position)) {
			whites.remove(position);
		} else {
			whites.add(position.clone());
		}
	}

	/* Grow grid by tracking the most top-left and bottom-right positions */
	private void ensureFit(Position position) {
		topLeftCorner.row = Math.min(topLeftCorner.row, position.row);
		topLeftCorner.col = Math.min(topLeftCorner.col, position.col);

		bottomRightCorner.row = Math.max(bottomRightCorner.row, position.row);
		bottomRightCorner.col = Math.max(bottomRightCorner.col, position.col);
	}

	// check if cell is white
	public boolean isWhite(Position position) {
		return whites.contains(position);
	}

	/* Print board */
	public String toString() {
		StringBuilder boardBuilder = new StringBuilder();
		int rowMin = topLeftCorner.row;
		int rowMax = bottomRightCorner.row;
		int colMin = topLeftCorner.col;
		int colMax = bottomRightCorner.col;

		for (int r = rowMin; r <= rowMax; r++) {
			for (int c = colMin; c <= colMax; c++) {
				if (r == ant.position.row && c == ant.position.col) {
					boardBuilder.append(ant.orientation);
				} else if (isWhite(new Position(r, c))) {
					boardBuilder.append("X");
				} else {
					boardBuilder.append("_");
				}
			}
			boardBuilder.append("\n");
		}
		boardBuilder.append("Ant: " + ant.orientation + ".\n");
		return boardBuilder.toString();
	}
}

class Ant {
	public Position position = new Position(0, 0);
	public Orientation orientation = Orientation.right;

	public void turn(boolean clockwise) {
		orientation = orientation.getTurn(clockwise);
	}

	public void move() {
		if (orientation == Orientation.left) {
			position.col--;
		} else if (orientation == Orientation.right) {
			position.col++;
		} else if (orientation == Orientation.up) {
			position.row--;
		} else {
			position.row++;
		}
	}
}

enum Orientation {
	left, right, up, down;

	public Orientation getTurn(boolean clockwise) {
		if (this == left) {
			return clockwise ? up : down;
		} else if (this == right) {
			return clockwise ? down : up;
		} else if (this == up) {
			return clockwise ? right : left;
		} else {
			return clockwise ? left : right;
		}
	}

	@Override
	public String toString() {
		if (this == left) {
			return "\u2190";
		} else if (this == up) {
			return "\u2191";
		} else if (this == right) {
			return "\u2192";
		} else {
			return "\u2193";
		}
	}
}

class Position {
	public int row;
	public int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Position && ((Position) o).row == this.row && ((Position) o).col == this.col;

	}

	@Override
	public int hashCode() {
		return (row * 31) ^ col;
	}

	@Override
	public Position clone() {
		return new Position(row, col);
	}
}
