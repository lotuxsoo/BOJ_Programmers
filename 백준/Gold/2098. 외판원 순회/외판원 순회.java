import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long solve(int visited, int current) {
        // 모든 도시 순회
        if (visited == (1 << N) - 1) {
            return W[current][start] == 0 ? INF : W[current][start];
        }

        if (dp[visited][current] != -1) {
            return dp[visited][current];
        }

        long bestCost = INF;

        for (int next = 0; next < N; next++) {
            // 다음으로 가는 비용 유무, 방문 도시 여부 확인
            if (W[current][next] != 0 && (visited & (1 << next)) == 0) {
                bestCost = Math.min(bestCost, solve(visited | (1 << next), next) + W[current][next]);
            }
        }

        dp[visited][current] = bestCost;
        return dp[visited][current];
    }

    static int N;
    static int[][] W;
    static long[][] dp;
    static long INF = 1_000_000_000;
    static int start = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[visitedSet][currentNode]: 해당 집합,현재 노드에서의 최단 비용
        dp = new long[(1 << N)][N];
        for (int i = 0; i < (1 << N); i++) {
            Arrays.fill(dp[i], -1);
        }

        long ans = solve(start | (1 << start), start);

        System.out.println(ans);
    }
}
