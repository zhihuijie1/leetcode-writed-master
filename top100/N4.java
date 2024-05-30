package algorithmbasic.leetcode.top100;

public class N4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 双指针法
        int n = nums1.length;
        int m = nums2.length;
        // p1 p2分别指向两个数组头节点
        int p1 = 0;
        int p2 = 0;
        int count = 0;
        // m1往前冲 m2记录m1的前一个
        int m1 = 0;
        int m2 = 0;

        while (count <= (n + m) / 2) {

            m2 = m1;

            if (p1 < n && p2 < m) {

                if (nums1[p1] < nums2[p2]) {

                    m1 = nums1[p1++];

                } else {

                    m1 = nums2[p2++];
                }
            } else {

                if (p1 < n) {

                    m1 = nums1[p1++];

                } else {

                    m1 = nums2[p2++];
                }
            }

            count++;
        }

        if ((n + m) % 2 == 0) {
            return (m1 + m2) / 2.0;
        } else {
            return (double) m1;
        }
    }
}

/**
 初始化两个指针 i 和 j，最初都设置为0。

 在每个步骤中向前移动对应于较小值的指针。

 继续移动指针，直到处理完元素总数的一半。

 根据 i 和 j 指向的值计算并返回中位数。
 */
