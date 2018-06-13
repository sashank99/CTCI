/**
* Write a method to compute all permutations of a string whose characters are not necessarily unique. 
* The list of permutations should not have duplicates.
*/
public class PermutationWithDups{

	public static List<String> findPermutations(String s){
		if(s==null) return null;
		Map<Character,Integer> charCountMap=buildCharCountMap(s);
		List<String> permutationsList=new ArrayList<>();
		findPermutations(charCountMap,s.length(),"",permutationsList);
		return permutationsList;
	}
	
	public static Map<Character,Integer> buildCharCountMap(String s){
	
		Map<Character,Integer> map=new HashMap<>();
		
		for(i=0;i<s.length();i++){
			Integer count=map.get(s.charAt(i));
			if(count==null) count=0;
			map.put(s.charAt(i),++count);
		}
		
		return map;
	}
	
	public static void findPermutations(Map<Character,Integer> charCountMap,int remaining,String prefix,List<String> permutationsList){
		if(remaining==0){
			permutationsList.add(prefix);
			return;
		}
		
		for(Character c:charCountMap.keySet()){
			Integer count=charCountMap.get(c);
			if(count>0){
				charCountMap.put(c,count-1);
				findPermutations(charCountMap,remaining-1,prefix+c,permutationsList);
				charCountMap.put(c,count);
			}
		}
	}

}