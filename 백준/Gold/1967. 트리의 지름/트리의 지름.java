
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

    static void dfs(int cur, int curDist) {
        visited[cur] = true;
        if (maxDist < curDist) {
            maxDist = curDist;
            maxNode = cur;
        }

        for (Node next : graph[cur]) {
            if (!visited[next.val]) {
                dfs(next.val, curDist + next.dist);
            }
        }
    }

    static ArrayList<Node>[] graph;
    static int n;
    static boolean[] visited;
    static int maxDist = 0, maxNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w)); // 양방향 연결 필요
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        maxDist = 0;
        visited = new boolean[n + 1];
        dfs(maxNode, 0);
        System.out.println(maxDist);
    }
}
