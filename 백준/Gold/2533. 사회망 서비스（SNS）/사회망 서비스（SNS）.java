
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static void dfs(int pos) {
        dp[pos][0] = 0;
        dp[pos][1] = 1;

        for (int child : graph[pos]) {
            if (!visited[child]) {
                visited[child] = true;
                dfs(child); // 재귀호출로 자식 노드의 dp값을 미리 구한다.
                dp[pos][0] += dp[child][1];
                dp[pos][1] += Math.min(dp[child][0], dp[child][1]);
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
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]), b = Integer.parseInt(s[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        // dp[v][0]: v가 얼리어답터가 아닐때, 서브트리에서의 최소 얼리어답터 수
        // dp[v][1]: v가 얼리어답터일 때, 서브트리에서의 최소 얼리어답터 수
        dp = new int[N + 1][2];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        visited = new boolean[N + 1];
        visited[1] = true;

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
}
