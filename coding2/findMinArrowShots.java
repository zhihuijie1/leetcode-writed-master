package algorithmbasic.leetcode.coding2;

//https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane.
 * The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon
 * whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
 * There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely,
 * bursting any balloons in its path.
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 */
public class findMinArrowShots {
    public int findMinArrowShots(int[][] points) {
        return intervalSchedule(points);
    }


    //贪心算法 - 区间调度问题 - 计算不相交区间的个数
    private static int intervalSchedule(int[][] intervals) {
        //根据end进行升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if(a[1] < b[1]) {
                    return -1;
                }else if(a[1] == b[1]) {
                    return 0;
                }else {
                    return 1;
                }
            }
        });

        int count = 1; //被x_end覆盖到的区间有几个
        int x_end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(x_end < intervals[i][0]) {
                count++;
                x_end = intervals[i][1];
            }
        }
        return count;
    }
}
