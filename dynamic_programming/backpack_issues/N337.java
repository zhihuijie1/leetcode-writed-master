package algorithmbasic.leetcode.dynamic_programming.backpack_issues;
public class N337 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int rob(TreeNode root) {
        int[] res = process(root);
        return Math.max(res[0], res[1]);
    }

    private static int[] process(TreeNode root) {
        int[] result = new int[2];
        //递归的截止条件
        if (root == null) {
            return result;
        }
        //竖向纵深
        int[] left = process(root.left);
        int[] right = process(root.right);
        //水平横向判断
        //不偷根节点：Max(左孩子不偷，左孩子偷) + Max(右孩子不偷，右孩子偷)
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //偷：左孩子不偷+ 右孩子不偷 + 当前节点偷
        result[1] = root.val + left[0] + right[0];

        return result;
    }
}

//树形dp