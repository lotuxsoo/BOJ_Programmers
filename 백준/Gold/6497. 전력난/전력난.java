
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int x, y, cost;

        Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String[] sp = br.readLine().split(" ");
            int m = Integer.parseInt(sp[0]);
            int n = Integer.parseInt(sp[1]);
            if (m == 0 && n == 0) {
                break;
            }

            parent = new int[m];
            rank = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }

            long total = 0;
            ArrayList<Edge> edges = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                edges.add(new Edge(x, y, z));
                total += z;
            }

            // MST 만들기
            long minCost = 0;

            Collections.sort(edges, (a, b) -> Integer.compare(a.cost, b.cost));

            for (Edge edge : edges) {
                if (find(edge.x) != find(edge.y)) {
                    union(edge.x, edge.y);
                    minCost += edge.cost;
                }

            }

            System.out.println(total - minCost);
        }
    }
}
