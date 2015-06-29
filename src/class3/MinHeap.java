package class3;

public class MinHeap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int DEFAULT_CAPABILITY = 1024;
	private int[] array;
	private int size;
	
	public MinHeap(int capability) {
		if (capability <= 0) {
			throw new IllegalArgumentException("the capability should not smaller than 0");
		}
		array = new int[capability];
		size = 0;
	}
	
	public int size() {
		return size;
	} 
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == array.length;
	}
	
	
	
}
