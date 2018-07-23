package Hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import CtCILibrary.AssortedMethods;

/**
 * The similarity of two documents (each with distinct words) is defined to be
 * the size of intersection divided by the size of union. For example, if the
 * documents consist of integers, the similarity of {1,5,3} and {1,7,2,3} is
 * 0.4, because the intersection has size 2 and union has size 5. We have long
 * list of documents (with distinct values and each with an associated ID) where
 * the similarity is believed to be “sparse”. That is, any two arbitrarily
 * selected documents are very likely to have similarity 0. Design an algorithm
 * that returns a list of pairs of document IDs and the associated similarity.
 * Print only the pairs with similarity greater than 0. Empty documents should
 * not be printed at all. For simplicity, you may assume each document os
 * represent as an array of distinct integers. Example: Input: 13: {14,15, 100,
 * 9, 3} 16: {32,1,9,3,5} 19: {15,29,2,6,8,7} 24: {7,10} Output: ID1, ID2 :
 * Similarity 13, 19 : 0.1 13, 16 : 0.25 19, 24 : 0.14285714285714285
 */
public class SparseSimilarity {

	public static Map<DocPair, Double> computeSimilarities(Map<Integer, Document> documents) {
		Map<Integer, List<Integer>> wordsToDocs = groupWords(documents);
		Map<DocPair, Double> docPairs = computeIntersections(wordsToDocs);
		adjustSimilarity(documents, docPairs);
		return docPairs;
	}

	private static Map<Integer, List<Integer>> groupWords(Map<Integer, Document> documents) {
		Map<Integer, List<Integer>> wordsToDocs = new HashMap<>();
		for (Document doc : documents.values()) {
			List<Integer> words = doc.getWords();
			if (words == null)
				continue;
			for (int i = 0; i < words.size(); i++) {
				List<Integer> docsList = wordsToDocs.get(words.get(i));
				if (docsList == null) {
					docsList = new ArrayList<>();
				}
				docsList.add(doc.getDocId());
				wordsToDocs.put(words.get(i), docsList);
			}
		}
		return wordsToDocs;
	}

	private static Map<DocPair, Double> computeIntersections(Map<Integer, List<Integer>> wordToDocs) {
		Map<DocPair, Double> docPairsMap = new HashMap<>();
		for (List<Integer> docsList : wordToDocs.values()) {
			Collections.sort(docsList);
			for (int i = 0; i < docsList.size(); i++) {
				for (int j = i + 1; j < docsList.size(); j++) {
					DocPair pair = new DocPair(docsList.get(i), docsList.get(j));
					Double intersectionCount = docPairsMap.get(pair);
					if (intersectionCount == null)
						intersectionCount = 0.0;
					docPairsMap.put(pair, intersectionCount+1);
				}
			}

		}
		return docPairsMap;
	}

	private static void adjustSimilarity(Map<Integer, Document> documents, Map<DocPair, Double> docPairs) {
		for (Map.Entry<DocPair, Double> entry : docPairs.entrySet()) {
			DocPair pair = entry.getKey();
			double union = (double) documents.get(pair.doc1).getSize() + documents.get(pair.doc2).getSize()
					- entry.getValue();
			//System.out.println(pair.doc1+" ,"+pair.doc2+" intersection:"+entry.getValue()+" union:"+union);
			entry.setValue(entry.getValue() / union);
		}
	}
	
	public static ArrayList<Integer> removeDups(int[] array) {
		HashSet<Integer> set = new HashSet<>();
		for (int a : array) {
			set.add(a);
		}
		ArrayList<Integer> list = new ArrayList<>();
		list.addAll(set);
		return list;
	}
	
	public static boolean isEqual(Map<DocPair, Double> one, Map<DocPair, Double> two) {
		if (one.size() != two.size()) {
			return false;
		}
		
		for (Entry<DocPair, Double> a : one.entrySet()) {
			if (!two.containsKey(a.getKey())) {
				return false;
			}
			double sim1 = a.getValue();
			double sim2 = two.get(a.getKey());
			if (sim1 != sim2) {
				return false;
			}
		}
		return true;
	}
	
	public static void printSim(Map<DocPair, Double> similarities) {
		for (Entry<DocPair, Double> result : similarities.entrySet()) {
			DocPair pair = result.getKey();
			Double sim = result.getValue();
			System.out.println(pair.doc1 + ", " + pair.doc2 + " : " + sim);
		}
	}
	
	public static void main(String[] args) {
		int numDocuments = 10;
		int docSize = 5;
		HashMap<Integer, Document> documents = new HashMap<>();
		for (int i = 0; i < numDocuments; i++) {
			int[] words = AssortedMethods.randomArray(docSize, 0, 10);
			ArrayList<Integer> w = removeDups(words);
			System.out.println(i + ": " + w.toString());
			Document doc = new Document(i, w);
			documents.put(i, doc);
		}
		
		Map<DocPair, Double> similarities = computeSimilarities(documents);
		printSim(similarities);
	}
}

class Document {
	private List<Integer> words;
	private int docId;

	public Document(int docId, List<Integer> words) {
		this.words = words;
		this.docId = docId;
	}

	public List<Integer> getWords() {
		return words;
	}

	public int getDocId() {
		return docId;
	}

	public int getSize() {
		return words == null ? 0 : words.size();
	}
}

class DocPair {
	public int doc1;
	public int doc2;

	public DocPair(int doc1, int doc2) {
		this.doc1 = doc1;
		this.doc2 = doc2;
	}

	@Override
	public boolean equals(Object otherPair) {
		if (otherPair instanceof DocPair)
			return doc1 == ((DocPair) otherPair).doc1 && doc2 == ((DocPair) otherPair).doc2;
		return false;
	}
	
	@Override
	public int hashCode() {
		return (doc1 * 31) ^ doc2;
	}
}
