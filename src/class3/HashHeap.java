package class3;

import java.util.ArrayList;
import java.util.HashMap;

import class3.HashMinHeap.Node;

public class HashHeap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<Integer>();
		System.out.println(list.get(0));
	}

	public ArrayList<Integer> heap;
	private String mode;
	private int size_t;
	public HashMap<Integer, Node> hash;
	
	public class Node{
		public Integer index;
		public Integer counter;
		
		public Node(Node now) {
			// TODO Auto-generated constructor stub
			this.index = now.index;
			this.counter = now.counter;
		}
		
		public Node(Integer first, Integer second) {
			this.index = first;
			this.counter = second;
		}
	}
	
	public HashHeap(String mod) {
		this.heap = new ArrayList<Integer>();
		this.mode = mod;
		hash = new HashMap<Integer, Node>();
		size_t = 0;
	}
	
	public int peek() {
		if (heap == null || heap.size() == 0) {
			throw new IllegalArgumentException("the heap is empty");
		}
		return heap.get(0);
	}
	
	public int size() {
		return size_t;
	}
	
	public boolean isEmpty() {
		return heap.size() == 0;
	}
	
	public int parent(int id) {
		if (id == 0) {
			return -1;
		}
		return (id - 1)/2;
	}
	
	public int leftChild(int id) {
		return id * 2 + 1;
	}
	
	public int rightChild(int id) {
		return id * 2 + 2;
	}
	

	
	public void offer(int element) {
		size_t ++;
		// already in map
		if (hash.containsKey(element)) {
			Node node = hash.get(element);
			hash.put(element, new Node(node.index, node.counter + 1));
		} else { // not in map
			heap.add(element);
			hash.put(element, new Node(heap.size() - 1, 1));
			shiftUp(heap.size() - 1);
		}
	}
	
	
	/*
	 * if a <= b
	 * if mode = 'min', a has higher priority, return true
	 * if mode != 'min' [mode = 'max'], b has higher priority, return false;
	 * 
	 * if a > b
	 * if mode = 'min', b has higher priority, return false;
	 * if mode 1= 'min'[mode == 'max'], a has higher priority, return true;
	 */
	public boolean firstHasHigherPriority(int a, int b) {
		if (a <= b) {
			if (mode.equals("min")) {
				return true;
			} else {
				return false;
			}
		} else {
			if (mode.equals("max")) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public void swap(int indexA, int indexB) {
		// the original value from heap. 
		int valA = heap.get(indexA);
		int valB = heap.get(indexB);
		
		
		// update in heap
		heap.set(indexA, valB);
		heap.set(indexB, valA);
		
		
		int cntA = hash.get(valA).counter;
		int cntB = hash.get(valB).counter;
		
		hash.put(valA, new Node(indexB, cntA));
		hash.put(valB, new Node(indexA, cntB));
	}
	
	public Integer poll() {
		if (isEmpty()) {
			throw new IllegalAccessError("the heap is empty");
		}
		size_t --;
		
		Integer now = heap.get(0);
		Node hashnow = hash.get(now);
		if (hashnow.counter == 1) { // no duplicate, delete this val from heap and hash
			swap(0, heap.size() - 1);
			// delete from heap
			heap.remove(heap.size() - 1);
			// delete from hash
			hash.remove(now);
			// shiftDown the new head
			shiftDown(0);
		} else {
			// now has duplicates, only decreate the counter.
			hash.put(now, new Node(0, hashnow.counter - 1));
		}
		return now;
	}
	
	public boolean pollElement(int element) {
		if (!hash.containsKey(element)) {
			return false;
		}
		Node node = hash.get(element);
		int index = node.index;
		int count = node.counter;
		if (index < 0 || index >= heap.size()) {
			return false;
		}
		if (count > 1) {
			hash.put(element, new Node(index, count - 1));
		} else {
			swap(index, heap.size() - 1);
			size_t --;
			heap.remove(heap.size() - 1);
			shiftDown(index);
		}
		return true;
	}
	
		
	public void shiftDown(int index) {
		while(index < size_t) {
			int leftChildIndex = leftChild(index);
			int rightChildIndex = rightChild(index);
			
			int min = index;
			
			if (leftChildIndex < size_t && firstHasHigherPriority(heap.get(leftChildIndex), heap.get(min))) {
				min = leftChildIndex;
			}
			
			if (rightChildIndex < size_t && firstHasHigherPriority(heap.get(rightChildIndex), heap.get(min))) {
				min = rightChildIndex;
			}
			
			if (min == index) {
				break;
			}
			
			swap(index, min);
			index = min;
		}
	}
	
	public void shiftUp(int index) {
		while(index >= 0) {
			int partent = (index - 1)/2;
			
			if (partent >= 0 && firstHasHigherPriority(heap.get(index), heap.get(partent))) {
				swap(index, partent);
				index = partent;
			} else {
				break;
			}
		}
	}
	
	
	
	
	
	/*
	 * follow up
	 * 
	 * how to create heap which has duplicates
	 * 
	 * 节点对应值是多少， count
	 * Node{
	 * 	int value;
	 * 	int count;
	 * }
	 * 
	 * offer(10)
	 * offer(10)
	 * 
	 * 
	 * delete(10)  count --
	 * delete(10)  
	 */
	
	
	/*
	 * HashHeap  almost the same with LRU cache
	 * 
	 * LRU cache: HashMap + Linked List
	 */
	

}
