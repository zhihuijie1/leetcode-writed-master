package algorithmbasic.leetcode.top100;

public class N_543 {
    static class TreeNode {
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


    static int maxPathLength = -1;

    public static int diameterOfBinaryTree(TreeNode root) {
        process(root);
        return maxPathLength;
    }


    // 返回以当前节点为根节点的树的深度
    private static int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int lDeep = process(root.left); // 1
        int rDeep = process(root.right); // 0
        maxPathLength = Math.max(lDeep + rDeep, maxPathLength); // 1
        return Math.max(lDeep, rDeep) + 1; // 2
    }
}
