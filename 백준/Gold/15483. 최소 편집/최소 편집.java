import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();

        int n = A.length();
        int m = B.length();
        // dp[i][j]: A의 i번째까지의 문자열에서, B의 j번째까지의 문자열까지의 최소편집횟수
        int[][] dp = new int[n + 1][m + 1];

        // 초기값
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i; // i번 삭제
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j; // j번 삽입
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
