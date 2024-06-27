package algorithmbasic.leetcode.top100;

import java.util.Arrays;

public class N31 {
    //[1,2,3] [1,3,2],[2,1,3],[2.3,1],[3,1,2],[3,2,1]
    public void nextPermutation(int[] nums) {
        // 1：找到断点index

        // 2: 如果没有断点，说明这是当前数组的最后一个 -> 直接反转数组
        //    如果有断点 -> 在[index + 1, n - 1] 范围内，找到大于nums[index]的最小值，
        //    -> 交换
        //    -> 对其右侧进行由小到大的排序

        int N = nums.length;
        int d = N;// d -> 断点
        int i = N - 1;
        for(i = N - 1, d = i - 1; i > 0 && d >= 0; i--, d--) {
            if(nums[d] < nums[i]) {
                break;
            }
        }

        // 没有断点
        if(d < 0) {
            reverse(nums, 0, N - 1);
        }else {
            // 在[d + 1, n - 1] 范围内，找到大于nums[d]的最小值，
            int minIndex = find(nums, d + 1, N - 1, d);
            // 交换
            swap(nums, d, minIndex);
            // 对其右侧进行由小到大的排序，左闭右闭
            reverse(nums, d + 1, N - 1);
        }

    }


    private void reverse(int[] nums, int l, int r) {
        Arrays.sort(nums,l,r + 1);
    }

    private int find(int[] nums, int l, int r, int d) {
        int minIndex = -1;
        for(int i = l; i <= r; i++) {
            if(nums[i] > nums[d]) {
                if(minIndex == -1 || nums[i] < nums[minIndex]) {
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
