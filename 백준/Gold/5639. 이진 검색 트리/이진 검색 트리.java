
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public void insert(int n) {
            if (n < this.val) {
                if (this.left == null) {
                    this.left = new Node(n);
                } else {
                    this.left.insert(n);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(n);
                } else {
                    this.right.insert(n);
                }
            }
        }
    }

    static void postorder(Node cur) {
        if (cur == null) {
            return;
        }

        postorder(cur.left);
        postorder(cur.right);
        System.out.println(cur.val);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String s = br.readLine();
            if (s == null || s.equals(" ")) {
                break;
            }
            int val = Integer.parseInt(s);
            root.insert(val);
        }

        postorder(root);
    }
}
