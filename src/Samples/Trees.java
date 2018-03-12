package Samples;

import apple.laf.JRSUIUtils;
import org.omg.CORBA.MARSHAL;

import java.util.ArrayList;
import java.util.List;

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
}
