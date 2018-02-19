package Samples;

import org.omg.CORBA.MARSHAL;

public class Trees {
    public static class Node {
        Node left, right;
        int data;
        public Node(int data) {
            this.data = data;
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
            System.out.println(data);
            if (right != null) {
//                System.out.println("Search right tree");
                right.printInOrder();
            }
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

        public int height(Node node) {
            if (node == null) {
                return 0;
            }
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    public static void testTreeInOrder() {
        int[] array = new int[] {9, 5, 8, 1, 3, 7, 4, 2, 6};
        Node root = new Node(5);
        for (int number : array) {
            root.insert(number);
        }
//        root.printInOrder();
        root.printPreOrder();
    }
}
