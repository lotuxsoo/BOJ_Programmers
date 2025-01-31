import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[301];
        dp[1] = A[1];

        if (n >= 2) {
            dp[2] = A[1] + A[2];
        }

        if (n >= 3) {
            dp[3] = Math.max(A[1] + A[3], A[2] + A[3]);
        }

        for (int i = 4; i < n + 1; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + A[i - 1]) + A[i];
        }

        System.out.println(dp[n]);
    }
}
