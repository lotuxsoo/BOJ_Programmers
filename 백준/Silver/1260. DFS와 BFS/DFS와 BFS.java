import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M, V;
    static int[][] graph;
    static boolean[] visited;
    static StringBuilder sb;

    static void putEdges(int x, int y) {
        graph[x][y] = 1;
        graph[y][x] = 1;
    }

    static void BFS(int v) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.offer(v);
        visited[v] = true;

        while (!dq.isEmpty()) {
            int current = dq.poll();
            sb.append(current).append(" ");

            for (int i = 1; i <= N; i++) {
                if (graph[current][i] == 1 && !visited[i]) {
                    dq.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

    static void DFS(int v) {
        visited[v] = true;
        sb.append(v).append(" ");
        for (int i = 1; i <= N; i++) {
            if (graph[v][i] == 1 && !visited[i]) {
                DFS(i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        M = Integer.parseInt(st.nextToken()); // 간선의 개수
        V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호 V

        graph = new int[N + 1][N + 1];
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