
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] time = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            int[] indegree = new int[N + 1]; // 진입차수 배열
            ArrayList<Integer>[] graph = new ArrayList[N + 1]; // 인접리스트
            for (int i = 0; i < N + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x].add(y);
                indegree[y]++;
            }

            int W = Integer.parseInt(br.readLine());

            // dp[i]: i를 짓는데까지 걸리는 시간
            int[] dp = new int[N + 1];

            // 위상정렬 시작
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    if (i == W) {
                        sb.append(time[i]).append("\n");
                        break;
                    }
                    queue.add(i);
                    dp[i] = time[i];
                }
            }

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == W) {
                    sb.append(dp[cur]).append("\n");
                    break;
                }

                for (int next : graph[cur]) {
                    indegree[next]--; // 선행 건물 하나 지음
                    dp[next] = Math.max(dp[next], dp[cur] + time[next]);
                    if (indegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }

        System.out.println(sb.toString());
    }
}
