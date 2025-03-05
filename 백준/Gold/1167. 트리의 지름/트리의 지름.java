
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    static Node bfs(int start) {
        int maxVertex = 0;
        int maxDist = 0;

        int[] dist = new int[V + 1];
        Arrays.fill(dist, -1);
        dist[start] = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (dist[cur.vertex] < cur.cost) {
                continue;
            }

            for (Node next : A[cur.vertex]) {
                if (dist[next.vertex] == -1) {
                    dist[next.vertex] = dist[cur.vertex] + next.cost;
                    if (maxDist < dist[next.vertex]) {
                        maxDist = dist[next.vertex];
                        maxVertex = next.vertex;
                    }
                    queue.add(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }

        return new Node(maxVertex, maxDist);
    }

    static int V;
    static ArrayList<Node>[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        A = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            while (true) {
                int nxt = Integer.parseInt(st.nextToken());
                if (nxt == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());
                A[cur].add(new Node(nxt, cost));
            }
        }

        Node X = bfs(1);
        Node Y = bfs(X.vertex);

        System.out.println(Y.cost);
    }
}
