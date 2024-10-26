import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, M, V;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static StringBuilder sb;

    static void putEdges(int x, int y) {
        graph.get(x).add(y);
        graph.get(y).add(x);
    }

    static void BFS(int v) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.offer(v);
        visited[v] = true;

        while (!dq.isEmpty()) {
            int current = dq.poll();
            sb.append(current).append(" ");

            Collections.sort(graph.get(current));

            for (int x : graph.get(current)) {
                if (!visited[x]) {
                    dq.offer(x);
                    visited[x] = true;
                }
            }
        }
    }

    static void DFS(int v) {
        visited[v] = true;
        sb.append(v).append(" ");

        Collections.sort(graph.get(v));

        for (int x : graph.get(v)) {
            if (!visited[x]) {
                DFS(x);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        M = Integer.parseInt(st.nextToken()); // 간선의 개수
        V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호 V

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            putEdges(x, y);
        }

        sb = new StringBuilder();
        DFS(V);
        sb.append("\n");

        visited = new boolean[N + 1];
        BFS(V);
        System.out.println(sb.toString());
    }
}