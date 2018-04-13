package Samples;

import java.util.*;

public class Trees {
    public static class Node {
        Node left, right;
        int data;
        public Node(int data) {
            this.data = data;
        }

        public void insertRandom(int value) {
            if (left == null) {
                left = new Node(value);
            } else if (right == null) {
                right = new Node(value);
            } else if (height(left) > height(right)) {
                right.insertRandom(value);
            } else {
                left.insertRandom(value);
            }

        }

        public void insert(int value) {
            if (value <= data) {
                if (left == null) {
                    System.out.println("Creating left node with value = " + value);
                    left = new Node(value);
                } else {
                    System.out.println("Inserting left node with value = " + value);
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    System.out.println("Creating right node with value = " + value);
                    right = new Node(value);
                } else {
                    System.out.println("Inserting right node with value = " + value);
                    right.insert(value);
                }
            }
        }

        public boolean contains(int value) {
            if(value == data) {
                return true;
            } else if (value <= data) {
                if (left == null) {
                    return false;
                } else {
                    return left.contains(value);
                }
            } else {
                if (right == null) {
                    return false;
                } else {
                    return right.contains(value);
                }
            }
        }

        public void printInOrder() {
            if (left != null) {
//                System.out.println("Search left tree");
                left.printInOrder();
            }
            System.out.println(", " +data);
            if (right != null) {
//                System.out.println("Search right tree");
                right.printInOrder();
            }
        }

        public void printPostOrder() {
            if (right != null) {
//                System.out.println("Search right tree");
                right.printInOrder();
            }
            if (left != null) {
//                System.out.println("Search left tree");
                left.printInOrder();
            }


            System.out.println(", " +data);
        }

        public void printPreOrder() {
            System.out.println(data);

            if (left != null) {
//                System.out.println("Search left tree");
                left.printInOrder();
            }

            if (right != null) {
//                System.out.println("Search right tree");
                right.printInOrder();
            }
        }

        public static int height(Node node) {
            if (node == null) {
                return 0;
            }
            return Math.max(height(node.left), height(node.right)) + 1;
        }
        public static int size(Node node) {
            if (node == null) {
                return 0;
            }
            return size(node.left) + size(node.right) + 1;
        }
    }

    public static void testTreeInOrder() {
        int[] array = new int[] {9, 5, 8, 1, 3, 7, 4, 2, 6};
        Node root = new Node(5);
        for (int number : array) {
            root.insert(number);
        }
//        root.printInOrder();
//        root.printPreOrder();
        root.printPostOrder();
    }

    public static void testValidBST() {
        int[] array = new int[] {2, 3};
        Node root = new Node(1);
        for (int number : array) {
            root.insert(number);
        }
        System.out.println("IsValidBst = " + checkValidBST(root));
    }

    public static boolean checkValidBST(Node root) {
        if (root == null) {
            return false;
        }
        if(root.left == null && root.right == null) return true;
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isValidBST(Node root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.data <= min || root.data >= max) {
            return false;
        }

        boolean leftTreeValid = isValidBST(root.left, min, root.data);
        boolean rightTreeValid = isValidBST(root.right, root.data, max);
        return leftTreeValid && rightTreeValid;
    }


    public static boolean isValidBST(Node root) {
        int numberOfItems =  Node.size(root);
        int[] values = new int[numberOfItems];
        getValues(root, values);
        boolean valid = true;
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] >= values[i + 1]) {
                return false;
            }
        }
        return valid;
    }
    static int index = 0;

    static void getValues(Node root, int[] values) {
        if (root == null) {
            return;
        }
        getValues(root.left, values);
        values[index] = root.data;
        index++;
        getValues(root.right, values);
    }

//    Find Leaves of Binary Tree
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public static void testFindLeaves() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        System.out.println("Tree leaves = " + new Trees().findLeaves(root));
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        findLeaves(lists, root);
        return lists;
    }

    public int findLeaves( List<List<Integer>> list, TreeNode root) {
        if (root == null) return  -1;
        int leftLevel = findLeaves(list, root.left);
        int rightLevel = findLeaves(list, root.right);
        int level = Math.max(leftLevel, rightLevel) + 1;
        if(list.size() == level) {
            list.add(new ArrayList<Integer>());
        }
        list.get(level).add(root.val);
        root.left = root.right = null;
        return level;
    }
        //[1,-1,2,null,null,3]

//    [1,2,3,4,null,null,4]
// [1, 2, 3, null, null, 4, 5]
//                5
//              /  \
//             2    3
//                /  \
//               2    4
//             /  \
//            3    1
//    [5,2,3,null,null,2,4,3,1]
//    [2,1,3]
    public static void testBFSTraversal() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(2);
//        root.right.right = new TreeNode(4);
//        root.right.left.left = new new TreeNode(1);TreeNode(3);
//        root.right.left.right = new TreeNode(1);
//        root.right.right = new TreeNode(5);
        Trees trees = new Trees();
//        String treeData = trees.serialize(root);
//        System.out.println(trees.serialize(root));
//        TreeNode node = trees.deserialize(treeData);
//        System.out.println(trees.serialize(node));

        String treeData = trees.serializeBST(root);
        System.out.println(treeData);
        TreeNode node = trees.deserializeBST(treeData);
        System.out.println(trees.serializeBST(node));
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    public void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null").append(",");
        } else {
            sb.append(String.valueOf(root.val)).append(",");
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new ArrayDeque<>(Arrays.asList(data.split(",")));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        while (!nodes.isEmpty()){
            String val = nodes.poll();
            if (val.equals("null")) {
                return null;
            } else {
                TreeNode node = new TreeNode(Integer.valueOf(val));
                node.left = buildTree(nodes);
                node.right = buildTree(nodes);
                return node;
            }
        }
        return null;
    }

    public String serializeBST(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildStringBST(root, sb);
        return sb.toString();
    }
    public void buildStringBST(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(String.valueOf(root.val)).append(",");
        buildStringBST(root.left, sb);
        buildStringBST(root.right, sb);

    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeBST(String data) {
        int[] index = new int[] { 0 };
        return buildTreeBST(data.split(","), index, Integer.MAX_VALUE);
    }

    private TreeNode buildTreeBST(String[] nodes, int[] index, int max) {
        System.out.println("index[0] = " + index[0]);
        if (index[0] >= nodes.length || Integer.valueOf(nodes[index[0]]) >= max) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(nodes[index[0]++]));
        node.left = buildTreeBST(nodes, index, node.val);
        node.right = buildTreeBST(nodes, index, max);
        return node;
    }
//    public String serialize(TreeNode root) {
//        StringBuilder builder = new StringBuilder();
//        buildString(root, builder);
//        builder.append(',');
//        return builder.toString();
//    }
//    private void buildString(TreeNode node, StringBuilder builder) {
//        builder.append(',');
//        if (node != null) {
//            builder.append(node.val);
//            buildString(node.left, builder);
//            buildString(node.right, builder);
//        }
//    }
//    // BFS traversal
////    private String serialize(TreeNode root) {
////        StringBuilder stringBuilder = new StringBuilder();
////        buildString(root, stringBuilder);
////        return stringBuilder.toString();
////    }
////    private void buildString(TreeNode root, StringBuilder stringBuilder) {
////        if (root == null) {
////            stringBuilder.append("null").append(",");
////        } else {
////            stringBuilder.append(root.val).append(",");
////            buildString(root.left, stringBuilder);
////            buildString(root.right, stringBuilder);
////        }
////    }
//    public TreeNode deserialize(String data) {
//        if (data.isEmpty()) return null;
////        String[] treeArray = data.split(",");
////        Queue<String> queue = new ArrayDeque<>(Arrays.asList(treeArray));
////        return buildTree(queue);
//        return buildTree(data, new int[1]);
//    }
//    private TreeNode buildTree(Queue<String> queue) {
//        if (!queue.isEmpty()) {
//            String value = queue.poll();
//            if (value.equals("null")) {
//                return null;
//            } else {
//                TreeNode node = new TreeNode(Integer.valueOf(value));
//                node.left = buildTree(queue);
//                node.right = buildTree(queue);
//                return node;
//            }
//        }
//        return null;
//    }
//    private TreeNode buildTree(String data, int[] index) {
//        index[0]++;
//        int value = data.charAt(index[0]) - '0';
//        if (value == ',' - '0') return null;
//        int mult = value == '-' - '0' ? -1 : 1;
//        if (value == '-' - '0') value = data.charAt(++index[0]) - '0';
//        while (data.charAt(++index[0]) != ',')
//            value = value*10 + data.charAt(index[0]) - '0';
//        value *= mult;
//        TreeNode node = new TreeNode(value);
//        node.left = buildTree(data, index);
//        node.right = buildTree(data, index);
//        return node;
//    }

//    private String serialize(TreeNode root) {
//        if (root == null) return "";
//        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(root.val);
//        nodeQueue.add(root);
//        while (!nodeQueue.isEmpty()) {
//            TreeNode node = nodeQueue.poll();
//            if (node != null) {
//                if (node.left == null) {
//                    stringBuilder.append(", null");
//                } else {
//                    stringBuilder.append(", ").append(node.left.val);
////                    if(node.left.left != null || node.left.right != null) {
//                        nodeQueue.add(node.left);
////                    }
//                }
//                if (node.right == null) {
//                    stringBuilder.append(", null");
//                } else {
//                    stringBuilder.append(", ").append(node.right.val);
////                    if(node.right.left != null || node.right.right != null) {
//                        nodeQueue.add(node.right);
////                    }
//                }
//            }
//        }
//        return stringBuilder.toString();
//    }
//
//    // [1, 2, 3, null, null, 4, 5]
//    // [0, 1, 2,    3, 4,    5, 6]
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if (data.isEmpty()) return null;
//        String[] treeArray = data.split(", ");
//        TreeNode root = new TreeNode(Integer.valueOf(treeArray[0]));
//        return insertInLevelOrder(root, treeArray, 1);
//    }
//    //    [5,2,3,null,null,2,4,3,1]
//    private TreeNode insertInLevelOrder(TreeNode root, String[] treeArray, int index) {
//        if (root == null) return null;
//        int i = index;
//        if (i < treeArray.length) {
//            String value = treeArray[i++];
//            if (!value.equals("null")) {
//                root.left = new TreeNode(Integer.valueOf(value));
//            }
//            if (i < treeArray.length) {
//                value = treeArray[i];
//                if (!value.equals("null")) {
//                    root.right = new TreeNode(Integer.valueOf(value));
//                }
//            }
//            root.left = insertInLevelOrder(root.left, treeArray,  2 * index + 1);
//            root.right = insertInLevelOrder(root.right, treeArray,  2 * (index + 1) + 1);
//        }
//        return root;
//    }
//    private TreeNode insertInLevelOrder(TreeNode root, String[] treeArray, int level, int index) {
//        if (root == null) return null;
//        int i = level + index;
//        if (i < treeArray.length) {
//            String value = treeArray[i++];
//            if (!value.equals("null")) {
//                root.left = new TreeNode(Integer.valueOf(value));
//            }
//            value = treeArray[i];
//            if (!value.equals("null")) {
//                root.right = new TreeNode(Integer.valueOf(value));
//            }
//            root.left = insertInLevelOrder(root.left, treeArray, level + 1, index + 1);
//            root.right = insertInLevelOrder(root.right, treeArray, 2 * level + 1, 2 * (index + 1));
//        }
//        return root;
//    }

//    669. Trim a Binary Search Tree
//    Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
//            Example 1:
//    Input:
//            1
//            / \
//            0   2
//    L = 1
//    R = 2
//    Output:
//            1
//            \
//            2

    public static void testTrimBST() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);
//        TreeNode root = new TreeNode(2);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(3);
        Trees trees = new Trees();
        System.out.println(trees.serializeBST(root));
        TreeNode node = trees.trimBST(root, 3, 4);
        System.out.println(trees.serializeBST(node));
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        if (root.val < L || root.val > R) {
            if (root.left != null && root.left.val >= L && root.left.val <= R) {
                root = root.left;
            } else if (root.right != null && root.right.val >= L && root.right.val <= R) {
                root = root.right;
            } else {
                root = null;
            }
        }
        return root;
    }

    public static void testlowestCommonAncestor() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        TreeNode p = root.left.left ;
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        TreeNode q = null;//root.left.right.right;
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        System.out.println("LCA = " + new Trees().lowestCommonAncestor(root, p, q).val);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else {
            return left != null ? left : right;
        }
    }

    public static void testIsSymmetric() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
//        root.right = new TreeNode(2);
////        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(3);
////        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(3);
        System.out.println(("Is Symmetric = " + new Trees().isSymmetric(root)));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        if ((root.left != null && root.right == null) || (root.left == null && root.right != null)) return false;
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left.val != right.val) return false;
        if ((left.left != null && right.right == null) || (left.left == null && right.right != null)) return false;
        if ((left.right != null && right.left == null) || (left.right == null && right.left != null)) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public static void testHasSum() {
//        [5,4,8,11,null,13,4,7,2,null,null,null,1]
//        22
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
//        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        System.out.println(("Has path sum = " + new Trees().hasPathSum(root, 22)));
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum == 0) {
            return true;
        }
        sum -= root.val;
        if (sum == 0) return true;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
/*
    Write a function to determine the max value in a binary tree (not a binary search tree).
            3
            / \
            6   10
            / \  | \
            4   2 7  3
    maxValue -> 10
            */

    int maxValue = Integer.MIN_VALUE;
    public static void testMaxValue() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(3);
        Trees trees = new Trees();
        trees.findMaxValue(root);
        System.out.println(("Max value = " + trees.maxValue));
    }

    private void findMaxValue(TreeNode root) {
        if (root == null) return;
        maxValue = Math.max(root.val, maxValue);
        if (root.left != null) {
            findMaxValue(root.left);
        }
        if (root.right != null) {
            findMaxValue(root.right);
        }
    }

    /*
Write a function to determine the value of the maximum valued path from root to a leaf.

    *3
   / \
  6   *10
 / \  | \
4   2 *7 3

maxValuePathRootToLeaf -> 20

   *3
   / \
  6  *-10
 / \  | \
4   2 7  *29

maxValuePathRootToLeaf -> 22

   *3
   / \
  6  *10
 / \  | \
4   2 7 *3
      |
     -14
maxValuePathRootToLeaf -> 16
 */

/*
    *-3
    / \
  *-6  -10
  / \  | \
-4 *-2 -7 0
maxValuePathRootToLeaf -> -11
*/

    private int maxVal = Integer.MIN_VALUE;

    public static void testFindMax() {
        TreeNode root = new TreeNode(-3);
        root.left = new TreeNode(-6);
        root.right = new TreeNode(-10);
        root.left.left = new TreeNode(-4);
        root.left.right = new TreeNode(-2);
        root.right.left = new TreeNode(-7);
        root.right.right = new TreeNode(0);
        Trees trees = new Trees();
//        trees.findMax(root);
        trees.maxPathSum(root);
        System.out.println(("Max value = " + trees.maxVal));
//        System.out.println(("Max path sum = " + trees.maxSum));
    }

    public int findMax(TreeNode node) {
        this.maxVal = Integer.MIN_VALUE;
        this.process(node,0);
        return maxVal;
    }

    private void process(TreeNode node, int pathVal) {
        int curPathVal = node.val + pathVal;

        if ( node.left == null && node.right == null) {
            if (curPathVal > maxVal) {
                maxVal = curPathVal;
            }
        }

        if (node.left != null ) {
            process (node.left,curPathVal);
        }

        if (node.right != null ) {
            process (node.right,curPathVal);
        }
    }

    int maxSum;
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        findMaxPathSum(root);
        return maxSum;
    }
    private int findMaxPathSum(TreeNode root) {
        if(root == null) return 0;
        int leftSum = findMax(root.left);
        int rightSum = findMax(root.right);
        maxSum = Math.max(root.val + leftSum + rightSum, maxSum);
        int ret = root.val + Math.max(leftSum, rightSum);
        return ret > 0 ? ret : 0;
    }


    public static void testBinaryTreePaths() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        Trees trees = new Trees();
        List<String> paths = trees.binaryTreePaths(root);
        System.out.println("Binary paths = " + paths.toString());
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        String path = "";
        if (root != null) binaryTreePaths(root, paths, path);
        return paths;
    }

    private void binaryTreePaths(TreeNode root, List<String> paths, String path) {
        if(root.left != null) {
            binaryTreePaths(root.left, paths, path + root.val + "->");
        }
        if(root.right != null) {
            binaryTreePaths(root.right, paths, path + root.val + "->");
        }
        if (root.left == null && root.right == null) {
            paths.add(path + root.val);
        }
    }

}
