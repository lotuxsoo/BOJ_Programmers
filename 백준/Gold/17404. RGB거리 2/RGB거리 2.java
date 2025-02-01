import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3]; // 0:R, 1:G, 2:B

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        final int INF = 1000000000;
        int MIN_VAL = Integer.MAX_VALUE;

        // dp[i][j]: i번집에서 j색깔을 칠했을때 최소비용
        int[][] dp = new int[N][3];

        for (int color = 0; color < 3; color++) {
            dp[0][0] = cost[0][0];
            dp[0][1] = cost[0][1];
            dp[0][2] = cost[0][2];
            dp[0][color] = INF;

            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
            }

            MIN_VAL = Math.min(MIN_VAL, dp[N - 1][color]);
        }

        System.out.println(MIN_VAL);
    }
}
