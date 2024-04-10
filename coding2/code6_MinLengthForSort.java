package algorithmbasic.leetcode.coding2;


//给定一个整数数组 nums，需要找到一个连续的子数组，如果只对该子数组进行非递减排序，则整个数组将按非递减顺序排序。
//返回最短的子数组并输出其长度。
//Example 1:
//Input: nums = [2,6,4,8,10,9,15]
//Output: 5
//Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.


//思路：规定一个LeftMax变量，存储左侧的最大值，当从左向右遍历的时候，如果leftMax > cur 当前值画❌。
//     -目的:最右侧❌的右面一定是升序的，不需要动。
//     规定一个rightMin变量，存储右侧的最小值，当从右向左遍历的时候，如果rightMin < cur 当前值画❌。
//     -目的：最左侧❌的左侧一定是降序的，不需要动。
//     两个❌中间的就是需要排序的

public class code6_MinLengthForSort {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int leftMax = nums[0];
        int rightestCross = 0; //记录最右侧❌的下标
        for (int i = 0; i < n; i++) {
            if (nums[i] < leftMax) {
                rightestCross = i;
                continue;
            }
            leftMax = Math.max(nums[i], leftMax);
        }
        //rightestCross记录着最右侧❌的下标

        int rightMin = nums[n - 1];
        int leftestCross = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > rightMin) {
                leftestCross = i;
                continue;
            }
            rightMin = Math.min(rightMin, nums[i]);
        }
        //leftestCross记录着最左侧❌的下标
        //1 2 3 4 -> return 0
        //1    -> return 0
        //1 2 6 5 4 3 8 9 -> return 4

        return rightestCross - leftestCross <= 0 ? 0 : rightestCross - leftestCross + 1;
    }
}
