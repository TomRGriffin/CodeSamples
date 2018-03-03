package Samples;

public class MediumLeetCode {
//    Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
//    The root is the maximum number in the array.
//    The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
//    The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
//    Construct the maximum tree by the given array and output the root node of this tree.
//
//            Example 1:
//    Input: [3,2,1,6,0,5]
//    Output: return the tree root node representing the following tree:
//
//            6
//            /   \
//            3     5
//            \    /
//            2  0
//            \
//            1
//    Note:
//    The size of the given array will be in the range [1,1000].

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
//
//    public void addToLeftTree(TreeNode node, int value) {
//        if (node.val < value) {
//            if (node.left == null) {
//                node.left = new TreeNode(value);
//            } else {
//                if (node.left.val > value)
//            }
//        }
//    }
//
//    public TreeNode constructMaximumBinaryTree(int[] nums) {
//        int index = findMax(nums);
//        int maxValue = nums[index];
//        TreeNode root = new TreeNode(maxValue);
//        for (int i = 0; i < nums.length ; i++) {
//            if (index < i) {
//                addToLeftTree(root, nums[i]);
//            } else {
//                addToRightTree(root, nums[i]);
//            }
//        }
//        return root;
//    }
//    public static void testMaxBinaryTree() {
//        int[] nums = new int[] { 3,2,1,6,0,5 };
//        new MediumLeetCode().constructMaximumBinaryTree(nums);
//    }
}
