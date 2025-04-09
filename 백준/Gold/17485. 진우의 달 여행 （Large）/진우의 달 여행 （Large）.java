
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[i][j][k]: (i,j)까지 K방향으로 도착하는데 필요한 최소 연료값
        int[][][] dp = new int[N][M][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        for (int j = 0; j < M; j++) {
            for (int k = 0; k < 3; k++) {
                dp[0][j][k] = map[0][j]; // 0번째 행 초기화
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i - 1 >= 0 && j - 1 >= 0) { // 방향 0
                    dp[i][j][0] = Math.min(dp[i][j][0], Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + map[i][j]);
                }
                if (i - 1 >= 0) { // 방향 1
                    dp[i][j][1] = Math.min(dp[i][j][1], Math.min(dp[i - 1][j][0], dp[i - 1][j][2]) + map[i][j]);
                }
                if (i - 1 >= 0 && j + 1 < M) { // 방향 2
                    dp[i][j][2] = Math.min(dp[i][j][2], Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]) + map[i][j]);
                }
            }
        }

        int result = INF;
        for (int j = 0; j < M; j++) {
            for (int k = 0; k < 3; k++) {
                result = Math.min(result, dp[N - 1][j][k]);
            }
        }

        System.out.println(result);
    }
}
