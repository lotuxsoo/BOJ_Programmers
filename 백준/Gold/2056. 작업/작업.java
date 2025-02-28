
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Info {
        int n, time;

        Info(int n, int time) {
            this.n = n;
            this.time = time;
        }
    }

    static int N;
    static ArrayList<Integer>[] A; // 방향 인접리스트
    static int[] time; // 작업 시간
    static int[] D; // 진입차수 배열
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        A = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        time = new int[N + 1];
        D = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(st.nextToken());
                A[x].add(i);
                D[i]++;
            }
        }

        // i번 작업까지 수행했을 때의 최대 시간
        dp = new int[N + 1];

        Queue<Info> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (D[i] == 0) {
                dp[i] = time[i];
                queue.add(new Info(i, time[i]));
            }
        }

        int MAX = 0;

        while (!queue.isEmpty()) {
            Info cur = queue.poll();

            MAX = Math.max(MAX, dp[cur.n]);

            for (int next : A[cur.n]) {
                dp[next] = Math.max(dp[next], dp[cur.n] + time[next]);

                D[next]--;
                if (D[next] == 0) {
                    queue.add(new Info(next, dp[next]));
                }
            }
        }

        System.out.println(MAX);
    }
}
