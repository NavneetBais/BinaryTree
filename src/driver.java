import java.util.ArrayList;
import java.util.Scanner;

public class driver {
    static Scanner sc;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);
        Node root = createTree();

        System.out.println("Size of Binary Tree: " + size(root));
        System.out.println("No of leaf nodes found : " + noOfLeafs(root));
        System.out.println("Max Value :     " + maxValue(root, Integer.MIN_VALUE));
        System.out.println(findValue(root, 19));
        System.out.println("Give Node are Siblings : " + isSibling(root, new Node(9), new Node(7)));
        ArrayList<Integer> res = new ArrayList<Integer>();
        getAncestors(root, new Node(7), res);
        System.out.println(res);
    }

    // To check if given value are Siblings
    static boolean isSibling(Node root, Node a, Node b) {
        if (root == null)
            return false;
        return (root.left.data == a.data && root.right.data == b.data)
                || (root.left.data == b.data && root.right.data == a.data) || isSibling(root.left, a, b)
                || isSibling(root.right, a, b);
    }

    // Maximum Value in a Tree
    static int maxValue(Node root, int max) {
        if (root == null) {
            return 0;
        }
        if (root.data > max) {
            max = root.data;
            int leftMax = maxValue(root.left, max);
            int rightMax = maxValue(root.right, max);
            if (leftMax > rightMax) {
                max = leftMax;
            } else {
                max = rightMax;
            }
        }

        return max;
    }

    // Ancestor of a Given Node
    static boolean getAncestors(Node root, Node a, ArrayList<Integer> res) {
        if (root != null) {
            if (root.data == a.data) {
                return true;
            }
            if (getAncestors(root.left, a, res) || getAncestors(root.right, a, res)) {
                res.add(root.data);
                return true;
            }
        }
        return false;
    }

    // Find Value in a Tree
    static boolean findValue(Node root, int value) {

        if (root == null) {
            return false;
        }
        if (root.data == value) {
            return true;
        }
        boolean leftflag = findValue(root.left, value);
        boolean rightflag = findValue(root.right, value);
        if (leftflag == true || rightflag == true) {
            return true;
        }
        return false;
    }

    // Size of BinaryTree
    static int size(Node root) {
        if (root == null)
            return 0;

        return size(root.left) + size(root.right) + 1;

    }

    // Number of Leaves
    static int noOfLeafs(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        return noOfLeafs(root.left) + noOfLeafs(root.right);
    }

    static Node createTree() {
        Node root = null;
        System.out.println("Enter Data: ");
        int data = sc.nextInt();
        if (data == -1)
            return null;

        root = new Node(data);
        System.out.println("Enter left for" + data);
        root.left = createTree();
        System.out.println("Enter right for" + data);
        root.right = createTree();

        return root;
    }

}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;

    }

}