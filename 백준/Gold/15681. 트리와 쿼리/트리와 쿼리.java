
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static void makeTree(int cur, int parent) {
        dp[cur] = 1; // 본인 포함

        for (int child : tree[cur]) {
            if (child != parent) {
                makeTree(child, cur);
                dp[cur] += dp[child];
            }
        }
    }

    static int N, R, Q;
    static ArrayList<Integer>[] tree;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        // dp[v]: v를 루트로 하는 서브트리에 속한 정점의 수
        dp = new int[N + 1];

        makeTree(R, -1);

        for (int i = 0; i < Q; i++) {
            int x = Integer.parseInt(br.readLine());
            System.out.println(dp[x]);
        }
    }
}
