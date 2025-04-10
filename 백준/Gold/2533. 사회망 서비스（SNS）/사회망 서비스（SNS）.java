
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static void dfs(int cur, int parent) {
        visited[cur] = true;

        dp[cur][0] = 0; // 얼리가 아닐 때
        dp[cur][1] = 1; // 얼리일 때 (본인 포함)

        for (int next : graph[cur]) {
            if (next != parent) {
                dfs(next, cur);
                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }

    static int N;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1]; // 양방향 트리 표현
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            String[] sp = br.readLine().split(" ");
            int u = Integer.parseInt(sp[0]);
            int v = Integer.parseInt(sp[1]);
            graph[u].add(v);
            graph[v].add(u);
        }

        // dp[i][0]: i가 얼리가 아닐 때, 서브트리의 최소 얼리 수
        // dp[i][1]: i가 얼리일 때, 서브트리의 최소 얼리 수
        dp = new int[N + 1][2];

        visited = new boolean[N + 1];
        // 루트 1로 지정
        dfs(1, -1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
}
