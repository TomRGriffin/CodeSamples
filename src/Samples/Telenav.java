//package Samples;
//
//public class Telenav {
//
//    Explain what does this code do and identify potential issues?
//
//    public class MainActivity extends Activity {
//        private final Object mLock = new Object();
//        private int value = 0;
//        // .. networkRequest initialization
//
//        protected void onCreate(Bundle bundle) {
//            new Thread() {
//                updateValue();
//            }.start();
//
//            Button b = findViewById(R.id.button);
//            b.setOnClickListener(new OnClickListener() {
//            b.setText(getValue());
//            });
//        }
//
//        private int getValue() {
//            synchronized(mLock) {
//                return value;
//            }
//        }
//
//        private void updateValue() {
//            synchronized (mLock) {
//                value = networkRequest.blockingCall();
//            }
//        }
//    }
//
//1. onCreate should be public
//2. Button b = (Button) findByView
//3. check not null
//            4. atmoic
//5. setText(stirng)
//6. if b is null, it will crash
//7. ANR
//
//
//    B.
//    How to modify singleton class TicketRepository such that it could become testable and at the same time preserve it's singleton property?
//
//    public class TicketRepository {
//        private static TicketRepository instance = null;
//        private TicketRepository() {}
//
//        public static TicketRepository getInstance() {
//            if (instance == null) {
//                instance = new TicketRepository();
//            }
//            return instance;
//        }
//
//        public Ticket findTicket(String ticketId) {
//            // open ticket database
//        ...
//
//            // select using ticketId
//        ...
//
//            // verify we have only one ticket associated with id, if not report error
//        ...
//
//            // return the matching ticket
//        }
//
//        public static class TicketRepositoryTest {
//            private TicketRepository objTicketRepository;
//            void setUp() {
//                objTicketRepository = new TicketRepository();
//            }
//            testFindTicket() {
//                objTicketRepository.insertTicket("TestTicket");
//                // Inserting ticket to database
//                assertEquals("TestTicket".equals(objTicketRepository.findTicket("TestTicket")));
//            }
//        }
//    ...
//    }
//
//
//
//
//
//    C.
//    Given a binary tree, which nodes are represented by the following data structure, write a function that connects node siblings.
//
//    public class TreeNode {
//        private TreeNode left;
//        private TreeNode right;
//        private TreeNode sibling;
//    }
//
//    Input 1:
//            1
//            /   \
//            2     3
//            / \   / \
//            4   5  6  7
//
//    Output 1:
//            1
//            /   \
//            2  -> 3
//            / \   / \
//            4 ->5->6->7
//
//    Input 2:
//            1
//            /  \
//            2    3
//            / \  / \
//            4   5 x  7
//
//    Output 2:
//            1
//            /  \
//            2 -> 3
//            / \  / \
//            4->5  x  7
//
//
//    output1:
//            2.sib = 3
//            4.sbi = 5
//            5.sib = 6
//            6.sib =7
//
//    outpu2:
//            5.sib = null
//
//
//    void connectSib(TreeNode root) {
//        if (root == null) return;
//        findSibling(root);
//    }
//
//    void findSibling(TreeNode root) {
//        if (root.left != null && root.right != null){
//            root.left.sibling = root.right;
//            if (root.sibling != null) {
//                root.right.sibling = root.sibling.left;
//            }
//            findSibling(root.left);
//            findSibling(root.right)
//        }
//    }
//
//
//
//    D.
//    Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
//
//    Given target value is a floating point.
//    You are guaranteed to have only one unique value in the BST that is closest to the target.
//
//
//            1,4,7,9   target:2
//    closet:1
//
//    target: 3
//    closet:4
//
//    min = Float.MAX_VALUE
//
//
//    public class TreeNode {
//        private TreeNode left;
//        private TreeNode right;
//    }
//
//    float findValue(TreeNode node, float value) {
//        final float EPILSON = 0.0001; //Math.epilson
//        if (node == null) {
//            return 0;
//        }
//
//        float min = Float.MAX_VALUE;
//        float rootValue = node.value;
//        if (value < rootValue) {
//            if (root.left != null) {
//                float leftValue = node.left.value
//                min = Math.min(min, Math.abs(findValue(node.left, leftValue) - value);
//                if ((leftValue - value) < EPILSON) {
//                    min = Math.min(min, leftValue);
//                }
//            }
//        } else (value > rootValue) {
//            if (root.right != null) {
//                float rightValue = node.left.value
//                if ((rightValue - value) < EPILSON)
//                    min = Math.min(min, rightValue)
//            }
//        }
//
//        return Math,;
//    }
//
//}
