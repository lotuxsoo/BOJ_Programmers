
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] cost;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][3];
        int result = INF;

        for (int firstColor = 0; firstColor < 3; firstColor++) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], INF);
            }

            for (int k = 0; k < 3; k++) {
                if (firstColor == k) {
                    dp[0][k] = cost[0][k];
                }
            }

            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
            }

            for (int k = 0; k < 3; k++) {
                if (firstColor != k) {
                    result = Math.min(result, dp[N - 1][k]);
                }
            }
        }

        System.out.println(result);
    }
}
