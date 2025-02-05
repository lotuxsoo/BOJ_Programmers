import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        // dp[i]: A[i]를 **마지막 원소**로 하는 증가하는 부분수열중 최대합
        int[] dp = new int[N + 1];
        int MAX_VAL = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            dp[i] = A[i];
            for (int j = 1; j < i; j++) {
                if (A[i] > A[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + A[i]);
                }
            }
            MAX_VAL = Math.max(MAX_VAL, dp[i]);
        }

        System.out.println(MAX_VAL);
    }
}
