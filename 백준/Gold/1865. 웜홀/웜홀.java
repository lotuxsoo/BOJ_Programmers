
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int S, E, T;

        Edge(int S, int E, int T) {
            this.S = S;
            this.E = E;
            this.T = T;
        }
    }

    static boolean canTimeTravel(int x) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[x] = 0;

        for (int i = 0; i < N; i++) {
            boolean updated = false;
            for (Edge edge : edges) {
                if (dist[edge.S] != INF && dist[edge.E] > dist[edge.S] + edge.T) {
                    dist[edge.E] = dist[edge.S] + edge.T;
                    updated = true;
                    if (i == N - 1) {
                        return true;
                    }
                }
            }
            if (!updated) {
                break;
            }
        }

        return false;
    }

    static int N, M, W;
    static ArrayList<Edge> edges;
    static final int INF = 1_000_000_000;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, -T));
            }

            boolean hasCycle = false;
            for (int i = 1; i <= N; i++) {
                if (canTimeTravel(i)) {
                    sb.append("YES\n");
                    hasCycle = true;
                    break;
                }
            }
            if (!hasCycle) {
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString());
    }
}
