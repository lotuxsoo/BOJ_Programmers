import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int[] drinks;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        drinks = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            drinks[i] = Integer.parseInt(br.readLine());
        }

        // dp[i]: i번째 잔을 고려할때 마실수있는 최대양
        dp = new int[n + 1];
        dp[1] = drinks[1];
        if (n == 1) {
            System.out.println(dp[1]);
            return;
        }
        dp[2] = Math.max(dp[1], drinks[1] + drinks[2]);

        for (int i = 3; i < n + 1; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + drinks[i - 1] + drinks[i], dp[i - 2] + drinks[i]));
        }

        System.out.println(dp[n]);
    }
}
