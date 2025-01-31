import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int a, b, cost;

        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static int N, totalLength = 0;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static int find(int x) {
        if (x != parent[x]) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) parent[rootX] = rootY;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (line[j] == '0') continue;
                int cost = (line[j] >= 'a') ? (line[j] - 'a' + 1) : (line[j] - 'A' + 27);
                totalLength += cost;
                if (i != j) pq.add(new Edge(i, j, cost));  // ✅ 모든 간선 추가
            }
        }

        int mstCost = 0, edgeCount = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (find(edge.a) != find(edge.b)) {
                union(edge.a, edge.b);
                mstCost += edge.cost;
                edgeCount++;
            }
        }

        // ✅ 모든 정점이 연결되지 못한 경우 (MST가 완성되지 않음)
        boolean isConnected = true;
        int root = find(0);
        for (int i = 1; i < N; i++) {
            if (find(i) != root) {
                isConnected = false;
                break;
            }
        }

        if (!isConnected) {
            System.out.println(-1);
        } else {
            System.out.println(totalLength - mstCost);
        }
    }
}