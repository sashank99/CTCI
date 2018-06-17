package sorting_searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CtCILibrary.AssortedMethods;
/**
* Write a method to sort an array of strings so that all the anagrams are next to each other.
*/
public class GroupAnagrams{

	public static void sort(String[] arr){
		if(arr==null) return;
		Map<String,List<String>> listMap=new HashMap<>();
		for(String str:arr){
			String sortStr= sort(str);
			List<String> list=listMap.get(sortStr);
			if(list==null){
				list=new ArrayList<>();
			}
			list.add(str);
			listMap.put(sortStr, list);
		}
		int index=0;
		for(List<String> list: listMap.values()){
			for(String str: list){
				arr[index++]= str;
			}
		}
	}

	public static String sort(String str){
		char[] chars= str.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
	
	public static void main(String[] args) {
		String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
		sort(array);
		System.out.println(AssortedMethods.stringArrayToString(array));
	}
}
