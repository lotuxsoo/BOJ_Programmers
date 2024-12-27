import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge> {
        int x, y, cost;

        Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost; // 가중치 기준 오름차순
        }
    }

    static int[] parent;

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int r1 = find(x);
        int r2 = find(y);
        if (r1 != r2) {
            parent[r1] = r2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        Collections.sort(edges);

        parent = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            parent[i] = i;
        }

        int sum = 0;
        int cnt = 0; // V-1 돼야함
        for (Edge e : edges) {
            if (cnt == V - 1) {
                break;
            }
            if (find(e.x) != find(e.y)) {
                union(e.x, e.y);
                sum += e.cost;
                cnt++;
            }
        }
        System.out.println(sum);
    }
}