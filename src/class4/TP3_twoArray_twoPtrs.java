package class4;

import java.util.Arrays;

public class TP3_twoArray_twoPtrs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int smallestDifference(int[] A, int[] B) {
        // write your code here
		// sort the arrays first. 
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		int i = 0, j = 0;
		int min = Integer.MAX_VALUE;
		while(i < A.length && j < B.length) {
			min = Math.min(min, Math.abs(A[i] - B[j]));
			if (A[i] < B[j]) {
				i ++;
			} else {
				j ++;
			}
		}
		
		return min;
    }
}
