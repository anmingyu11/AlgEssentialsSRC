package _java;

import base.Base;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction
 * (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7 - 1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class _0121BestTimetoBuyandSellStock extends Base {

    private abstract static class Solution {
        public abstract int maxProfit(int[] prices);
    }

    /**
     * Runtime: 1 ms, faster than 80.11% of Java online submissions for Best Time to Buy and Sell Stock.
     * Memory Usage: 35.5 MB, less than 94.99% of Java online submissions for Best Time to Buy and Sell Stock.
     */
    private static class Solution1 extends Solution {
        /**
         * The points of interest are the peaks and valleys in the given graph.
         * We need to find the largest peak following the smallest valley.
         * We can maintain two variables - minprice and maxprofit corresponding to the
         * smallest valley and maximum profit
         * (maximum difference between selling price and minprice) obtained so far respectively.
         * @param A
         * @return
         */
        @Override
        public int maxProfit(int[] A) {
            final int n = A.length;
            int max = 0;
            if (n < 1) {
                return max;
            }
            int buy = A[0];
            // 既然从1开始就要想到会越界的这个问题.
            for (int i = 1; i < n; ++i) {
                max = Math.max(max, A[i] - buy);
                if (A[i] < buy) {
                    buy = A[i];
                }
            }
            return max;
        }

    }

    public static void main(String[] args) {
        int[] a1 = {7, 1, 5, 3, 6, 4};
        int[] a2 = {7, 6, 4, 3, 1};
        int[] a3 = {};

        Solution s = new Solution1();

        println(s.maxProfit(a1)); // 5
        println(s.maxProfit(a2)); // 0
    }
}
