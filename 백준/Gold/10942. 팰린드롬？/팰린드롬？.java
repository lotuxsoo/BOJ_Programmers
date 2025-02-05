import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j]: i~j까지 팰린드롬이면 1 아니면 0
        boolean[][] dp = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        for (int i = 1; i < N; i++) {
            if (A[i] == A[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        for (int len = 2; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;
                if (A[i] == A[j]) {
                    if (dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(dp[S][E] ? 1 : 0).append("\n");
        }
        System.out.println(sb.toString());
    }
}
