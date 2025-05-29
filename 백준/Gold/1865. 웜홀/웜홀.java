
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static class Edge {
        int from, to;
        long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M, W;
    static List<Edge> edges;
    static final long INF = 1_000_000_000_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (TC-- > 0) {
            String[] sp = br.readLine().split(" ");
            N = Integer.parseInt(sp[0]);
            M = Integer.parseInt(sp[1]);
            W = Integer.parseInt(sp[2]);

            edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                sp = br.readLine().split(" ");
                int from = Integer.parseInt(sp[0]);
                int to = Integer.parseInt(sp[1]);
                long cost = Long.parseLong(sp[2]);
                // 도로는 방향이 없음
                edges.add(new Edge(from, to, cost));
                edges.add(new Edge(to, from, cost));
            }

            for (int i = 0; i < W; i++) {
                sp = br.readLine().split(" ");
                int from = Integer.parseInt(sp[0]);
                int to = Integer.parseInt(sp[1]);
                long cost = Long.parseLong(sp[2]);
                // 웜홀은 방향이 있음
                edges.add(new Edge(from, to, -cost));
            }

            // 정점 0번과 모두 연결
            for (int i = 1; i <= N; i++) {
                edges.add(new Edge(0, i, 0));
            }

            long[] dist = new long[N + 1];
            Arrays.fill(dist, INF);
            dist[0] = 0;

            for (int i = 0; i < N; i++) {
                for (Edge edge : edges) {
                    if (dist[edge.from] != INF && (dist[edge.to] > dist[edge.from] + edge.cost)) {
                        dist[edge.to] = dist[edge.from] + edge.cost;
                    }
                }
            }

            boolean flag = false;
            for (Edge edge : edges) {
                if (dist[edge.from] != INF && (dist[edge.to] > dist[edge.from] + edge.cost)) {
                    flag = true;
                    break;
                }
            }

            sb.append(flag ? "YES\n" : "NO\n");
        }
        System.out.println(sb.toString());
    }
}
