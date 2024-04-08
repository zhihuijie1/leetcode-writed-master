package algorithmbasic.leetcode.coding2;

/**
 * 【问题描述】
 * 哈沙德数是指在某个固定的进位制当中，可以被各位数字之和整除的正整 数。
 * 例如 126 是十进制下的一个哈沙德数，因为 (126) 10 mod (1+2+6) = 0 ；
 * 126 也是八进制下的哈沙德数，因为 (126) 10 = (176) 8 ， (126) 10 mod (1 + 7 + 6) = 0 ；
 * 同时 126 也是 16 进制下的哈沙德数，因为 (126) 10 = (7 e ) 16 ， (126) 10 mod (7 + e ) = 0 。
 * 小蓝认为，如果一个整数在二进制、八进制、十进制、十六进制下均为 哈沙德数，那么这个数字就是幸运数字，
 * 第 1 至第 10 个幸运数字的十进制表示 为：1 , 2 , 4 , 6 , 8 , 40 , 48 , 72 , 120 , 126 . . . 。
 * 现在他想知道第 2023 个幸运数 字是多少？你只需要告诉小蓝这个整数的十进制表示即可。
 */
public class B_lucky_Number {
    public static void main(String[] args) {
        long index = 127;
        int count = 11;
        while (count != 2024) {
            //十进制
            boolean isshi = isNum(index);

            //八进制
            boolean isba = isBa(index);

            //二进制
            boolean iser = isEr(index);

            //十六进制
            boolean isshiliu = isHex(index);

            if (isshi && isba && iser && isshiliu) {
                count++;
                //System.out.println(index);
            }
            index++;
        }
        System.out.println(--index);
    }

    //十进制
    private static boolean isNum(long n) {
        long k = n;
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n = n / 10;
        }
        return k % sum == 0 ? true : false;
    }

    //八进制判断
    private static boolean isBa(long n) {
        long k = n;
        int sum = 0;
        while (n != 0) {
            sum += n % 8;
            n = n / 8;
        }
        return k % sum == 0 ? true : false;
    }

    //二进制判断
    private static boolean isEr(long n) {
        long k = n;
        int sum = 0;
        while (n != 0) {
            sum += n % 2;
            n = n / 2;
        }
        return k % sum == 0 ? true : false;
    }

    //十六进制判断
    private static boolean isHex(long n) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        long k = n;
        int sum = 0;
        while (n != 0) {
            sum += arr[(int) (n % 16)];
            n = n / 16;
        }
        return k % sum == 0 ? true : false;
    }
}
