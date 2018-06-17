package sorting_searching;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array like data structure Listy which lacks a size method.
 * It does, however, have an elementAt (i) method that returns the element at
 * index i in O(1) time. If i is beyond the bounds of the data structure, it
 * returns -1.( For this reason, the data structure only supports positive
 * integers). Given a List which contains sorted positive integers, find the
 * index at which an element x occurs. If x occurs multiple times, you may
 * return any index
 */
public class SortedSearch {

	public static int search(Listy listy, int element) {
		int index = 1;
		while (listy.elementAt(index) != -1 && listy.elementAt(index) <= element) {
			index *= 2;
		}
		return binarySearch(listy, (index / 2), index, element);
	}

	public static int binarySearch(Listy listy, int start, int end, int element) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if (listy.elementAt(mid) == element)
			return mid;
		else if (listy.elementAt(mid) == -1 || listy.elementAt(mid) > element)
			return binarySearch(listy, start, mid - 1, element);
		else
			return binarySearch(listy, mid + 1, end, element);

	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 18};
		Listy list = new Listy();
		list.addAll(array);
		for (int a : array) {
			System.out.println(search(list, a));
		}
		System.out.println(search(list, 15));
	}

}

class Listy {
	private  List<Integer>list=new ArrayList<>();

	public void add(int i) {
		list.add(i);
	}
	
	public void addAll(int[] arr) {
		for(int element:arr){
			list.add(element);
		}
	}

	public int elementAt(int i) {

		return i >= list.size() ? -1 : list.get(i);
	}
}
