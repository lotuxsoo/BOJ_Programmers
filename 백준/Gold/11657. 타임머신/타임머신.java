
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

    static int N, M;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);

        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            sp = br.readLine().split(" ");
            int A = Integer.parseInt(sp[0]);
            int B = Integer.parseInt(sp[1]);
            long C = Long.parseLong(sp[2]);
            edgeList.add(new Edge(A, B, C));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edgeList.get(j);
                if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        boolean hasNegativeCycle = false;

        for (int i = 0; i < M; i++) {
            Edge edge = edgeList.get(i);
            if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                hasNegativeCycle = true;
                break;
            }
        }

        if (hasNegativeCycle) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= N; i++) {
                sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
            }
            System.out.println(sb.toString().trim());
        }
    }
}
