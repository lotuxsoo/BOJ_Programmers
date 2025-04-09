
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (TC-- > 0) {
            ArrayList<Edge> edges = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, w)); // 방향 X
                edges.add(new Edge(e, s, w));
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, -w)); // 방향 O
            }

            // 가상 정점 0번을 추가
            for (int i = 1; i <= N; i++) {
                edges.add(new Edge(0, i, 0));
            }

            long[] dist = new long[N + 1];
            Arrays.fill(dist, INF);
            dist[0] = 0;

            // 정점수 N+1 -> N번 반복해서 에지 업데이트
            for (int j = 0; j < N; j++) {
                for (Edge edge : edges) {
                    if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                        dist[edge.to] = dist[edge.from] + edge.cost;
                    }
                }
            }

            boolean hasCycle = false;
            // 음수 사이클 확인
            for (Edge edge : edges) {
                if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                    hasCycle = true;
                    break;
                }
            }
            if (hasCycle) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString());
    }
}
