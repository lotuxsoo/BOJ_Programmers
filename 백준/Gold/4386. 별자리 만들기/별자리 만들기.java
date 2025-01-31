import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int a, b;
        double dist;

        Edge(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
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
        if (r1 != r2) {
            parent[r1] = r2;
        }
    }

    static double[][] nodes;
    static PriorityQueue<Edge> pq;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        nodes = new double[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            nodes[i][0] = x;
            nodes[i][1] = y;
        }

        pq = new PriorityQueue<>((i, j) -> Double.compare(i.dist, j.dist));

        // 간선들 구하기
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                double x1 = nodes[i][0], y1 = nodes[i][1], x2 = nodes[j][0], y2 = nodes[j][1];
                double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                pq.add(new Edge(i, j, distance));
            }
        }

        // 유니온 파인드 (인덱스 저장)
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        double total = 0;

        while (cnt < N - 1) {
            Edge cur = pq.poll();

            if (find(cur.a) != find(cur.b)) {
                union(cur.a, cur.b);
                cnt++;
                total += cur.dist;
            }
        }

        System.out.printf("%.2f", total);
    }
}
