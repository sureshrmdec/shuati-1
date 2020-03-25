package leetcode;

public class LE_1031_Maximum_Sum_Of_Two_Non_Overlapping_Subarrays {
    /**
     * Given an array A of non-negative integers, return the maximum sum of elements in
     * two non-overlapping (contiguous) subarrays, which have lengths L and M.
     * (For clarification, the L-length subarray could occur before or after the M-length subarray.)
     *
     * Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1]
     * + ... + A[j+M-1]) and either:
     *
     * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
     * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
     *
     * Example 1:
     * Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
     * Output: 20
     * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
     *
     * Example 2:
     * Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
     * Output: 29
     * Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
     *
     * Example 3:
     * Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
     * Output: 31
     * Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
     *
     * Note:
     * L >= 1
     * M >= 1
     * L + M <= A.length <= 1000
     * 0 <= A[i] <= 1000
     *
     * Medium
     */

    /**
     * Prefix Sum + Sliding Window
     *
     * Basically it can be broken it into 2 cases:
     *   L is always before M
     *   M is always before L
     *
     * L is always before M, we maintain a lMax to keep track of the max sum of L subarray,
     * and sliding the window of M from left to right to cover all the M subarray.
     *
     * The same for the case where M is before L.
     *
     *             L          M  preSum[i]
     * |_______|_____|___________|_________________|
     *     preSum[i - M - L]
     *             preSum[i - M]
     *
     *
     *              M         L  preSum[i]
     * |_______|__________|______|_________________|
     *    preSum[i - M - L]
     *                  preSum[i - L]
     *
     * Time  : O(n)
     * Space : O(1)
     */
    class Solution {
        public int maxSumTwoNoOverlap(int[] A, int L, int M) {
            if (A == null || A.length == 0) return 0;

            int n = A.length;
            int[] preSum = new int[n + 1];

            for (int i = 0; i < n ; i++) {
                preSum[i + 1] = preSum[i] + A[i];
            }

            int lMax = preSum[L];
            int mMax = preSum[M];
            int res = preSum[L + M];

            for (int i = L + M; i <= n; i++) {
                //case 1: L subarray is always before M subarray
                lMax = Math.max(lMax, preSum[i - M] - preSum[i - M - L]);

                //case 2: M subarray is always before L subarray
                mMax = Math.max(mMax, preSum[i - L] - preSum[i - M - L]);

                /**
                 * compare two cases and update res
                 *
                 * !!!
                 * preSum[i] - preSum[i - M] : sum of subarray with length of M and ends at ith element(not index i)
                 * preSum[i] - preSum[i - L] : sum of subarray with length of L and ends at ith element(not index i)
                 **/
                res = Math.max(res, Math.max(lMax + preSum[i] - preSum[i - M], mMax + preSum[i] - preSum[i - L]));
            }

            return res;
        }
    }
}
