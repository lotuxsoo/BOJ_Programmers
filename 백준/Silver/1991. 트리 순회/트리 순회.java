
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 왼쪽 -> 루트 -> 오른쪽
    static void inOrder(int cur) {
        if (cur == -1) {
            return;
        }

        inOrder(tree[cur][0]);
        System.out.print((char) (cur + 'A'));
        inOrder(tree[cur][1]);
    }

    // 루트 -> 왼쪽 -> 오른쪽
    static void preOrder(int cur) {
        if (cur == -1) {
            return;
        }

        System.out.print((char) (cur + 'A'));
        preOrder(tree[cur][0]);
        preOrder(tree[cur][1]);
    }

    static void postOrder(int cur) {
        if (cur == -1) {
            return;
        }

        postOrder(tree[cur][0]);
        postOrder(tree[cur][1]);
        System.out.print((char) (cur + 'A'));
    }

    static int N;
    static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new int[N][2];
        for (int i = 0; i < N; i++) {
            Arrays.fill(tree[i], -1);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int val = st.nextToken().charAt(0) - 'A';
            char left = st.nextToken().charAt(0);
            if (left != '.') {
                tree[val][0] = left - 'A';
            }
            char right = st.nextToken().charAt(0);
            if (right != '.') {
                tree[val][1] = right - 'A';
            }
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
    }
}
