package algorithmbasic.leetcode.coding2;

import java.util.Scanner;

public class D_Total_rectangular_area {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        int x3 = sc.nextInt();
        int y3 = sc.nextInt();
        int x4 = sc.nextInt();
        int y4 = sc.nextInt();
        int mianJi = 0;

        // -- 矩形相交求面积的公式 --
        //矩形相交的条件 --> min(x2,x4) >= max(x1,x3) && min(y2,y4) >= max(y1,y3)
        //相交的面积 -->  ( min(x2,x4) - max(x1,x3) ) * ( (min(y2,y4) - max(y1,y3)) )
        int ans = 0;
        if (Math.min(x2, x4) >= Math.max(x1, x3) && Math.min(y2, y4) >= Math.max(y1, y3)) {
            int x = (Math.min(x2, x4) - Math.max(x1, x3)) * ((Math.min(y2, y4) - Math.max(y1, y3))); //相交的面机
            ans = (x2 - x1) * (y2 - y1) + (x4 - x3) * (y4 - y3) - x;
            System.out.println(ans);
        } else {
            ans = (x2 - x1) * (y2 - y1) + (x4 - x3) * (y4 - y3);
            System.out.println(ans);
        }
    }
}
