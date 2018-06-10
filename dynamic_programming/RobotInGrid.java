package dynamic_programming;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import CtCILibrary.AssortedMethods;

import java.util.HashSet;

/**
 * Imagine a robot sitting on the upper left corner of grid with r rows and c
 * columns. The robot can move in two directions, right and down, but certain
 * cells are "off limits" such that the robot cannot step on them. Design an
 * algorithm to find a path of the robot from the top left to the bottom right.
 */
public class RobotInGrid {

	public static List<Point> findPath(boolean[][] grid) {
		if (grid == null || grid.length == 0)
			return null;
		List<Point> path = new ArrayList<>();
		Set<Point> failedPoints = new HashSet<>();
		if (findPath(grid, grid.length - 1, grid[0].length - 1, path, failedPoints)) {
			return path;
		}
		return null;
	}

	public static boolean findPath(boolean[][] grid, int row, int col, List<Point> path, Set<Point> failedPoints) {

		if (row < 0 || col < 0 || !grid[row][col])
			return false;

		Point point = new Point(row, col);

		if (failedPoints.contains(point))
			return false;

		boolean isAtOrigin = (row == 0) && (col == 0);

		if (isAtOrigin || findPath(grid, row - 1, col, path, failedPoints)
				|| findPath(grid, row, col - 1, path, failedPoints)) {
			path.add(point);
			return true;
		} else {
			failedPoints.add(point);
			return false;
		}

	}
	
	public static void main(String[] args) {
		int size = 5;
		boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 70);
		
		AssortedMethods.printMatrix(maze);
		
		List<Point> path = findPath(maze);
		if (path != null) {
			System.out.println(path.toString());
		} else {
			System.out.println("No path found.");
		}
	}

}

class Point {
	private int row;
	private int col;

	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.toString().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Point) && (((Point) o).row == this.row) && (((Point) o).col == this.col);
	}
}
