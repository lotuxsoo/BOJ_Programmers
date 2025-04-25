
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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
        int a, b;
        double cost;

        Edge(int a, int b, double cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static double getDist(int idx1, int idx2) {
        return Math.sqrt(Math.pow(nodes[idx1].x - nodes[idx2].x, 2) + Math.pow(nodes[idx1].y - nodes[idx2].y, 2));
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        if (root1 != root2) {
            if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }
        }
    }

    static int[] rank, parent;
    static int N, M;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);

        nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]);
            int y = Integer.parseInt(sp[1]);
            nodes[i] = new Node(i, x, y);
        }

        rank = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]);
            int y = Integer.parseInt(sp[1]);

            if (find(x) != find(y)) {
                union(x, y);
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Double.compare(a.cost, b.cost));
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double dist = getDist(i, j);
                pq.add(new Edge(i, j, dist));
            }
        }

        double result = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (find(cur.a) != find(cur.b)) {
                union(cur.a, cur.b);
                result += cur.cost;
            }
        }

        System.out.println(String.format("%.2f", result));
    }
}
