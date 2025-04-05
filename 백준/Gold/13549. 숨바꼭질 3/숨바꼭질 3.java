
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

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

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(N);

        while (!deque.isEmpty()) {
            int cur = deque.poll();

            if (cur == K) {
                System.out.println(dp[cur]);
                return;
            }

            if (cur + 1 >= 0 && cur + 1 <= 100000) {
                if (dp[cur + 1] > dp[cur] + 1) {
                    dp[cur + 1] = dp[cur] + 1;
                    deque.addLast(cur + 1);
                }
            }
            if (cur - 1 >= 0 && cur - 1 <= 100000) {
                if (dp[cur - 1] > dp[cur] + 1) {
                    dp[cur - 1] = dp[cur] + 1;
                    deque.addLast(cur - 1);
                }
            }
            if (cur * 2 >= 0 && cur * 2 <= 100000) {
                if (dp[cur * 2] > dp[cur]) {
                    dp[cur * 2] = dp[cur];
                    deque.addFirst(cur * 2);
                }
            }
        }

    }
}
