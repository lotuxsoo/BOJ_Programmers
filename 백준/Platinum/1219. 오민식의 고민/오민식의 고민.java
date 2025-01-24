import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge {
        int s, e, v;

        Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }

    static int N, S, E, M;
    static ArrayList<Edge> edges;
    static boolean[] visited;

    static boolean DFS(int start) {
        if (start == E) {
            return true;
        }

        visited[start] = true;

        for (Edge edge : edges) {
            if (edge.s == start && !visited[edge.e]) {
                if (DFS(edge.e)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s, e, v));
        }

        int[] money = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        long[] cost = new long[N];
        Arrays.fill(cost, Long.MIN_VALUE);
        cost[S] = money[S];

        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if (cost[edge.s] != Long.MIN_VALUE &&
                        cost[edge.e] < cost[edge.s] - edge.v + money[edge.e]) {
                    cost[edge.e] = cost[edge.s] - edge.v + money[edge.e];
                }
            }
        }

        if (cost[E] == Long.MIN_VALUE) {
            System.out.println("gg");
            return;
        }

        // 양수 사이클에 포함된 노드들을 저장할 Set
        Set<Integer> cycleNodes = new HashSet<>();

        // 양수 사이클 발견 및 사이클 노드 저장
        for (Edge edge : edges) {
            if (cost[edge.s] != Long.MIN_VALUE &&
                    cost[edge.e] < cost[edge.s] - edge.v + money[edge.e]) {
                cycleNodes.add(edge.s);
                cycleNodes.add(edge.e);
            }
        }

        // 사이클이 존재하면
        if (!cycleNodes.isEmpty()) {
            // 사이클의 모든 노드에서 DFS 시도
            for (int cycleNode : cycleNodes) {
                visited = new boolean[N];  // DFS 호출마다 visited 초기화
                if (DFS(cycleNode)) {
                    System.out.println("Gee");
                    return;
                }
            }
        }

        System.out.println(cost[E]);
    }
}