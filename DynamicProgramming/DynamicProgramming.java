import java.util.ArrayList;
import java.util.HashMap;

public class DynamicProgramming {
    private HashMap<Integer[][], Integer> hiLoStressMap;

    // Every day for the rest of the year, you're going to be given a choice between two jobs to do:
    // one that is LOW stress, and one that is HIGH stress. Each job pays out a dollar amount;
    // *usually* the high stress jobs pay more. However, after doing a high stress job, you need to
    // REST for a day.

    // Given a list of all the payouts for all the low stress and high stress jobs,
    // what is the most amount of money you can get?

    // You can assume lowPayouts.length == highPayouts.length
    public static int hiLoStress(int[] lowPayouts, int[] highPayouts) {
        HashMap<Integer, Integer> rewardsMap = new HashMap<Integer, Integer>();
        // This is a hashMap of money BY a certain day, the money amount
        // Day 0 we have nothing
        rewardsMap.put(0, 0);
        // Only way we can get to day 2 and be able to choose something is if we take the low paying
        // job on day 1
        rewardsMap.put(1, lowPayouts[0]);
        return hiLoStressHelper(lowPayouts, highPayouts, 2, rewardsMap);
    }

    private static int hiLoStressHelper(int[] lowPayouts, int[] highPayouts, int day,
            HashMap<Integer, Integer> rewardsMap) {
        /*
         * Ok writing out these instructions right now So we have a day x, we look at days x - 1 and
         * x - 2 in the HashMap The way to get to day x is either day x - 2 + high paying or day x -
         * 1 + lowPaying // We check which is bigger and add that to the HashMap
         */
        int withHighPaying = rewardsMap.get(day - 2) + highPayouts[day - 2];
        if (day >= lowPayouts.length + 1) {
            return Math.max(withHighPaying, rewardsMap.get(day - 1));
        }
        int withLowPaying = rewardsMap.get(day - 1) + lowPayouts[day - 1];
        rewardsMap.put(day, Math.max(withHighPaying, withLowPaying));
        return hiLoStressHelper(lowPayouts, highPayouts, day + 1, rewardsMap);
        // Recursive method
        // // Determine what job we took today.
        // // Add to our salary
        // // check the two paths
        // if (day >= lowPayouts.length) {
        // // Out of time
        // return currSalary;
        // }
        // if (tookHighStress) {
        // currSalary += highPayouts[day];
        // } else {
        // currSalary += lowPayouts[day];
        // }
        // day++;
        // if (tookHighStress) {
        // // We resting
        // day++;
        // }
        // return Math.max(hiLoStressHelper(lowPayouts, highPayouts, day, currSalary, true),
        // hiLoStressHelper(lowPayouts, highPayouts, day, currSalary, false));
    }


    // You are partaking in a scavenger hunt!
    // You've gotten a secret map to find many of the more difficult
    // items, but they are only available at VERY specific times at
    // specific places. You have an array, times[], that lists at which
    // MINUTE an item is available, in increasing order.
    // Items in the ScavHunt are worth varying numbers of points.
    // You also have an array, points[], same length as times[],
    // that lists how many points each of the corresponding items is worth.
    // Problem is: to get from one location to the other takes 5 minutes,
    // so if there is an item, for example, available at time 23 and another
    // at time 27, it's just not possible for you to make it to both: you'll
    // have to choose!
    // Write a method that returns the maximum POINTS you can get.
    public static int scavHunt(int[] times, int[] points) {
        HashMap<Integer, Integer> rewardsMap = new HashMap<Integer, Integer>();
        int index = 0;
        rewardsMap.put(times[0], 0);
        return scavHuntHelper(times, points, index, times[index], rewardsMap);
    }

    private static int scavHuntHelper(int[] times, int[] points, int currIndex, int minute,
            HashMap<Integer, Integer> pointsMap) {
        // Broken one
        // ArrayList<Integer> maxRewards = new ArrayList<Integer>();
        // int index = currIndex - 1;
        // while (index >= 0 && minute - times[index] < 5) {
        //     // Only way we could have gotten here is if we were at these minutes and didn't take
        //     // treasure
        //     maxRewards.add(pointsMap.get(times[index]));
        //     index--;
        // }
        // if (index != -1) {
        //     maxRewards.add(pointsMap.get(times[index]) + points[index]);
        // }
        // int max = maxRewards.get(0);
        // for (int i = 1; i < maxRewards.size(); i++) {
        //     max = Math.max(max, maxRewards.get(i));
        // }
        // pointsMap.put(minute, max);
        // currIndex++;
        // if (currIndex >= times.length) {
        //     maxRewards.clear();
        //     index = currIndex - 1;
        //     while (minute - times[index] < 5) {
        //         maxRewards.add(pointsMap.get(times[index]) + points[index]);
        //         index--;
        //     }
        //     max = maxRewards.get(0);
        //     for (int i = 1; i < maxRewards.size(); i++) {
        //         max = Math.max(max, maxRewards.get(i));
        //     }
        //     return max;
        // }
        return scavHuntHelper(times, points, currIndex, times[currIndex], pointsMap);
    }



    /*
     * Uses memoization to calculate the route which grants the most cookies, starting at [0][0],
     * only going right or down at each point
     */
    public static int dynamicCookies(int[][] cookieGrid) {
        return 0;
    }



}
