
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int val, weight;

        Node(int val, int weight) {
            this.val = val;
            this.weight = weight;
        }
    }

    static void dfs(int cur, int weight) {
        dist[cur] = weight;
        result = Math.max(result, dist[cur]);

        for (Node next : graph[cur]) {
            if (dist[next.val] == -1) {
                dfs(next.val, weight + next.weight);
            }
        }
    }

    static int n;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[p].add(new Node(c, w));
            graph[c].add(new Node(p, w)); // 양방향 그래프 저장 필수
        }

        dist = new int[n + 1];
        Arrays.fill(dist, -1);

        dfs(1, 0);
        int maxNode = 0;
        for (int i = 1; i <= n; i++) {
            if (result == dist[i]) {
                maxNode = i;
            }
        }

        result = 0;
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dfs(maxNode, 0);
        System.out.println(result);
    }
}
