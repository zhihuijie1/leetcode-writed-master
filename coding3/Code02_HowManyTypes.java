package algorithmbasic.leetcode.coding3;

import java.util.Arrays;
import java.util.HashSet;

/*
 * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，
 * 如果其中某两个字符串，所含有的字符种类完全一样，就将两个字符串算作一类 比如：baacba和bac就算作一类
 * 虽然长度不一样，但是所含字符的种类完全一样（a、b、c） 返回arr中有多少类？
 *
 */

/*
 * 思路：制作一个简化器，比如说 “baacba”-->通过简化器变为 “bac”或“abc”
 * a_z一共26个字母，一个int类型有4个字节32位，所以可以用一个int的二进制作为简化器
 */

public class Code02_HowManyTypes {
    public static int types1(String[] arr) {
        int N = arr.length;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            char[] str = arr[i].toCharArray();
            int M = str.length;
            int key = 0;// 对当前字符串进行简化的简化器

            for (int j = 0; j < M; j++) { /**--------------------- great idea */
                key |= (1 << str[j] - 'a');
            }
            set.add(key);
        }
        return set.size();
    }

    public static int types2(String[] arr) {
        int N = arr.length;
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            char[] str = arr[i].toCharArray();
            int M = str.length;
            boolean[] map = new boolean[26];  /**----------简化器------------ great idea start */

            for (int j = 0; j < M; j++) {
                map[str[j] - 'a'] = true;
            }

            String key = "";
            for (int j = 0; j < 26; j++) {
                if (map[j]) {
                    key += String.valueOf((char) ('a' + j));
                }
            }

            set.add(key); /**------------------------- great idea end */
        }
        return set.size();
    }
}
