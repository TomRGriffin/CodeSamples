package Samples;

import java.util.HashMap;

public class UberLeetCode {

//    168. Excel Sheet Column Title
//    Given a positive integer, return its corresponding column title as appear in an Excel sheet.
//
//    For example:
//    1 -> A
//    2 -> B
//    3 -> C
//    26 -> Z
//    27 -> AA
//    28 -> AB
    public static void testConvertToTile() {
        System.out.println("Excel tile = " + new UberLeetCode().convertToTitle(1527));
    }
    public String convertToTitle(int n) {
        if (n == 0) return "";
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            char ch = (char) ((n - 1) % 26 + 65);
            n = (n - 1) / 26;
            sb.insert(0, ch);
        }
        return sb.toString();
    }

    public static void testTitleToNumber() {
        System.out.println("Excel tile = " + new UberLeetCode().titleToNumber("AA"));
    }
    public int titleToNumber(String s) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        if (n == 1) {
            return s.charAt(0) - 'A' + 1;
        } else {
            int res = 0;
//            3 + 1 * 26 + 26*26 + 26
//            3 + 2*26+26*26*0
            for (int i = n - 1; i >=     0; i--) {
                int charInt = s.charAt(i) - 'A' + 1;
                int nextPos = 1;
                for (int j = i; j < n - 1; j++) {
                    nextPos *= 26;
                }
                res += charInt * nextPos;
            }
            return res;
        }
    }
//
//    [       [".",".",".",".","5",".",".","1","."],
//            [".","4",".","3",".",".",".",".","."],
//            [".",".",".",".",".","3",".",".","1"],
//            ["8",".",".",".",".",".",".","2","."],
//            [".",".","2",".","7",".",".",".","."],
//            [".","1","5",".",".",".",".",".","."],
//            [".",".",".",".",".","2",".",".","."],
//            [".","2",".","9",".",".",".",".","."],
//            [".",".","4",".",".",".",".",".","."]]

    public static class RandomListNode {
         int label;
         RandomListNode next, random;
         RandomListNode(int x) { this.label = x; }
    };

    public static void testCopyRandomList() {
        RandomListNode node = new RandomListNode(0);
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        node.next = node1;
        node.random = node1;
        node1.next = node2;
        node1.random = node;
        node2.random = node2;

        RandomListNode copyNode = new UberLeetCode().copyRandomList(node);
    }

//    public RandomListNode copyRandomList(RandomListNode head) {
//        RandomListNode p = head;
//        RandomListNode dummy = new RandomListNode(0);
//        RandomListNode q = dummy;
//        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
//        while (p != null) {
//            q.next = new RandomListNode(p.label);
//            map.put(p, q.next);
//            p = p.next;
//            q = q.next;
//        }
//        p = head;
//        q = dummy;
//        while(p != null) {
//            q.next.random = map.get(p.random);
//            p = p.next;
//            q = q.next;
//        }
//        return dummy.next;
//    }
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode node = head;
        while(node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        node = head;
        while(node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }

}
