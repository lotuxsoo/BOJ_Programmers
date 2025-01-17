import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int[][] tree = new int[26][2]; // 0 -> left(1) -> right(2)
    static ArrayList<String> answerList = new ArrayList<>();

    static void preOrder(int now) { // root -> left - > right
        if (now == -1) {
            return;
        }

        System.out.print((char) (now + 'A'));
        preOrder(tree[now][0]);
        preOrder(tree[now][1]);
    }

    static void inOrder(int now) { // left -> root -> right
        if (now == -1) {
            return;
        }

        inOrder(tree[now][0]);
        System.out.print((char) (now + 'A'));
        inOrder(tree[now][1]);
    }

    static void postOrder(int now) { // left -> right -> root
        if (now == -1) {
            return;
        }

        postOrder(tree[now][0]);
        postOrder(tree[now][1]);
        System.out.print((char) (now + 'A'));

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] splits = br.readLine().split(" ");
            int node = splits[0].charAt(0) - 'A'; // 알파벳을 index로 변환
            char left = splits[1].charAt(0);
            char right = splits[2].charAt(0);

            if (left == '.') {
                tree[node][0] = -1;
            } else {
                tree[node][0] = left - 'A';
            }
            if (right == '.') {
                tree[node][1] = -1;
            } else {
                tree[node][1] = right - 'A';
            }
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
        System.out.println();
    }
}
