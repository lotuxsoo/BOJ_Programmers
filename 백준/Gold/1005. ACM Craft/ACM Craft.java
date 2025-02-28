
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, W;
    static int[] cost;
    static ArrayList<Integer>[] A;
    static int[] D;
    static int[] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cost = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }

            A = new ArrayList[N + 1];
            for (int i = 0; i < N + 1; i++) {
                A[i] = new ArrayList<>();
            }

            D = new int[N + 1];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                A[X].add(Y);
                D[Y]++;
            }
            W = Integer.parseInt(br.readLine());

            dp = new int[N + 1];

            // 위상 정렬
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i < N + 1; i++) {
                if (D[i] == 0) {
                    queue.add(i);
                    dp[i] = cost[i];
                }
            }

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                if (cur == W) {
                    sb.append(dp[W]).append("\n");
                    break;
                }

                for (int next : A[cur]) {
                    // next를 짓는데까지 걸리는 최장 시간
                    dp[next] = Math.max(dp[next], dp[cur] + cost[next]);

                    D[next]--;
                    if (D[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }

        System.out.println(sb.toString().trim());
    }
}
