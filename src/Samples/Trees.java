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
//        root.right.left.left = new TreeNode(3);
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
}
