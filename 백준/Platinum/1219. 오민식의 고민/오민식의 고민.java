import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int s, e, v;

        Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }

    static ArrayList<Edge> edges;

    static boolean DFS(int start, boolean[] visited, int end) {
        visited[start] = true;
        if (start == end) {
            return true;
        }

        for (Edge edge : edges) {
            if (edge.s == start && !visited[edge.e]) {
                if (DFS(edge.e, visited, end)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도시 수
        int S = Integer.parseInt(st.nextToken()); // 시작 도시
        int E = Integer.parseInt(st.nextToken()); // 도착 도시
        int M = Integer.parseInt(st.nextToken()); // 교통수단 수

        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s, e, v));
        }

        st = new StringTokenizer(br.readLine());
        int[] money = new int[N];
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        long[] cost = new long[N];
        Arrays.fill(cost, Long.MIN_VALUE);
        cost[S] = money[S];

        // 벨만포드 (음수 간선 존재 가능)
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                int s = edge.s, e = edge.e, v = edge.v;

                if (cost[s] != Long.MIN_VALUE && cost[e] < cost[s] - v + money[e]) {
                    cost[e] = cost[s] - v + money[e];
                }
            }
        }

        // 추가 확인
        boolean cycle = false;
        boolean[] onCycle = new boolean[N];

        for (Edge edge : edges) {
            int s = edge.s, e = edge.e, v = edge.v;

            if (cost[s] != Long.MIN_VALUE && cost[e] < cost[s] - v + money[e]) {
                cycle = true;
                onCycle[s] = true;
                onCycle[e] = true;
            }
        }

        if (!cycle) {
            System.out.println(cost[E] == Long.MIN_VALUE ? "gg" : cost[E]);
        } else {
            for (int i = 0; i < N; i++) {
                if (onCycle[i]) {
                    boolean[] visitedFromStart = new boolean[N];
                    boolean[] visitedFromEnd = new boolean[N];

                    if (DFS(S, visitedFromStart, i) && DFS(i, visitedFromEnd, E)) {
                        System.out.println("Gee");
                        return;
                    }
                }
            }
            System.out.println(cost[E] == Long.MIN_VALUE ? "gg" : cost[E]);
        }
    }
}
