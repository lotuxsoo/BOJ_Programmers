
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int node, cost;

        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static int V, E;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c)); // 단방향
        }

        int minCost = Integer.MAX_VALUE;
        boolean found = false;

        for (int i = 1; i <= V; i++) {
            int[] dist = new int[V + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[i] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
            pq.add(new Node(i, 0));

            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (dist[cur.node] < cur.cost) {
                    continue;
                }

                if (cur.node == i && dist[i] != 0) {
                    minCost = Math.min(minCost, dist[cur.node]);
                    found = true;
                    break;
                }

                for (Node next : graph[cur.node]) {
                    if (dist[next.node] == 0 || dist[next.node] > dist[cur.node] + next.cost) {
                        dist[next.node] = dist[cur.node] + next.cost;
                        pq.add(new Node(next.node, cur.cost + next.cost));
                    }
                }
            }
        }

        System.out.println(found ? minCost : -1);
    }
}
