package class3;

import java.util.ArrayList;

public class SlidingWindowMedian {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static void test() {
		int[] nums = {};
		int k = 0;
		ArrayList<Integer> result = medianSlidingWindow(nums, k);
		System.out.println(result);
	}

	public static ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.length == 0 || k == 0) {
			return result;
		}
		

		HashHeap maxHeap = new HashHeap("max");
		HashHeap minHeap = new HashHeap("min");

		// maxHeap.size == minHeap.size or maxHeap.size = minHeap.size + 1
		int median = nums[0];
		for (int i = 0; i < nums.length; i++) {
			// add current into heap
			if (i != 0) {
				if (nums[i] > median) {
					minHeap.offer(nums[i]);
				} else {
					maxHeap.offer(nums[i]);
				}
			}

			if (i >= k) {
				if (nums[i - k] == median) {
					// throw away the median, update it from
					if (maxHeap.size() > 0) {
						median = maxHeap.poll();

					} else if (minHeap.size() > 0) {
						median = minHeap.poll();
					}
				} else if (median < nums[i - k]) {
					minHeap.pollElement(nums[i - k]);	
				} else {
					maxHeap.pollElement(nums[i - k]);
				}
			}

			// shift element from maxHeap to minHeap until maxSize == minSize
			while (maxHeap.size() > minHeap.size()) {
				minHeap.offer(median);
				median = maxHeap.poll();
			}

			// minSize > 1 + maxSize
			// shift element from minHeap to maxHeap until maxSize + 1 = minSize 
			while (maxHeap.size() + 1 < minHeap.size()) {
				maxHeap.offer(median);
				median = minHeap.poll();
			}

			if (i + 1 >= k) {
				result.add(median);
			}
			
			if (i + 1>= k) {
				System.out.println("***************");
				System.out.println("nums[i] " + nums[i]);
				if (i >= k) 
					System.out.println("nums[i - k] " + nums[i - k]);
				
				System.out.println("----------------");
				System.out.println("info for maxHeap");
				maxHeap.printHeap();
				maxHeap.printHash();
				System.out.println("maxHash.size = " + maxHeap.map.size());
				System.out.println("maxHeap.size= " + maxHeap.size());
				
				System.out.println("================");
				System.out.println("median = " + median);
				System.out.println("================");
				
				System.out.println("info for minHeap");
				minHeap.printHeap();
				minHeap.printHash();
				System.out.println("minHeap.size = " + minHeap.size());
				System.out.println("----------------");
			}
			
			

		}
		return result;

	}
	

}
