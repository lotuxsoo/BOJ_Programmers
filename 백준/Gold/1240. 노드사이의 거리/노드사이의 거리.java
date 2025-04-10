
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int val, dist;

        Node(int val, int dist) {
            this.val = val;
            this.dist = dist;
        }
    }

    static ArrayList<Node>[] graph;
    static int N, M;
    static boolean[] visited;

    static int dfs(int s, int e, int totalDist) {
        if (e == s) {
            return totalDist;
        }

        visited[s] = true;

        for (Node next : graph[s]) {
            if (!visited[next.val]) {
                // 다음 for루프 영향 안주도록 파라미터로 거리 누적
                int result = dfs(next.val, e, totalDist + next.dist);
                if (result != -1) {
                    return result;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>(); // null이 아닌 상태로
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            visited = new boolean[N + 1];
            int dist = dfs(u, v, 0);
            System.out.println(dist);
        }
    }
}
