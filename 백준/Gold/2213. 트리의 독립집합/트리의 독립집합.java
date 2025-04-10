
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static void dfs(int cur) {
        visited[cur] = true;

        dp[cur][0] = 0;
        dp[cur][1] = weight[cur];

        for (int next : graph[cur]) {
            if (!visited[next]) {
                dfs(next);
                dp[cur][0] += Math.max(dp[next][1], dp[next][0]);
                dp[cur][1] += dp[next][0];
            }
        }
    }

    static void trace(int cur, int parent, boolean isIncluded) {
        if (isIncluded) {
            answer.add(cur);
            for (int next : graph[cur]) {
                if (next != parent) {
                    trace(next, cur, false);
                }
            }
        } else {

            for (int next : graph[cur]) {
                if (next != parent) {
                    if (dp[next][0] > dp[next][1]) {
                        trace(next, cur, false);
                    } else {
                        trace(next, cur, true);
                    }
                }
            }
        }
    }

    static int n;
    static int[] weight;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;
    static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        weight = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // dp[i][0]: i가 선택안됐을 때, 최대 독립집합 크기
        // dp[i][1]: i가 선택됐을 때, 최대 독립집합 크기
        dp = new int[n + 1][2];

        visited = new boolean[n + 1];
        // 1로 루트 지정
        dfs(1);

        // 독립집합 복원
        if (dp[1][0] > dp[1][1]) {
            System.out.println(dp[1][0]);
            trace(1, 0, false);
        } else {
            System.out.println(dp[1][1]);
            trace(1, 0, true);
        }

        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        for (int x : answer) {
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString());
    }
}
