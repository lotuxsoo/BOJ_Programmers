import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int a, b, cost;

        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int r1 = find(x);
        int r2 = find(y);

        if (rank[r1] < rank[r2]) {
            parent[r1] = r2;
        } else if (rank[r1] > rank[r2]) {
            parent[r2] = r1;
        } else {
            parent[r2] = r1;
            rank[r1]++;
        }
    }

    static int N, M;
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }

        // 집합 초기화
        parent = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int nodeCount = 0;
        int totalCost = 0;

        while (nodeCount < N - 1) {
            Edge edge = pq.poll();

            if (find(edge.a) != find(edge.b)) {
                union(edge.a, edge.b);
                totalCost += edge.cost;
                nodeCount++;
            }
        }

        System.out.println(totalCost);
    }
}
