package algorithmbasic.leetcode.coding3;


/*
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，
 * 并返回该子网格中的元素数量。如果不存在，则返回 0。
 */

/*
 * 思路：题目给定的数据规模是100 根据最大数据规模是10^8可以大致的猜到，复杂度是N^3是可以的。
 *      在一个矩阵中查找所有长方形的时间复杂度是N^4 - 首先随机找一个点(左上)的时间复杂度是N^2 然后再随机找一个点(右下)的时间复杂度也是N^2
 *      在一个矩阵中查找所有正方形的时间复杂度是N^3 - 首先随机找一个点(左上)的时间复杂度是N^2 然后再随机找一个点(右下)的时间复杂度是N
 *
 *      现在在矩阵中随机找到一个正方形的时间复杂度已经达到N^3了，那么判断边界构成的子网面积的复杂度必须是O(1)
 *      所以要对数组进行预处理，想要的效果是随机一个点(i,j)就可以得到其右边最长的连续1有几个，其下边最长连续的1有几个。
 *      经预处理之后，我可以快速的判断出当前的正方行是否是边长为1环成的
 */
public class Code03_Largest1BorderedSquare {
    public static int largest1BorderedSquare(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int ans = 0;
        //对数组进行预处理 时间复杂度是N^2
        int[][] right = rightPreprocessing(m);
        int[][] down = downPreprocessing(m);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                //[i,j]是左上点
                for (int size = 1; size <= Math.min(N - i, M - j); size++) {
                    if (right[i][j] >= size && down[i][j] >= size && down[i][j + size - 1] >= size && right[i + size - 1][j] >= size) {
                        ans = Math.max(ans, size * size);
                    }
                }
            }
        }
        return ans;
    }

    //向右进行预处理
    private static int[][] rightPreprocessing(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        int[][] right = new int[N][M];

        //从右往左进行预处理
        for (int i = 0; i < N; i++) {
            for (int j = M - 1; j >= 0; j--) {
                if (m[i][j] == 0) {
                    right[i][j] = 0;
                } else {
                    right[i][j] = j + 1 < M && right[i][j + 1] != 0 ? 1 + right[i][j + 1] : 1;
                }
            }
        }
        return right;
    }

    //向下进行预处理
    private static int[][] downPreprocessing(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        int[][] down = new int[N][M];

        //从下往上进行预处理
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 0) {
                    down[i][j] = 0;
                } else {
                    down[i][j] = i + 1 < N && down[i + 1][j] != 0 ? 1 + down[i + 1][j] : 1;
                }
            }
        }
        return down;
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 0, 0, 1}};
        int ans = largest1BorderedSquare(arr);
        System.out.println(ans);
    }
}