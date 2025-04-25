
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static class Node {
        int idx, x, y;

        Node(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge {
        int to;
        double cost;

        Edge(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static double getDist(int idx1, int idx2) {
        return Math.sqrt(Math.pow(nodes[idx1].x - nodes[idx2].x, 2) + Math.pow(nodes[idx1].y - nodes[idx2].y, 2));
    }
    
    static int N, M;
    static Node[] nodes;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);

        nodes = new Node[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]);
            int y = Integer.parseInt(sp[1]);
            nodes[i] = new Node(i, x, y);
        }

        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double dist = getDist(i, j);
                graph[i].add(new Edge(j, dist));
                graph[j].add(new Edge(i, dist));
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Double.compare(a.cost, b.cost));

        for (int i = 0; i < M; i++) {
            sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]);
            int y = Integer.parseInt(sp[1]);

            graph[x].add(new Edge(y, 0));
            graph[y].add(new Edge(x, 0));
        }

        boolean[] visited = new boolean[N + 1];
        double result = 0;
        int connected = 0;
        pq.add(new Edge(1, 0));

        // 작은 간선부터 하나씩 꺼냄
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;
            result += cur.cost;
            connected++;

            if (connected == N) {
                break;
            }

            for (Edge next : graph[cur.to]) {
                if (!visited[next.to]) {
                    pq.add(next);
                }
            }
        }

        System.out.println(String.format("%.2f", result));
    }
}
