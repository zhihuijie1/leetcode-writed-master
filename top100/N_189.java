package algorithmbasic.leetcode.top100;

import java.util.Arrays;

public class N_189 {
    public static void rotate(int[] nums, int k) {
        int N = nums.length;
        int m = k <= N ? k : k % N;
        if(m == N) {
            System.out.println(Arrays.toString(nums));
        }
        int[] newNums = new int[N];
        int index = N - k;
        int i = 0;
        while(index < N && i < N) {
            newNums[i++] = nums[index++];
        }
        index = 0;
        while(index < N - k && i < N) {
            newNums[i++] = nums[index++];
        }
        System.out.println(Arrays.toString(newNums));
    }

    public static void main(String[] args) {
        int[] nums = {-1,-100,3,99};
        int k = 2;
        rotate(nums, k);
    }
}
