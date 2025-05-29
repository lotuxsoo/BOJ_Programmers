
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static ArrayList<Integer>[] graph;
    static int N, M;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            sp = br.readLine().split(" ");
            int a = Integer.parseInt(sp[0]);
            int b = Integer.parseInt(sp[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{1, 0}); // 시작점, 거리
        int max = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (dist[cur[0]] < cur[1]) {
                continue;
            }

            max = Math.max(max, dist[cur[0]]);

            for (int next : graph[cur[0]]) {
                if (dist[next] > dist[cur[0]] + 1) {
                    dist[next] = dist[cur[0]] + 1;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == max) {
                if (!flag) {
                    sb.append(i).append(" ");
                    sb.append(max).append(" ");
                    flag = true;
                    count++;
                } else {
                    count++;
                }
            }
        }
        sb.append(count);
        System.out.println(sb.toString());
    }
}
