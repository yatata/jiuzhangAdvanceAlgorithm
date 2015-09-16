package class4;

import java.util.HashMap;
import java.util.HashSet;

public class TP2_fwd_Windows {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test1();
//		test3();
		test4();
//		test5();
	}

	public static void test5() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] array = {1,2,2,3};
		for(int i =0; i < array.length; i ++) {
			if (map.containsKey(array[i])) {
				map.put(array[i], map.get(array[i]) + 1);
			} else {
				map.put(array[i], 1);
			}
		}
		System.out.println(map.size());
	}
	/*
	 * Minimum size subarray sum
	 */
	public static void test1() {
		int[] nums = { 2, 3, 1, 2, 4, 3 };
		int s = 7;
		int rev = minimumSize(nums, s);
		System.out.println("rev = " + rev);

	}

	public static int minimumSize(int[] nums, int s) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int fast = 0, slow = 0;
		int curSum = 0;
		int minLength = Integer.MAX_VALUE;
		while (slow < nums.length) {
			while (fast < nums.length && curSum < s) {
				curSum += nums[fast];
				fast++;
			}
			if (curSum >= s) {
				minLength = Math.min(minLength, fast - slow);
			}
			curSum -= nums[slow];
			slow++;
		}

		if (minLength == Integer.MAX_VALUE) {
			minLength = -1;
		}
		return minLength;
	}

	/*
	 * longest substring without repeating characters
	 */

	public static int lengthOfLongestSubstring(String s) {
		int slow = 0, fast = 0;
		int maxLen = 0;
		HashSet<Character> set = new HashSet<Character>();
		while (slow < s.length()) {
			while (fast < s.length() && !set.contains(s.charAt(fast))) {
				set.add(s.charAt(fast));
				fast++;
			}
			maxLen = Math.max(maxLen, fast - slow);
			set.remove(s.charAt(slow));
			slow++;

		}
		return maxLen;
	}

	public static int lengthOfLongestSubstring2(String s) {
		int slow = 0, fast = 0;
		int maxLen = 0;
		HashSet<Character> set = new HashSet<Character>();
		while(fast < s.length()) {
			if (!set.contains(s.charAt(fast))) {
				set.add(s.charAt(fast));
				fast ++;
				maxLen = Math.max(maxLen, fast - slow);
			} else {
				while(set.contains(s.charAt(fast))) {
					char slowChar = s.charAt(slow);
					set.remove(slowChar);
					slow ++;
				}
			}
		}
		return maxLen;
	}

	/*
	 * Minimum Window Substring
	 */
	public static void test3() {
		String source = "abc";
		String target = "a";
		String result = minWindow(source, target);
		System.out.println("result = " + result);
	}

	public static String minWindow(String source, String target) {
		if (source == null || source.length() == 0) {
			return "";
		}

		int[] targetHash = new int[256];
		int[] sourceHash = new int[256];
		String minStr = "";
		int minLen = Integer.MAX_VALUE;

		for (int i = 0; i < target.length(); i++) {
			targetHash[target.charAt(i)]++;
		}

		int slow = 0, fast = 0;
		while (slow < source.length()) {
			while (!isValid(sourceHash, targetHash) && fast < source.length()) {
				sourceHash[source.charAt(fast)]++;
				if (fast < source.length()) {
					fast++;
				} else {
					break;
				}
			}

			if (isValid(sourceHash, targetHash)) {
				if (minLen > fast - slow) {
					minLen = fast - slow;
					System.out.println("slow = " + slow);
					System.out.println("fast = " + fast);
					minStr = source.substring(slow, fast);
				}
			}

			sourceHash[source.charAt(slow)]--;
			slow++;
		}

		return minStr;
	}

	public static boolean isValid(int[] sourceHash, int[] targetHash) {
		for (int i = 0; i < 256; i++) {
			if (targetHash[i] > sourceHash[i]) {
				break;
			}
		}
		return true;
	}

	/*
	 * Longest Substring with At Most K Distinct Characters
	 */
	public static void test4() {
		String s = "nutdrgzdrkrvfdfcvzuunxwlzegjukhkjpvqruitobiahxhgdrpqttsebjsg";
		for(int i = 0; i < s.length(); i ++) {
			System.out.println("index = " + i + "   " + "value = " + s.charAt(i));
		}
		System.out.println("---------------------");
		int k = 11;
		int rev = lengthOfLongestSubstringKDistinct(s, k);
		
		
		System.out.println("rev = " + rev);
	
	}
	

	
	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		// write your code here
		if (s == null || s.length() == 0) {
			return 0;
		}
		
		int slow = 0, fast = 0;
		int maxLen = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		while(fast < s.length()) {
			char fastChar = s.charAt(fast);
			if (map.containsKey(fastChar)) {
				map.put(fastChar, map.get(fastChar) + 1);
			} else {
				map.put(fastChar, 1);
				
				while (map.size() > k) {
					// remove slow
					char slowChar = s.charAt(slow);
					slow ++;
					int count = map.get(slowChar);
					if (count > 1) {
						map.put(slowChar, count - 1);
					} else {
						map.remove(slowChar);
					}
				}
			}
			maxLen = Math.max(maxLen, fast - slow + 1);
			fast ++;
		}
		return maxLen;

	}
	
}
