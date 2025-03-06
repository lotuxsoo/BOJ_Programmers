
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static void dfs(int cur) {
        dp[cur][0] = 0;
        dp[cur][1] = W[cur];

        for (int child : tree[cur]) {
            if (!visited[child]) {
                visited[child] = true;
                dfs(child);

                dp[cur][1] += dp[child][0];
                dp[cur][0] += Math.max(dp[child][0], dp[child][1]);
            }
        }
    }

    static void tracking(int u, boolean isIncluded) {
        if (isIncluded) {
            answer.add(u);
        }

        visited[u] = true;

        for (int v : tree[u]) {
            if (visited[v]) {
                continue;
            }
            if (isIncluded) {
                tracking(v, false);
            } else {
                if (dp[v][0] < dp[v][1]) {
                    tracking(v, true);
                } else {
                    tracking(v, false);
                }
            }
        }
    }

    static int N;
    static int[] W;
    static ArrayList<Integer>[] tree;
    static int[][] dp;
    static boolean[] visited;
    static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            W[i] = Integer.parseInt(st.nextToken());
        }
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

        // dp[v][0]: v가 독립집합에 속하지 않았을 때, 최대 합
        // dp[v][1]: v가 독립집합에 속했을 때, 최대 합
        dp = new int[N + 1][2];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1);

        visited = new boolean[N + 1];

        if (dp[1][0] < dp[1][1]) {
            System.out.println(dp[1][1]);
            tracking(1, true);
        } else {
            System.out.println(dp[1][0]);
            tracking(1, false);
        }

        Collections.sort(answer);
        for (int x : answer) {
            System.out.print(x + " ");
        }
    }
}
