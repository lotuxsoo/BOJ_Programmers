import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int a, b; // 별의 인덱스
        double cost;

        Edge(int a, int b, double cost) {
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
        int root1 = find(x);
        int root2 = find(y);

        if (rank[root1] < rank[root2]) {
            parent[root1] = root2;
        } else if (rank[root1] > rank[root2]) {
            parent[root2] = root1;
        } else {
            parent[root2] = root1;
            rank[root1]++;
        }
    }

    static int n;
    static double[][] stars;
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        stars = new double[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.cost, o2.cost));

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double x1 = stars[i][0], y1 = stars[i][1], x2 = stars[j][0], y2 = stars[j][1];
                double cost = Math.sqrt((Math.pow(x1 - x2, 2)) + Math.pow(y1 - y2, 2));
                pq.add(new Edge(i, j, cost));
            }
        }

        // 집합 초기화
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        // 크루스칼
        int edgeCount = 0;
        double totalCost = 0.0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (find(edge.a) != find(edge.b)) {
                union(edge.a, edge.b);
                edgeCount++;
                totalCost += edge.cost;
            }

            if (edgeCount == n - 1) {
                break;
            }
        }

        System.out.printf("%.2f", totalCost);
    }
}
