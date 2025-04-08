import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
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
        int r1 = find(x), r2 = find(y);
        if (r1 != r2) {
            if (rank[r1] < rank[r2]) {
                parent[r1] = r2;
            } else if (rank[r1] > rank[r2]) {
                parent[r2] = r1;
            } else {
                parent[r2] = r1;
                rank[r1]++;
            }
        }
    }

    static int[] parent, rank;
    static int N, M;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long budget = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
            budget += c;
        }

        parent = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        Collections.sort(edges, (x, y) -> Integer.compare(x.cost, y.cost));

        long edgeCount = 0, cost = 0;
        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                edgeCount++;
                cost += edge.cost;
            }

            if (edgeCount == N - 1) {
                break;
            }
        }

        System.out.println(edgeCount < N - 1 ? -1 : budget - cost);
    }
}
