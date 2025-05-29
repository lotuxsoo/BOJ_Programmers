
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

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

    static ArrayList<Edge> edges = new ArrayList<>();
    static int N, M;
    static final long INF = 1_000_000_000_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);

        for (int i = 0; i < M; i++) {
            sp = br.readLine().split(" ");
            int a = Integer.parseInt(sp[0]);
            int b = Integer.parseInt(sp[1]);
            long c = Long.parseLong(sp[2]);
            edges.add(new Edge(a, b, c));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        // 최단 경로는 최대 N-1개의 간선 이용
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if ((dist[edge.from] != INF) && (dist[edge.to] > dist[edge.from] + edge.cost)) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        // 음수 사이클 확인
        for (Edge edge : edges) {
            if ((dist[edge.from] != INF) && dist[edge.to] > dist[edge.from] + edge.cost) {
                System.out.println(-1);
                return;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 2; i <= N; i++) {
            sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
