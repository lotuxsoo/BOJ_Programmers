
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public void insert(int newVal) {
            if (newVal < this.val) {
                if (this.left == null) {
                    this.left = new Node(newVal);
                } else {
                    this.left.insert(newVal);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(newVal);
                } else {
                    this.right.insert(newVal);
                }
            }
        }
    }

    static void postOrder(Node cur) {
        if (cur == null) {
            return;
        }

        postOrder(cur.left);
        postOrder(cur.right);
        System.out.println(cur.val);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node node = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String input = br.readLine();
            if (input == null || input.isEmpty()) {
                break;
            }
            node.insert(Integer.parseInt(input));
        }

        postOrder(node);
    }
}
