
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static void solve() {
        int[][] dp = new int[N][M];
        dp[0][0] = map[0][0];

        // 첫 행은 오른쪽 이동만 가능
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + map[0][i];
        }

        // 오른쪽,왼쪽 각각 위와 비교하면서 최대 누적합 갱신
        for (int i = 1; i < N; i++) {
            int[] L = new int[M];
            int[] R = new int[M];

            L[0] = dp[i - 1][0] + map[i][0];
            R[M - 1] = dp[i - 1][M - 1] + map[i][M - 1];

            for (int j = 1; j < M; j++) {
                L[j] = Math.max(dp[i - 1][j], L[j - 1]) + map[i][j];
            }
            for (int j = M - 2; j >= 0; j--) {
                R[j] = Math.max(dp[i - 1][j], R[j + 1]) + map[i][j];
            }
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(L[j], R[j]);
            }
        }

        System.out.println(dp[N - 1][M - 1]);
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

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

        solve();
    }
}
