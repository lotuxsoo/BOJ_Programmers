
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static void dfs(int cur) {
        dp[cur][0] = 0;
        dp[cur][1] = people[cur];

        for (int child : graph[cur]) {
            if (!visited[child]) {
                visited[child] = true;
                dfs(child);

                dp[cur][1] += dp[child][0];
                dp[cur][0] += Math.max(dp[child][0], dp[child][1]);
            }
        }
    }

    static int N;
    static int[] people;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        people = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // dp[v][0]: v가 우수마을이 아닐때, 하위트리의 최대 주민수 총합
        // dp[v][1]: v가 우수마을이 맞을때, 하위트리의 최대 주민수 총합
        dp = new int[N + 1][2];

        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
}
