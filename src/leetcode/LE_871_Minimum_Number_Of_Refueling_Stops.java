package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by yuank on 11/23/18.
 */
public class LE_871_Minimum_Number_Of_Refueling_Stops {
    /**
         A car travels from a starting position to a destination which is target miles east of the starting position.

         Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0]
         miles east of the starting position, and has station[i][1] liters of gas.

         The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.
         It uses 1 liter of gas per 1 mile that it drives.

         When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.

         What is the least number of refueling stops the car must make in order to reach its destination?
         If it cannot reach the destination, return -1.

         Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.
         If the car reaches the destination with 0 fuel left, it is still considered to have arrived.


         Example 1:
         Input: target = 1, startFuel = 1, stations = []
         Output: 0
         Explanation: We can reach the target without refueling.

         Example 2:
         Input: target = 100, startFuel = 1, stations = [[10,100]]
         Output: -1
         Explanation: We can't reach the target (or even the first gas station).

         Example 3:
         Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
         Output: 2
         Explanation:
         We start with 10 liters of fuel.
         We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
         Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
         and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
         We made 2 refueling stops along the way, so we return 2.

         Note:

         1 <= target, startFuel, stations[i][1] <= 10^9
         0 <= stations.length <= 500
         0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target

         Hard
     */


    /**
     * http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-871-minimum-number-of-refueling-stops/
     *
     * Heap
     * Time  : O(nlogn)
     * Space : O(n)
     *
     * Assume you can cheat, go as far as your gas allows, if you can't get to the destination,
     * find the one station with the max fuel among the  stations that you have passed so far,
     * refuel, keep going...
     *
     * Use heap to get the max fuel station you have passed along the way.
     */
    class Solution1 {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            int stops = 0;
            int cur = startFuel;

            /**
             * Max Heap !!!
             */
            PriorityQueue<Integer> pq = new PriorityQueue<>(500, Collections.reverseOrder());

            int i = 0;
            while (true) {
                if (cur >= target) {
                    return stops;
                }

                while (i < stations.length && stations[i][0] <= cur) {
                    pq.offer(stations[i][1]);
                    i++;
                }

                if (pq.isEmpty()) {
                    break;
                }

                cur += pq.poll();
                stops++;
            }

            return -1;
        }
    }

    /**
     * DP
     * Knapsack
     * Time  : O(n ^ 2)
     * Space : O(n)
     */
    class Solution2 {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            int n = stations.length;
            long[] dp = new long[n + 1];//!!! n + 1
            /**
             dp[i] : max dirs to go with i refules
             **/
            dp[0] = startFuel;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j >= 1; j--) {
                    /**
                     if we can reach station i with j -1 stops,
                     we can reach dp[j - 1] + stations[i][1] with j stops
                     **/
                    if (dp[j - 1] >= stations[i][0]) {
                        dp[j] = Math.max(dp[j], dp[j - 1] + (long)stations[i][1]);
                    }
                }
            }

            /**
             * !!! "i <= n"
             */
            for (int i = 0; i <= n; i++) {
                if (dp[i] >= target) {
                    return i;
                }
            }

            return -1;
        }
    }
}
