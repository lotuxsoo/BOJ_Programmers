
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(sp[j]);
            }
        }

        int answer = INF;

        for (int first = 0; first < 3; first++) {
            int[][] dp = new int[N][3];

            for (int i = 0; i < 3; i++) {
                if (i != first) {
                    dp[0][i] = map[0][i];
                } else {
                    dp[0][first] = INF;
                }
            }

            for (int j = 1; j < N; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + map[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + map[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + map[j][2];
            }

            answer = Math.min(answer, dp[N - 1][first]);
//            for (int i = 0; i < 3; i++) {
//                if (i == first) {
//                    answer = Math.min(answer, dp[N - 1][i]);
//                }
//            }
        }

        System.out.println(answer);
    }
}
