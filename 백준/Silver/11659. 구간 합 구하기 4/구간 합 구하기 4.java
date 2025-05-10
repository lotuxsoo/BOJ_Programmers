
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);

        arr = new int[N + 1];
        dp = new int[N + 1];
        sp = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(sp[i - 1]);
            dp[i] = dp[i - 1] + arr[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sp = br.readLine().split(" ");
            int a = Integer.parseInt(sp[0]);
            int b = Integer.parseInt(sp[1]);
            sb.append(dp[b] - dp[a - 1]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
