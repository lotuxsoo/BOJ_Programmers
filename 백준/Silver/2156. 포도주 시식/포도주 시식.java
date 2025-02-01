import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] drinks = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            drinks[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N + 1];
        dp[1] = drinks[1];
        if (N == 1) {
            System.out.println(dp[1]);
            return;
        }
        dp[2] = drinks[1] + drinks[2];

        for (int i = 3; i < N + 1; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + +drinks[i], dp[i - 3] + drinks[i - 1] + +drinks[i]));
        }

        System.out.println(dp[N]);
    }
}
