package class3;

import java.util.*;

public class SlidingWindowMaximum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	public static void test() {
		int[] nums = {1,4,2,7,9,3,1};
		int k = 3;
		ArrayList<Integer> result = medianSlidingWindow(nums, k);
		System.out.println(result);
	}
	public static ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
		HashHeap maxHeap = new HashHeap("");
		HashHeap minHeap = new HashHeap("min");

		// suppose that maxHeap.size == minHeap.size if k is even
		// or maxHeap.size == minHeap.size + 1 if k is odd

		// first, sort the first k elements
		int[] firstpart = Arrays.copyOf(nums, k);
		Arrays.sort(firstpart);

		if (k % 2 == 0) {
			for (int i = 0; i < k; i++) {
				if (i < k / 2) {
					maxHeap.offer(firstpart[i]);
				} else {
					minHeap.offer(firstpart[i]);
				}
			}
		} else {
			for (int i = 0; i < k; i++) {
				if (i <= k / 2) {
					maxHeap.offer(firstpart[i]);
				} else {
					minHeap.offer(firstpart[i]);
				}
			}
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(maxHeap.peek());
		for(int i = k; i < nums.length; i ++) {
			int cur = nums[i];
			int prev = nums[i - k];
			if (cur <= maxHeap.peek()) {
				maxHeap.offer(cur);
			} else if (cur >= minHeap.peek() ) {
				minHeap.offer(cur);
			}
			
			if (minHeap.hash.containsKey(prev)){
				minHeap.pollElement(prev);
			} else if (maxHeap.hash.containsKey(prev)) {
				maxHeap.pollElement(prev);
			}
			
			// rebalance the two heap.
			
			if (k %2 == 1) {
				if (maxHeap.size() > minHeap.size() + 1) {
					int element = maxHeap.poll();
					minHeap.offer(element);
				} else if (maxHeap.size() < minHeap.size() + 1) {
					int element = minHeap.poll();
					maxHeap.offer(element);
				}
			} else {
				if (maxHeap.size() > minHeap.size()) {
					int element = maxHeap.poll();
					minHeap.offer(element);
				} else if (maxHeap.size() < minHeap.size()) {
					int element = minHeap.poll();
					maxHeap.offer(element);
				}
			}
			result.add(maxHeap.peek());
		}
		
		return result;
	}

}
