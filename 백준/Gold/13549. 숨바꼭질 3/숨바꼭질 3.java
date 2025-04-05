
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N, K;
    static int[] dp;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        dp = new int[100001];
        Arrays.fill(dp, INF);
        dp[N] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == K) {
                System.out.println(dp[cur]);
                return;
            }

            if (cur + 1 >= 0 && cur + 1 <= 100000) {
                if (dp[cur + 1] > dp[cur] + 1) {
                    dp[cur + 1] = dp[cur] + 1;
                    queue.add(cur + 1);
                }
            }
            if (cur - 1 >= 0 && cur - 1 <= 100000) {
                if (dp[cur - 1] > dp[cur] + 1) {
                    dp[cur - 1] = dp[cur] + 1;
                    queue.add(cur - 1);
                }
            }
            if (cur * 2 >= 0 && cur * 2 <= 100000) {
                if (dp[cur * 2] > dp[cur]) {
                    dp[cur * 2] = dp[cur];
                    queue.add(cur * 2);
                }
            }
        }

    }
}
