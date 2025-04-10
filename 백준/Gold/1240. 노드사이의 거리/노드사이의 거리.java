
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

    static int dfs(int s, int e, int dist) {
        if (s == e) {
            return dist;
        }

        visited[s] = true;

        for (Node next : graph[s]) {
            if (!visited[next.val]) {
                int result = dfs(next.val, e, dist + next.dist);
                if (result != -1) {
                    return result;
                }
            }
        }

        return -1;
    }

    static int N, M;
    static ArrayList<Node>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
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
            visited = new boolean[N + 1];
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            System.out.println(dfs(u, v, 0));
        }
    }
}
