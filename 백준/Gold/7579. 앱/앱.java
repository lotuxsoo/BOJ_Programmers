
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] m;
    static int[] c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        m = new int[N + 1];
        c = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        // dp[c]=m (c 비용으로 만들 수 있는 최대 메모리)
        int[] dp = new int[10001];
        int minCost = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            for (int cost = 10000; cost >= c[i]; cost--) {
                dp[cost] = Math.max(dp[cost], dp[cost - c[i]] + m[i]);
                if (dp[cost] >= M) {
                    minCost = Math.min(minCost, cost);
                }
            }
        }

        System.out.println(minCost);
    }
}
