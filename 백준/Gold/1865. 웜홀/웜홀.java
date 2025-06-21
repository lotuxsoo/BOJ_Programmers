
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
    static final long INF = 1_000_000_000_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            String[] sp = br.readLine().split(" ");
            N = Integer.parseInt(sp[0]);
            M = Integer.parseInt(sp[1]);
            W = Integer.parseInt(sp[2]);

            ArrayList<Edge> edgeList = new ArrayList<>();

            // 무방향 양수 간선 추가
            for (int i = 0; i < M; i++) {
                sp = br.readLine().split(" ");
                int S = Integer.parseInt(sp[0]);
                int E = Integer.parseInt(sp[1]);
                int T = Integer.parseInt(sp[2]);
                edgeList.add(new Edge(S, E, T));
                edgeList.add(new Edge(E, S, T));
            }
            // 방향 음수 간선 추가
            for (int i = 0; i < W; i++) {
                sp = br.readLine().split(" ");
                int S = Integer.parseInt(sp[0]);
                int E = Integer.parseInt(sp[1]);
                int T = Integer.parseInt(sp[2]);
                edgeList.add(new Edge(S, E, -T));
            }

            boolean hasNegativeCycle = false;
            for (int i = 1; i <= N; i++) {
                edgeList.add(new Edge(0, i, 0));
            }

            long[] dist = new long[N + 1];
            Arrays.fill(dist, INF);
            dist[0] = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < edgeList.size(); j++) {
                    Edge edge = edgeList.get(j);
                    if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                        dist[edge.to] = dist[edge.from] + edge.cost;
                    }
                }
            }

            for (int j = 0; j < edgeList.size(); j++) {
                Edge edge = edgeList.get(j);
                if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                    hasNegativeCycle = true;
                    break;
                }
            }

            if (!hasNegativeCycle) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
