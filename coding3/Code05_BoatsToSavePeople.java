package algorithmbasic.leetcode.coding3;

import java.util.Arrays;

public class Code05_BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0 || limit <= 0) {
            return 0;
        }
        Arrays.sort(people);
        int N = people.length;
        int L = 0; //刚开始的时候指向小于 limit/2 但是最近的位置
        int R = 0; //刚开始的位置在 L的右侧
        int[] isMatch = new int[N];
        for (int i = 0; i < N; i++) {
            if (people[i] <= limit / 2) {
                L++;
            } else {
                break;
            }
        }
        L--;
        R = L + 1;
        //用L主动去匹配R，
        while (L >= 0 && R < N) {

            if (people[L] + people[R] <= limit) {
                int begin = R;
                int end = R;
                while (people[L] + people[R] <= limit) { //L R可以匹配
                    R++;
                    end = R;
                }
                for (int i = 0; i - (end - begin) >= 0 && i < end - begin + 1; i++) {
                    L -= i;
                    isMatch[L] = 1;
                    isMatch[begin + i] = 1;
                }
            } else {
                while (people[L] + people[R] > limit) {
                    L--;
                }
            }
        }

        int ans = 0;
        int error = 0;
        int right = 0;

        if (R < N) {
            for (int i = 0; i < R; i++) {
                if (isMatch[i] == 1) {
                    right++;
                } else {
                    error++;
                }
            }
            return right / 2 + (error + error - 1) / 2 + N - R;
        } else {
            for (int i = 0; i < N; i++) {
                if (isMatch[i] == 1) {
                    right++;
                } else {
                    error++;
                }
            }
            return right / 2 + (error + error - 1) / 2;
        }
    }
}
