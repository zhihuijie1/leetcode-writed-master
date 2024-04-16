package algorithmbasic.leetcode.coding2;
/*
 * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
 * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
 * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
 * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
 * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
 * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
 * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的最小次数
 * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
 * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
 */

/*
 * 思路：想要计算购买m瓶可乐，投入硬币的最小次数 --> 先花面值大的纸币，再花面值小的纸币，这样即满足了小的投入次数，同时又满足了一个购买大的数量
 * 来到中间面值的时候，前面的面值可能会有剩余的张数。而前面的大面值之所以会有剩余一定是因为前面的钱凑不出一瓶可乐了。所以当前面值需要与前面的大面值一起组合来凑出
 * 第一瓶可乐，然后将找回的零钱分发下去，然后再用当前的面值的钱来买可乐，将找回的零钱分发下去，然后更新剩余的钱数与面值
 */
public class code2_Cola {
    // 要买的可乐数量，m
    // 100元有a张
    // 50元有b张
    // 10元有c张
    // 可乐单价x
    public static int putTimes(int m, int a, int b, int c, int x) {
        //统一一下商品的单价与数量 -- 一般使用的方法是数组或内部类
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        int preQianZhang = 0;// 之前面值的钱还剩下多少总张数
        int preQianRest = 0;// 之前面值的钱还剩下多少总钱数
        int puts = 0;//次数
        for (int i = 0; i < 3 && m != 0; i++) {
            //来到当前面值的时候，前面的面值可能会有剩余的张数。而前面的大面值之所以会有剩余一定是因为前面的钱凑不出一瓶可乐了
            //所以当前面值需要与前面的大面值一起组合来凑出第一瓶可乐

            //买第一瓶可乐需要当前面值的张数,需要向上取整
            int curZhang = (x - preQianRest + qian[i] - 1) / qian[i];
            if (curZhang > zhang[i]) {
                //当前面值的总钱数 + 之前剩余面值的总钱数之和 买一瓶可乐都不够
                //更新之前面值的钱还剩下多少总张数，之前面值的钱还剩下多少总钱数
                preQianZhang += zhang[i];
                preQianRest += zhang[i] * qian[i];
                continue;
            } else {
                //当前面值的总钱数 + 之前剩余面值的总钱数之和 可以购买第一瓶可乐
                //然后将找回的零钱分发下去，
                giveRest(qian, zhang, i + 1, (preQianRest + qian[i] * curZhang) - x, 1);/**--------------- great idea */
                //当前面值还剩几张
                zhang[i] = zhang[i] - curZhang;
                //更新买第一瓶可乐需要的次数
                puts += curZhang + preQianZhang;
                m--;
            }
            //然后再用当前的面值的钱来买可乐，将找回的零钱分发下去，然后更新剩余的钱数与面值

            //用当前的面值购买一瓶可乐需要多少张,向上取整
            int curBuyOneZhang = (x + qian[i] - 1) / qian[i];
            //当前面值可以买几瓶
            int curCanBuyOneNumbers = Math.min(zhang[i] / curBuyOneZhang, m);
            //将找回的零钱分发下去
            giveRest(qian, zhang, i + 1, qian[i] * curBuyOneZhang - x, curCanBuyOneNumbers);
            //当前面值去搞定可乐一共需要投几次币
            puts += curBuyOneZhang * curCanBuyOneNumbers;
            m -= curCanBuyOneNumbers;
            //更新剩余的钱数与面值
            //zhang[i]为剩余的张数
            zhang[i] -= curBuyOneZhang * curCanBuyOneNumbers;
            preQianRest = zhang[i] * qian[i];
            preQianZhang = zhang[i];
        }
        return m == 0 ? puts : -1;
    }

    //i: 最大的零钱面值下标
    //oneTimeRest: 找回的零钱
    //times: 有几次找零行为
    public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
        for (int j = i; j < 3; j++) {
            int curzhang = oneTimeRest / qian[j];
            zhang[j] += curzhang * times;/**--------------- great idea */
            oneTimeRest %= qian[j]; /**--------------- great idea */
        }
    }

    // ----------------- for  test ------------------

    public static void main(String[] args) {
        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("如果错误会打印错误数据，否则就是正确");
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = right(m, a, b, c, x);
            if (ans1 != ans2) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                System.out.println("wrong is -- " + ans1);
                System.out.println("right is -- " + ans2);
                break;
            }
        }
        System.out.println("test end");
    }

    // 暴力尝试，为了验证正式方法而已
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }
}
//test begin
//int m = 7;
//int a = 5;
//int b = 4;
//int c = 9;
//int x = 30;
//wrong is -- 16
//right is -- 7
//test end
