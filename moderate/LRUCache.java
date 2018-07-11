package moderate;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and build a “least recently used” cache, which evicts the least
 * recently used item. The cache should map from keys to values (allowing you to
 * insert and retrieve a value associated with a particular key) and be
 * initialized with a max size. When it is full, it should evict the least
 * recently used item. You can assume the keys are integers and values are
 * strings
 */
public class LRUCache {
	private int maxCacheSize;
	private Map<Integer, LinkedListNode> map = new HashMap<>();
	private LinkedListNode head = null;
	private LinkedListNode tail = null;

	public LRUCache(int cacheSize) {
		this.maxCacheSize = cacheSize;
	}

	/* Get value for key and mark as most recently used. */
	public String getValue(int key) {
		LinkedListNode item = map.get(key);
		if (item == null)
			return null;

		if (item != head) {
			removeFromLinkedList(item);
			insertAtFrontOfLinkedList(item);
		}
		return item.value;
	}

	/* Remove node from linked list */
	private void removeFromLinkedList(LinkedListNode node) {
		if (node == null)
			return;
		if (node.prev != null)
			node.prev.next = node.next;
		if (node.next != null)
			node.next.prev = node.prev;
		if (node == tail)
			tail = node.prev;
		if (node == head)
			head = node.next;

	}

	/* Insert node at front of linked list */
	private void insertAtFrontOfLinkedList(LinkedListNode node) {
		if (node == null)
			return;
		if (head == null) {
			head = node;
			tail = node;
		} else {
			head.prev = node;
			node.next = head;
			head = node;
		}
	}

	/*
	 * Remove key/value pair from cache, deleting from hash table and linked
	 * list
	 */
	public boolean removeValue(int key) {
		LinkedListNode item = map.get(key);
		removeFromLinkedList(item);
		map.remove(key);
		return true;
	}

	/*
	 * Put key, value pair in cache. Remove old value for key if necessary.
	 * Inserts pair into linked list and hash table
	 */
	public void setKeyValue(int key, String value) {
		/* Remove if already there */
		removeValue(key);

		/* if full, remove least recently used item from cache */
		if (map.size() >= maxCacheSize && tail != null) {
			removeValue(tail.key);
		}

		/* Insert new node */
		LinkedListNode node = new LinkedListNode(key, value);
		insertAtFrontOfLinkedList(node);
		map.put(key, node);
	}
	
	public String getCacheAsString() {
		if (head == null) return "";
		return head.printForward();
	}

	private static class LinkedListNode {
		private LinkedListNode prev;
		private LinkedListNode next;
		private int key;
		private String value;

		public LinkedListNode(int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String printForward() {
			String data = "(" + key + "," + value + ")";
			if (next != null) {
				return data + "->" + next.printForward();
			} else {
				return data;
			}
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int cache_size = 5;
		LRUCache cache = new LRUCache(cache_size);
		
		cache.setKeyValue(1, "1");
		System.out.println(cache.getCacheAsString());
		cache.setKeyValue(2, "2");
		System.out.println(cache.getCacheAsString());
		cache.setKeyValue(3, "3");
		System.out.println(cache.getCacheAsString());
		cache.getValue(1);
		System.out.println(cache.getCacheAsString());
		cache.setKeyValue(4, "4");
		System.out.println(cache.getCacheAsString());
		cache.getValue(2);
		System.out.println(cache.getCacheAsString());
		cache.setKeyValue(5, "5");
		System.out.println(cache.getCacheAsString());
		cache.getValue(5);
		System.out.println(cache.getCacheAsString());
		cache.setKeyValue(6,  "6");
		System.out.println(cache.getCacheAsString());
		cache.getValue(1);
		System.out.println(cache.getCacheAsString());
		cache.setKeyValue(5, "5a");
		System.out.println(cache.getCacheAsString());
		cache.getValue(3);
		System.out.println(cache.getCacheAsString());
	}
}
