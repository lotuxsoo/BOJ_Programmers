
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int dfs(int start, int cur, int mask) {
        if (mask == (1 << N) - 1) {
            if (cost[cur][start] != 0) {
                return cost[cur][start];
            }
            return INF;
        }

        if (dp[cur][mask] != -1) {
            return dp[cur][mask];
        }

        dp[cur][mask] = INF;

        for (int i = 0; i < N; i++) {
            if (cost[cur][i] != 0 && ((mask & (1 << i))) == 0) {
                int next = dfs(start, i, (mask | (1 << i)));
                dp[cur][mask] = Math.min(dp[cur][mask], next + cost[cur][i]);
            }
        }

        return dp[cur][mask];
    }

    static int N;
    static int[][] cost;
    static int[][] dp;
    static final int INF = 1_000_000_000;
    static int minCost = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[x][mask]: x에서 시작, x까지의 마스크
        dp = new int[N][(1 << N)];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        dfs(0, 0, (1 << 0));
        System.out.println(dp[0][(1 << 0)]);
    }
}
