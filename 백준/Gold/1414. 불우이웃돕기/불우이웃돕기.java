import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

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
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        int totalCost = 0;

        for (int i = 0; i < n; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < ch.length; j++) {
                if (ch[j] == 0) {
                    continue;
                }
                if (ch[j] >= 'a' && ch[j] <= 'z') {
                    int c = ch[j] - 'a' + 1;
                    pq.add(new Edge(i, j, c));
                    totalCost += c;
                } else if (ch[j] >= 'A' && ch[j] <= 'Z') {
                    int c = ch[j] - 'A' + 27;
                    pq.add(new Edge(i, j, c));
                    totalCost += c;
                }
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
        int edgeCost = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (find(edge.a) != find(edge.b)) {
                union(edge.a, edge.b);
                edgeCount++;
                edgeCost += edge.cost;
            }

            if (edgeCount == n - 1) {
                break;
            }
        }

        System.out.println(edgeCount == n - 1 ? totalCost - edgeCost : -1);
    }
}
