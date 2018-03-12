package Samples;

public class FacebookLeetCode {
//    Minimum Size Subarray Sum
//    Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
//    For example, given the array [2,3,1,2,4,3] and s = 7,
//    the subarray [4,3] has the minimal length under the problem constraint.
//    11
//            [1,2,3,4,5]
    public static void testSubarraySum() {
//        int[] nums = new int[] { 2, 3, 1, 2, 4, 3 };
//        int s = 7;
        int[] nums = new int[] { 1, 2, 3, 4, 5 };
        int s = 11;
        System.out.println("Subarray = " + new FacebookLeetCode().getSubarrayLength(s, nums));
//        System.out.println("Subarray = " + new FacebookLeetCode().minSubArrayLen(s, nums));
    }

    private int getSubarrayLength(int s, int [] nums) {
//        int[] sumArray = new int[nums.length];
        int minArrayLength = Integer.MAX_VALUE;
        int i = 0;
        int sum = 0;
        int runningCount = 0;
        while (i < nums.length - 1) {
            int j = i;
            while (sum < s && j <= nums.length - 1) {
                sum += nums[j];
                runningCount++;
                j++;
            }
            if (sum >= s){
                minArrayLength = Math.min(runningCount, minArrayLength);
            }
            runningCount = 0;
            sum = 0;
            i++;
        }
        return minArrayLength == Integer.MAX_VALUE ? 0 : minArrayLength;
    }
//
//    public int minSubArrayLen(int s, int[] a) {
//        if (a == null || a.length == 0)
//            return 0;
//
//        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
//
//        while (j < a.length) {
//            sum += a[j++];
//
//            while (sum >= s) {
//                min = Math.min(min, j - i);
//                sum -= a[i++];
//            }
//        }
//
//        return min == Integer.MAX_VALUE ? 0 : min;
//    }

//    Given preorder and inorder traversal of a tree, construct the binary tree.
//            For example, given
//    preorder = [3,9,20,15,7]
//    inorder = [9,3,15,20,7]
//    Return the following binary tree:
//
//            3
//            / \
//            9  20
//            /  \
//            15   7
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void testBuildTree() {
        int[] preorder = new int[] {3,9,20,15,7};
        int[] inorder = new int[] {9,3,15,20,7};
        TreeNode root = new FacebookLeetCode().buildTree(preorder, inorder);
        int[] preorder1 = new int[] {1,2};
        int[] inorder1 = new int[] {2,1};
        TreeNode root1 = new FacebookLeetCode().buildTree(preorder1, inorder1);
        int[] preorder2 = new int[] {1,2};
        int[] inorder2 = new int[] {1,2};
        TreeNode root2 = new FacebookLeetCode().buildTree(preorder2, inorder2);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        buildTree(root, 0, preorder, inorder);

        return root;
    }

    private void buildTree(TreeNode root, int i, int[] preorder, int[] inorder) {
        if (i >= preorder.length - 1) return;

        if (root.left == null && root.right == null) {
            root.left = new TreeNode((preorder[++i]));
            root.right = new TreeNode((preorder[++i]));
            if (preorder[i] > preorder[i - 1]) {
                buildTree(root.right, i, preorder, inorder);
            } else if (preorder[i] < preorder[i - 1]) {
                buildTree(root.left, i, preorder, inorder);
            }
        }

    }
}
