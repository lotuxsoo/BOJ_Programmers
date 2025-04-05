
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] children = new int[N];
        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        // dp[i]: i번째를 포함하는 가장 긴 증가 부분수열
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int maxLen = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (children[j] < children[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        System.out.println(N - maxLen);
    }
}
