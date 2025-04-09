
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] time;
    static int[] indegree;
    static int[] dp;
    static ArrayList<Integer>[] graph; // graph[i]를 선행작업으로 가지는 작업번호 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        indegree = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            int n = Integer.parseInt(st.nextToken());
            indegree[i] = n;
            if (n > 0) {
                for (int j = 0; j < n; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    graph[x].add(i);
                }
            }
        }

        dp = new int[N + 1];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                dp[i] = time[i];
            }
        }

        int result = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result = Math.max(result, dp[cur]);

            for (int next : graph[cur]) {
                indegree[next]--;
                dp[next] = Math.max(dp[next], dp[cur] + time[next]);

                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        System.out.println(result);
    }
}
